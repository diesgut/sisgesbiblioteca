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
public class TemaBean implements Serializable{

    private Integer temaId;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private Date fechaIns;
    private Date fechaAct;
    private Integer usuarioIns;
    private Integer usuarioAct;
    private String nameUsuIns;
    private String nameUsuAct;

    /** Creates a new instance of TemaBean */
    public TemaBean() {
    }

    /**
     * @return the temaId
     */
    public Integer getTemaId() {
        return temaId;
    }

    /**
     * @param temaId the temaId to set
     */
    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estado
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
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
     * @return the usuarioIns
     */
    public Integer getUsuarioIns() {
        return usuarioIns;
    }

    /**
     * @param usuarioIns the usuarioIns to set
     */
    public void setUsuarioIns(Integer usuarioIns) {
        this.usuarioIns = usuarioIns;
    }

    /**
     * @return the usuarioAct
     */
    public Integer getUsuarioAct() {
        return usuarioAct;
    }

    /**
     * @param usuarioAct the usuarioAct to set
     */
    public void setUsuarioAct(Integer usuarioAct) {
        this.usuarioAct = usuarioAct;
    }

    /**
     * @return the nameUsuIns
     */
    public String getNameUsuIns() {
        return nameUsuIns;
    }

    /**
     * @param nameUsuIns the nameUsuIns to set
     */
    public void setNameUsuIns(String nameUsuIns) {
        this.nameUsuIns = nameUsuIns;
    }

    /**
     * @return the nameUsuAct
     */
    public String getNameUsuAct() {
        return nameUsuAct;
    }

    /**
     * @param nameUsuAct the nameUsuAct to set
     */
    public void setNameUsuAct(String nameUsuAct) {
        this.nameUsuAct = nameUsuAct;
    }

    /**
     * @return the fechaAct
     */
    public Date getFechaAct() {
        return fechaAct;
    }

    /**
     * @param fechaAct the fechaAct to set
     */
    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }


}
