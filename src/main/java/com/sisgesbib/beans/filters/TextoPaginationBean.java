/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.TextoBean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class TextoPaginationBean extends TextoBean implements Serializable{

    private List<Map> listaTexto;
    private String criterioBusqueda;
    private String textoBusqueda;
    
    /** Creates a new instance of TextoPaginationBean */
    public TextoPaginationBean() {
    }

    /**
     * @return the listaTexto
     */
    public List<Map> getListaTexto() {
        return listaTexto;
    }

    /**
     * @param listaTexto the listaTexto to set
     */
    public void setListaTexto(List<Map> listaTexto) {
        this.listaTexto = listaTexto;
    }

    /**
     * @return the criterioBusqueda
     */
    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    /**
     * @param criterioBusqueda the criterioBusqueda to set
     */
    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    /**
     * @return the textoBusqueda
     */
    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    /**
     * @param textoBusqueda the textoBusqueda to set
     */
    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

}
