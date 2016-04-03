/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.EjemplarPaginationBean;
import com.sisgesbib.dao.AutorDAO;
import com.sisgesbib.dao.EjemplarDAO;
import com.sisgesbib.dao.EmpresaDAO;
import com.sisgesbib.dao.SedeDAO;
import com.sisgesbib.dao.beans.AutorBean;
import com.sisgesbib.dao.beans.EjemplarBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Diego
 */
public class ExemplaryMaintenanceMB {

    AutorDAO autorDAO = null;
    EmpresaDAO empresaDAO = null;
    EjemplarDAO ejemplarDAO = null;
    SedeDAO sedeDAO = null;
    private String initPage;
    private Map generalMap;
    private EjemplarBean ejemplarBean = null;

    /** Creates a new instance of ExemplaryMaintenanceMB */
    public ExemplaryMaintenanceMB() {
        try {
            autorDAO = DAOFactory.getAutorDAO();
            ejemplarDAO = DAOFactory.getEjemplarDAO();
            empresaDAO = DAOFactory.getEmpresaDAO();
            sedeDAO = DAOFactory.getSedeDAO();

            if (GeneralUtil.getViewMap("viewTextoId") == null) {
                generalMap = (Map) GeneralUtil.getSessionMap("textoBeanSession");
                System.out.println(generalMap.get("nombreCategoria"));
                List<AutorBean> listaAutores = autorDAO.getAuthorsByTexto(Integer.parseInt(generalMap.get("textoId").toString()));
                int textoId = Integer.parseInt(generalMap.get("textoId").toString());
                GeneralUtil.putViewMap("viewTextName", generalMap.get("nombre"));
                GeneralUtil.putViewMap("viewPublisher", generalMap.get("nombreEditorial"));
                GeneralUtil.putViewMap("viewCategory", generalMap.get("nombreCategoria"));
                GeneralUtil.putViewMap("viewTextoId", textoId);
                GeneralUtil.removeSessionMap("textoBeanSession");
                GeneralUtil.putViewMap("viewListSelectedAuthors", listaAutores);

                listaEjemplarPorTexto(textoId);
                laodComboEmpresa();

            }

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaEjemplarPorTexto(int textoId) throws SQLException {
        EjemplarPaginationBean pgn = new EjemplarPaginationBean();
        pgn.setTextoId(textoId);
        pgn.setSedeId(GeneralUtil.getSessionUser().getSedeId());
        List<Map> listaEjemplar = (List<Map>) ejemplarDAO.getListaEjemplarPorTexto(pgn);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de ejemplares es : " + listaEjemplar.size());
        pgn.setListaEjemplares(listaEjemplar);
        GeneralUtil.putViewMap("ejemplarPaginationBean", pgn);
    }

    public void loadSedeByCompany() {
        try {
            int companyId = Integer.parseInt(GeneralUtil.getRequestMap("reqCompanyId").toString());
            loadSedePorEmpresa(companyId);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void laodComboEmpresa() throws SQLException {
        Map mapEmpresa = empresaDAO.getMapCompany();
        GeneralUtil.putViewMap("viewMapEmpresa", mapEmpresa);
    }

    public void loadSedePorEmpresa(int companyId) throws SQLException {
        Map mapSede = sedeDAO.getSedePorEmpresa(companyId);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño del map de sedes : " + mapSede.size());
        GeneralUtil.putViewMap("viewMapSede", mapSede);
    }

    public void selectExemplary(ActionEvent event) {
        try {
            generalMap = (Map) event.getComponent().getAttributes().get("ejemplarSelected");
            ejemplarBean = new EjemplarBean();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;

            if (generalMap.get("fechaIns") != null) {
                generalMap.put("fechaIns", null);
            }
            if (generalMap.get("fechaAct") != null) {
                generalMap.put("fechaAct", null);
            }
            date = df.parse(generalMap.get("fechaAdquisicion").toString());
            generalMap.put("fechaAdquisicion", null);

            BeanUtils.populate(ejemplarBean, generalMap);
            ejemplarBean.setFechaAdquisicion(date);
            generalMap.put("fechaAdquisicion", df.format(date));

            GeneralUtil.putRequestMap("ejemplarBean", ejemplarBean);
            int companyId = empresaDAO.getEmpresaBySede(ejemplarBean.getSedeId());

            GeneralUtil.putRequestMap("reqCompanyId", companyId);
            loadSedeByCompany();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void insertaEjemplar() {
        try {
            Integer textoId = Integer.parseInt(GeneralUtil.getViewMap("viewTextoId").toString());
            ejemplarBean = (EjemplarBean) GeneralUtil.getRequestMap("ejemplarBean");
            ejemplarBean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            ejemplarBean.setTextoId(textoId);
            System.out.println("el texto id es " + textoId);
            System.out.println(ejemplarBean.toString());
            ejemplarDAO.insertaEjemplar(ejemplarBean);
            GeneralUtil.putRequestMap("success", true);
            GeneralUtil.removeRequestMap("ejemplarBean");
            listaEjemplarPorTexto(textoId);
            GeneralUtil.removeVewMap("viewMapSede");
        } catch (Exception e) {
            GeneralUtil.putRequestMap("success", false);
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateExemplary() {
        try {
            Integer textoId = Integer.parseInt(GeneralUtil.getViewMap("viewTextoId").toString());
            ejemplarBean = (EjemplarBean) GeneralUtil.getRequestMap("ejemplarBean");
            ejemplarBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            ejemplarBean.setTextoId(textoId);

            System.out.println(ejemplarBean.toString());
            ejemplarDAO.updateEjemplar(ejemplarBean);
            GeneralUtil.putRequestMap("success", true);
            GeneralUtil.removeRequestMap("ejemplarBean");
            listaEjemplarPorTexto(textoId);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    /**
     * @return the initPage
     */
    public String getInitPage() {
        return initPage;
    }

    /**
     * @param initPage the initPage to set
     */
    public void setInitPage(String initPage) {
        this.initPage = initPage;
    }
}
