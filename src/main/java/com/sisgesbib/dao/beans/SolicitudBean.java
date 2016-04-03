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
public class SolicitudBean implements Serializable{

    @Override
    public String toString() {
        return "SolicitudBean{" + "solicitudId=" + solicitudId + ", fechaIns=" + fechaIns + ", fechaAte=" + fechaAte + ", tipoPrestamo=" + tipoPrestamo + ", estado=" + estado + ", usuarioAte=" + usuarioAte + ", textoId=" + textoId + ", lectorId=" + lectorId + ", sedeId=" + sedeId + '}';
    }

    private Integer solicitudId;
    private Date fechaIns;
    private Date fechaAte;
    private String tipoPrestamo;
    private String estado;
    private Integer usuarioAte;
    private Integer textoId;
    private Integer lectorId;
    private Integer sedeId;

    /** Creates a new instance of SolicitudBean */
    public SolicitudBean() {
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
     * @return the fechaAte
     */
    public Date getFechaAte() {
        return fechaAte;
    }

    /**
     * @param fechaAte the fechaAte to set
     */
    public void setFechaAte(Date fechaAte) {
        this.fechaAte = fechaAte;
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
     * @return the textoId
     */
    public Integer getTextoId() {
        return textoId;
    }

    /**
     * @param textoId the textoId to set
     */
    public void setTextoId(Integer textoId) {
        this.textoId = textoId;
    }

    /**
     * @return the lectorId
     */
    public Integer getLectorId() {
        return lectorId;
    }

    /**
     * @param lectorId the lectorId to set
     */
    public void setLectorId(Integer lectorId) {
        this.lectorId = lectorId;
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
