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
public class LectorBean implements Serializable{

    private Integer lectorId;
    private String codigo;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String sexo;
    private String tipo;
    private Date fechaIns;
    private Date fechaAct;
    private Integer usuarioIns;
    private Integer usuarioAct;
    private Integer sedeId;
    private Integer especialidadId;
    private String nomUsuIns;
    private String nomUsuAct;
    private Boolean estado;
    /** Creates a new instance of LectorBean */
    public LectorBean() {
    }

    @Override
    public String toString() {
        return "LectorBean{" + "lectorId=" + lectorId + ", codigo=" + codigo + ", nombres=" + nombres + ", apePaterno=" + apePaterno + ", apeMaterno=" + apeMaterno + ", sexo=" + sexo + ", tipo=" + tipo + ", fechaIns=" + fechaIns + ", fechaAct=" + fechaAct + ", usuarioIns=" + usuarioIns + ", usuarioAct=" + usuarioAct + ", sedeId=" + sedeId + ", especialidadId=" + especialidadId + ", nomUsuIns=" + nomUsuIns + ", nomUsuAct=" + nomUsuAct + '}';
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the apePaterno
     */
    public String getApePaterno() {
        return apePaterno;
    }

    /**
     * @param apePaterno the apePaterno to set
     */
    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    /**
     * @return the apeMaterno
     */
    public String getApeMaterno() {
        return apeMaterno;
    }

    /**
     * @param apeMaterno the apeMaterno to set
     */
    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    /**
     * @return the especialidadId
     */
    public Integer getEspecialidadId() {
        return especialidadId;
    }

    /**
     * @param especialidadId the especialidadId to set
     */
    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    /**
     * @return the nomUsuIns
     */
    public String getNomUsuIns() {
        return nomUsuIns;
    }

    /**
     * @param nomUsuIns the nomUsuIns to set
     */
    public void setNomUsuIns(String nomUsuIns) {
        this.nomUsuIns = nomUsuIns;
    }

    /**
     * @return the nomUsuAct
     */
    public String getNomUsuAct() {
        return nomUsuAct;
    }

    /**
     * @param nomUsuAct the nomUsuAct to set
     */
    public void setNomUsuAct(String nomUsuAct) {
        this.nomUsuAct = nomUsuAct;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
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
    
    
}
