/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class PrestamoBean implements Serializable{

    private Integer prestamoId;
    private Date fechaIns;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean renovado;
    private String estado;
    private String tipoPrestamo;
    private String tipoDocGarantia;
    private String numDocGarantia;
    private Integer usuarioAte;
    private Integer ejemplarId;
    private Integer solicitudId;

    /** Creates a new instance of PrestamoBean */
    public PrestamoBean() {
    }

    @Override
    public String toString() {
        return "PrestamoBean{" + "prestamoId=" + prestamoId + ", fechaIns=" + fechaIns + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", renovado=" + renovado + ", estado=" + estado + ", tipoPrestamo=" + tipoPrestamo + ", tipoDocGarantia=" + tipoDocGarantia + ", numDocGarantia=" + numDocGarantia + ", usuarioAte=" + usuarioAte + ", ejemplarId=" + ejemplarId + ", solicitudId=" + solicitudId + '}';
    }

    /**
     * @return the prestamoId
     */
    public Integer getPrestamoId() {
        return prestamoId;
    }

    /**
     * @param prestamoId the prestamoId to set
     */
    public void setPrestamoId(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }

    /**
     * @return the fechaIns
     */
    public Date getFechaIns() {
        return fechaIns;
    }

    /**
     * @param fechaIns the fechaIns to set
     */
    public void setFechaIns(Date fechaIns) {
        this.fechaIns = fechaIns;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the renovado
     */
    public Boolean getRenovado() {
        return renovado;
    }

    /**
     * @param renovado the renovado to set
     */
    public void setRenovado(Boolean renovado) {
        this.renovado = renovado;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the tipoPrestamo
     */
    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * @param tipoPrestamo the tipoPrestamo to set
     */
    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    /**
     * @return the tipoDocGarantia
     */
    public String getTipoDocGarantia() {
        return tipoDocGarantia;
    }

    /**
     * @param tipoDocGarantia the tipoDocGarantia to set
     */
    public void setTipoDocGarantia(String tipoDocGarantia) {
        this.tipoDocGarantia = tipoDocGarantia;
    }

    /**
     * @return the numDocGarantia
     */
    public String getNumDocGarantia() {
        return numDocGarantia;
    }

    /**
     * @param numDocGarantia the numDocGarantia to set
     */
    public void setNumDocGarantia(String numDocGarantia) {
        this.numDocGarantia = numDocGarantia;
    }

    /**
     * @return the usuarioAte
     */
    public Integer getUsuarioAte() {
        return usuarioAte;
    }

    /**
     * @param usuarioAte the usuarioAte to set
     */
    public void setUsuarioAte(Integer usuarioAte) {
        this.usuarioAte = usuarioAte;
    }

    /**
     * @return the ejemplarId
     */
    public Integer getEjemplarId() {
        return ejemplarId;
    }

    /**
     * @param ejemplarId the ejemplarId to set
     */
    public void setEjemplarId(Integer ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    /**
     * @return the solicitudId
     */
    public Integer getSolicitudId() {
        return solicitudId;
    }

    /**
     * @param solicitudId the solicitudId to set
     */
    public void setSolicitudId(Integer solicitudId) {
        this.solicitudId = solicitudId;
    }
}
