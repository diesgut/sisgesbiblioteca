/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.TemaPaginationBean;
import com.sisgesbib.dao.TemaDAO;
import com.sisgesbib.dao.beans.TemaBean;
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
public class ThemeMaintenanceMB {

    private String initPage;
    TemaDAO temaDAO = DAOFactory.getTemaDAO();

    /** Creates a new instance of ThemeMaintenanceMB */
    public ThemeMaintenanceMB() {
        try {
            if (GeneralUtil.getViewMap("temaPaginationBean") == null) {
                listaThemes();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaThemes() throws SQLException {
        TemaPaginationBean pgnBean = (TemaPaginationBean) GeneralUtil.getViewMap("temaPaginationBean");
        if (pgnBean == null) {
            pgnBean = new TemaPaginationBean();
            System.out.println("entro null");
        }
        List<TemaBean> lstTemas = temaDAO.getListaTheme(pgnBean);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de temas es  : " + lstTemas.size());
        pgnBean.setListaTema(lstTemas);
        GeneralUtil.putViewMap("temaPaginationBean", pgnBean);
    }

    public void selectTheme(ActionEvent event) {
        try {
            TemaBean bean = (TemaBean) event.getComponent().getAttributes().get("themeSelected");
            GeneralUtil.putRequestMap("temaBean", bean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void insertTheme() {
        try {
            TemaBean bean = (TemaBean) GeneralUtil.getRequestMap("temaBean");
            bean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            if (temaDAO.validateNombre(bean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            temaDAO.insertTheme(bean);
            listaThemes();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateTheme() {
        try {
            TemaBean temaBean=(TemaBean)GeneralUtil.getRequestMap("temaBean");
            temaBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            if(temaDAO.validateChangedName(temaBean)>0){
                String error=MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            temaDAO.updateTheme(temaBean);
            listaThemes();
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
