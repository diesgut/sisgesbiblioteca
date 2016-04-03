/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.LectorPaginacionBean;
import com.sisgesbib.dao.LectorDAO;
import com.sisgesbib.dao.beans.LectorBean;
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
 * @author Diego
 */
public class ReaderMaintenance {

    LectorDAO lectorDAO = DAOFactory.getLectorDAO();
    LectorBean lectorBean = null;
    private String initPage;

    /** Creates a new instance of ReaderMaintenance */
    public ReaderMaintenance() {
        if (GeneralUtil.getViewMap("lectorPaginacionBean") == null) {
            try {
                LectorPaginacionBean pgnLector = (LectorPaginacionBean) GeneralUtil.getViewMap("lectorPaginacionBean");
                pgnLector = new LectorPaginacionBean();
                GeneralUtil.putViewMap("lectorPaginacionBean", pgnLector);
                listaLectores();
            } catch (Exception e) {
                Log.logger.error("[UserId:" + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
            }

        }
    }

    public void listaLectores() throws SQLException {
        LectorPaginacionBean pgnLector = (LectorPaginacionBean) GeneralUtil.getViewMap("lectorPaginacionBean");
        List<LectorBean> listaLectores = (List<LectorBean>) lectorDAO.getListaLectores(pgnLector);
        pgnLector.setListaLectores(listaLectores);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de lectores es " + listaLectores.size());
        GeneralUtil.putViewMap("lectorPaginacionBean", pgnLector);
    }

    public void insertaLector() {
        try {
            lectorBean = (LectorBean) GeneralUtil.getRequestMap("lectorBean");
            if (lectorDAO.validaCodigoLector(lectorBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextCodIntInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            lectorBean.setEstado(true);
            lectorBean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            lectorDAO.insertaLector(lectorBean);
            listaLectores();
        } catch (Exception e) {
            Log.logger.error("[UserId:" + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateLector() {
        try {
            lectorBean = (LectorBean) GeneralUtil.getRequestMap("lectorBean");
            if (lectorDAO.validaChangeCodigoLector(lectorBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextCodIntInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            lectorBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            lectorDAO.updateLector(lectorBean);
            listaLectores();
        } catch (Exception e) {
            Log.logger.error("[UserId:" + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectReader(ActionEvent event) {
        try {
            lectorBean = (LectorBean) event.getComponent().getAttributes().get("readerSelected");
            GeneralUtil.putRequestMap("lectorBean", lectorBean);
        } catch (Exception e) {
            Log.logger.error("[UserId:" + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
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
