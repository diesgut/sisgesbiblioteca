/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.EditorialPaginationBean;
import com.sisgesbib.dao.EditorialDAO;
import com.sisgesbib.dao.UtilDAO;
import com.sisgesbib.dao.beans.EditorialBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Diego
 */
public class PublishingMaintenanceMB {

    private String initPage;
    private UtilDAO utilDAO = DAOFactory.getUtilDAO();
    private EditorialDAO editorialDAO = DAOFactory.getEditorialDAO();

    /** Creates a new instance of PublishingMaintenanceMB */
    public PublishingMaintenanceMB() {
        try {
            if (GeneralUtil.getViewMap("viewMapCountry") == null) {
                EditorialPaginationBean editPag = new EditorialPaginationBean();
                editPag.setCiudadId(-1);
                editPag.setPaisId("-1");
                GeneralUtil.putViewMap("editorialPaginationBean", editPag);
                listaEditorial();
                loadComboCountry();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaEditorial() throws SQLException {
        EditorialPaginationBean pagBean = (EditorialPaginationBean) GeneralUtil.getViewMap("editorialPaginationBean");
        List<EditorialBean> listaEditorial = editorialDAO.getListaEditorial(pagBean);
        pagBean.setListaPublishing(listaEditorial);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de editoriales es ; " + listaEditorial.size());
    }

    public void loadComboCountry() throws SQLException {
        Map mapCountry = utilDAO.getPaisForCombo();
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño del map de paises es :" + mapCountry.size());
        GeneralUtil.putViewMap("viewMapCountry", mapCountry);
    }

    public void loadCityByCountry() {
        try {
            System.out.println("entoro");
            String countryId = "";
            if (GeneralUtil.getRequestMap("reqCountryId") != null) {
                countryId = GeneralUtil.getRequestMap("reqCountryId").toString();
            } else {
                EditorialPaginationBean editPag = (EditorialPaginationBean) GeneralUtil.getViewMap("editorialPaginationBean");
                countryId = editPag.getPaisId();
            }
            System.out.println("el pais es "+countryId);
            Map mapCity = utilDAO.getCiudadPorPais(countryId);
            Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                    + "El tamaño del map de ciudades por el pais " + countryId + " es ; " + mapCity.size());
            GeneralUtil.putViewMap("viewMapCity", mapCity);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void insertaEditorial() {
        try {
            EditorialBean editorialBean = (EditorialBean) GeneralUtil.getRequestMap("editorialBean");
            editorialBean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            //falta validar el nombre
            if(editorialDAO.validaNombre(editorialBean)>0){
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            editorialDAO.insertaEditorial(editorialBean);
            listaEditorial();
            GeneralUtil.removeVewMap("viewMapCity");
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectPublishing(ActionEvent event) {
        try {
            EditorialBean editorialBean = (EditorialBean) event.getComponent().getAttributes().get("pubSelected");
            System.out.println(editorialBean.toString());
            String countryByCity = utilDAO.getCountryByCity(editorialBean.getCiudadId());
            GeneralUtil.putRequestMap("reqCountryId", countryByCity);

            Map mapCity = utilDAO.getCiudadPorPais(countryByCity);
            Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                    + "El tamaño del map de ciudades por el pais " + countryByCity + " es ; " + mapCity.size());
            GeneralUtil.putViewMap("viewMapCity", mapCity);


            GeneralUtil.putRequestMap("editorialBean", editorialBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateEditorial() {
        try {
            EditorialBean editorialBean = (EditorialBean) GeneralUtil.getRequestMap("editorialBean");
            editorialBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            if(editorialDAO.validaCambioNombre(editorialBean)>0){
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            editorialDAO.updateEditorial(editorialBean);
            listaEditorial();
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
