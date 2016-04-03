/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.TemaBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Diego
 */
public class TemaPaginationBean extends TemaBean implements Serializable{

    private List<TemaBean> listaTema;

    /** Creates a new instance of TemaPaginationBean */
    public TemaPaginationBean() {
    }

    /**
     * @return the listaTema
     */
    public List<TemaBean> getListaTema() {
        return listaTema;
    }

    /**
     * @param listaTema the listaTema to set
     */
    public void setListaTema(List<TemaBean> listaTema) {
        this.listaTema = listaTema;
    }
}
