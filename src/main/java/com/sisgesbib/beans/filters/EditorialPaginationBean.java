/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.EditorialBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Diego
 */
public class EditorialPaginationBean extends EditorialBean implements Serializable {

    private List<EditorialBean> listaPublishing;
    private String paisId;

    /** Creates a new instance of EditorialPaginationBean */
    public EditorialPaginationBean() {
    }

    /**
     * @return the listaPublishing
     */
    public List<EditorialBean> getListaPublishing() {
        return listaPublishing;
    }

    /**
     * @param listaPublishing the listaPublishing to set
     */
    public void setListaPublishing(List<EditorialBean> listaPublishing) {
        this.listaPublishing = listaPublishing;
    }

    /**
     * @return the paisId
     */
    public String getPaisId() {
        return paisId;
    }

    /**
     * @param paisId the paisId to set
     */
    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }
}
