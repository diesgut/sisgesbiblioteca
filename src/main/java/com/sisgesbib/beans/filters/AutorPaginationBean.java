/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.AutorBean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *
 * @author despantoso
 */
public class AutorPaginationBean extends AutorBean implements Serializable{
    
    private List<Map> lstAuthors;

    /** Creates a new instance of AutorPaginationBean */
    public AutorPaginationBean() {
    }

    /**
     * @return the lstAuthors
     */
    public List<Map> getLstAuthors() {
        return lstAuthors;
    }

    /**
     * @param lstAuthors the lstAuthors to set
     */
    public void setLstAuthors(List<Map> lstAuthors) {
        this.lstAuthors = lstAuthors;
    }

}
