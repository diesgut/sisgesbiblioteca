/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.MateriaBean;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author despantoso
 */
public class MateriaPaginationBean extends MateriaBean implements Serializable{

    private List<MateriaBean> lstMateria;
    /** Creates a new instance of MateriaPaginationBean */
    public MateriaPaginationBean() {
    }

    /**
     * @return the lstMateria
     */
    public List<MateriaBean> getLstMateria() {
        return lstMateria;
    }

    /**
     * @param lstMateria the lstMateria to set
     */
    public void setLstMateria(List<MateriaBean> lstMateria) {
        this.lstMateria = lstMateria;
    }

}
