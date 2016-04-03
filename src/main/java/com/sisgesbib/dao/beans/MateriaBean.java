/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author despantoso
 */
public class MateriaBean implements Serializable {

    private Integer materiaId;
    private String nombre;
    private String Descripcion;
    private Boolean estado;
    private Date fechaIns;
    private Date fechaAct;
    private Integer usuarioIns;
    private Integer usuarioAct;
    private String nameUsuIns;
    private String nameUsuAct;

    /** Creates a new instance of MateriaBean */
    public MateriaBean() {
    }

    /**
     * @return the materiaId
     */
    public Integer getMateriaId() {
        return materiaId;
    }

    /**
     * @param materiaId the materiaId to set
     */
    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
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
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
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
}
