/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.CategoriaPaginationBean;
import com.sisgesbib.dao.CategoriaDAO;
import com.sisgesbib.dao.beans.CategoriaBean;
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
public class CategoryMaintenance {

    private String initPage;
    private CategoriaDAO categoriaDAO = DAOFactory.getCategoriaDAO();

    /** Creates a new instance of CategoryMaintenance */
    public CategoryMaintenance() {
        try {
            if (GeneralUtil.getViewMap("categoriaPaginationBean") == null) {
                listaCategory();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaCategory() throws SQLException {
        CategoriaPaginationBean pgnBean = (CategoriaPaginationBean) GeneralUtil.getViewMap("categoriaPaginationBean");

        if (pgnBean == null) {
            pgnBean = new CategoriaPaginationBean();
        }
        List<CategoriaBean> lstAuthors = categoriaDAO.getListaCategoria(pgnBean);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de autores es : " + lstAuthors.size());
        pgnBean.setListaCategoria(lstAuthors);
        GeneralUtil.putViewMap("categoriaPaginationBean", pgnBean);
    }

    public void insertCategory() {
        try {
            CategoriaBean bean = (CategoriaBean) GeneralUtil.getRequestMap("categoriaBean");
            bean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());
            if (categoriaDAO.validateCategory(bean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                System.out.println("error es " + error);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            categoriaDAO.insertCategory(bean);
            listaCategory();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateCategory() {
        try {
            CategoriaBean bean = (CategoriaBean) GeneralUtil.getRequestMap("categoriaBean");
            bean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            if(categoriaDAO.validateChangeName(bean)>0){
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                System.out.println("error es " + error);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            categoriaDAO.updateCategory(bean);
            listaCategory();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectCategory(ActionEvent event) {
        try {
            CategoriaBean bean = (CategoriaBean) event.getComponent().getAttributes().get("categorySelected");
            GeneralUtil.putRequestMap("categoriaBean", bean);
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
