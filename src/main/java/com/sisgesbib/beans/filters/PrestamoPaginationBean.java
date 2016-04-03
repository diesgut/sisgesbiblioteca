/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.beans.filters;

import com.sisgesbib.dao.beans.PrestamoBean;
import com.sisgesbib.util.GeneralUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class PrestamoPaginationBean extends PrestamoBean implements Serializable{

    private Date dateFrom;
    private Date dateTo;
    private String[] argTipoPrestamo;
    private Integer sedeId=GeneralUtil.getSessionUser().getSedeId();
    
    private List<Map> listaPrestamos;

    /** Creates a new instance of PrestamoPaginationBean */
    public PrestamoPaginationBean() {
    }

    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        if (dateFrom == null) {
            dateFrom = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFrom);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            dateFrom = cal.getTime();
        }
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public Date getDateTo() {
        if(dateTo==null){
            dateTo=new Date();
        }
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the argTipoPrestamo
     */
    public String[] getArgTipoPrestamo() {
        return argTipoPrestamo;
    }

    /**
     * @param argTipoPrestamo the argTipoPrestamo to set
     */
    public void setArgTipoPrestamo(String[] argTipoPrestamo) {
        this.argTipoPrestamo = argTipoPrestamo;
    }

    /**
     * @return the listaPrestamos
     */
    public List<Map> getListaPrestamos() {
        return listaPrestamos;
    }

    /**
     * @param listaPrestamos the listaPrestamos to set
     */
    public void setListaPrestamos(List<Map> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    /**
     * @return the sedeId
     */
    public Integer getSedeId() {
        return sedeId;
    }

    /**
     * @param sedeId the sedeId to set
     */
    public void setSedeId(Integer sedeId) {
        this.sedeId = sedeId;
    }
}
