/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.CategoriaBean;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author despantoso
 */
public class CategoriaPaginationBean extends CategoriaBean implements Serializable{

    private List<CategoriaBean> listaCategoria;

    /** Creates a new instance of CategoriaPaginationBean */
    public CategoriaPaginationBean() {
    }

    /**
     * @return the listaCategoria
     */
    public List<CategoriaBean> getListaCategoria() {
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<CategoriaBean> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

}
