/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.LectorBean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class LectorPaginacionBean extends LectorBean implements Serializable{

    private List<LectorBean> listaLectores;
    
    /** Creates a new instance of LectorPaginacionBean */
    public LectorPaginacionBean() {
    }

    /**
     * @return the listaLectores
     */
    public List<LectorBean> getListaLectores() {
        return listaLectores;
    }

    /**
     * @param listaLectores the listaLectores to set
     */
    public void setListaLectores(List<LectorBean> listaLectores) {
        this.listaLectores = listaLectores;
    }
    
    
}
