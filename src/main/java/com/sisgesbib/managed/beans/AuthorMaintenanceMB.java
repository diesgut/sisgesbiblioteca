/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.AutorPaginationBean;
import com.sisgesbib.dao.AutorDAO;
import com.sisgesbib.dao.PaisDAO;
import com.sisgesbib.dao.beans.AutorBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author despantoso
 */
public class AuthorMaintenanceMB {

    private String initPage;
    AutorDAO autorDAO = DAOFactory.getAutorDAO();
    PaisDAO paisDAO = DAOFactory.getPaisDAO();

    /** Creates a new instance of AuthorMaintenanceMB */
    public AuthorMaintenanceMB() {
        try {
            if (GeneralUtil.getViewMap("autorPaginationBean") == null) {
                listaAuthors();
                loadComboCountry();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaAuthors() throws SQLException {
        AutorPaginationBean pgnAuthor = (AutorPaginationBean) GeneralUtil.getViewMap("autorPaginationBean");
        if (pgnAuthor == null) {
            pgnAuthor = new AutorPaginationBean();
        }
        List<Map> lstAuthors = autorDAO.getListaAuthors(pgnAuthor);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de autores es : " + lstAuthors.size());
        pgnAuthor.setLstAuthors(lstAuthors);
        GeneralUtil.putViewMap("autorPaginationBean", pgnAuthor);
    }

    public void loadComboCountry() throws SQLException {
        Map mapComboCountry = paisDAO.getCountryForCombo();
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de paises es : " + mapComboCountry.size());
        GeneralUtil.putViewMap("viewLstCountry", mapComboCountry);
    }

    public void insertAuthor() {
        try {
            AutorBean autorBean = (AutorBean) GeneralUtil.getRequestMap("autorBean");
            autorBean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            autorBean.setEstado(true);
            System.out.println(autorBean.toString());
            if (autorDAO.validateFullNameAuthor(autorBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            autorDAO.insertaAutor(autorBean);
            listaAuthors();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectAuthor(ActionEvent event) {
        try {
            Map authorSelected = (Map) event.getComponent().getAttributes().get("authorSelected");
            AutorBean autorBean=new AutorBean();
            autorBean.setAutorId((Integer)authorSelected.get("autorId"));
            autorBean.setNombres((String)authorSelected.get("nombres"));
            autorBean.setApellidos((String)authorSelected.get("apellidos"));
            autorBean.setEmail((String)authorSelected.get("email"));
            autorBean.setWeb((String)authorSelected.get("web"));
            autorBean.setSexo((String)authorSelected.get("sexo"));
            autorBean.setPaisId((String)authorSelected.get("paisId"));
            autorBean.setEstado((Boolean)authorSelected.get("estado"));
            
            GeneralUtil.putRequestMap("autorBean", autorBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateAutor() {
        try {
            AutorBean autorBean = (AutorBean) GeneralUtil.getRequestMap("autorBean");
            autorBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            System.out.println(autorBean.toString());
            if (autorDAO.validateChangeFullName(autorBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            autorDAO.updateAutor(autorBean);
            listaAuthors();
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
