/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.MateriaPaginationBean;
import com.sisgesbib.dao.MateriaDAO;
import com.sisgesbib.dao.beans.MateriaBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author despantoso
 */
public class MatterMaintenanceMB {

    private String initPage;
    private MateriaBean materiaBean=null;
    private MateriaDAO materiaDAO = DAOFactory.getMateriaDAO();

    /** Creates a new instance of MatterMaintenanceMB */
    public MatterMaintenanceMB() {
        try {
            if (GeneralUtil.getViewMap("materiaPaginationBean") == null) {
                listaMatters();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectMatter(ActionEvent event) throws SQLException {
        try {
            materiaBean = (MateriaBean) event.getComponent().getAttributes().get("matterSelected");
            GeneralUtil.putRequestMap("materiaBean", materiaBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaMatters() throws SQLException {
        MateriaPaginationBean pgnBean = (MateriaPaginationBean) GeneralUtil.getViewMap("materiaPaginationBean");
        if (pgnBean == null) {
            pgnBean = new MateriaPaginationBean();
            System.out.println("entro null");
        }
        List<MateriaBean> lstMatters = materiaDAO.getListaMateria(pgnBean);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de materias es  : " + lstMatters.size());
        pgnBean.setLstMateria(lstMatters);
        GeneralUtil.putViewMap("materiaPaginationBean", pgnBean);
    }

    public void insertMatter() throws SQLException {
        try {
            materiaBean = (MateriaBean) GeneralUtil.getRequestMap("materiaBean");
            materiaBean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            if (materiaDAO.validateName(materiaBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            materiaDAO.insertMatter(materiaBean);
            listaMatters();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateMatter() throws SQLException {
        try {
            materiaBean = (MateriaBean) GeneralUtil.getRequestMap("materiaBean");
            materiaBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            if(materiaDAO.validateChangeName(materiaBean)>0){
                String error=MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR, error,error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            materiaDAO.updateMatter(materiaBean);
            listaMatters();
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
