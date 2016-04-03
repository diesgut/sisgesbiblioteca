/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.EjemplarBean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class EjemplarPaginationBean extends EjemplarBean implements Serializable{

    private List<Map> listaEjemplares;
    
    /** Creates a new instance of EjemplarPaginationBean */
    public EjemplarPaginationBean() {
    }

    /**
     * @return the listaEjemplares
     */
    public List<Map> getListaEjemplares() {
        return listaEjemplares;
    }

    /**
     * @param listaEjemplares the listaEjemplares to set
     */
    public void setListaEjemplares(List<Map> listaEjemplares) {
        this.listaEjemplares = listaEjemplares;
    }
}
