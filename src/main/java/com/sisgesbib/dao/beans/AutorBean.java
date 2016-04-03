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
public class AutorBean implements Serializable{
    private Integer autorId;
    private String nombres;
    private String apellidos;
    private String email;
    private String web;
    private String sexo;
    private Boolean estado;
    private String paisId;
    private Date fechaIns;
    private Date fechaAct;
    private Integer usuarioIns;
    private Integer usuarioAct;

    /** Creates a new instance of AutorBean */
    public AutorBean() {
    }

    @Override
    public String toString(){
        return "autorId : "+getAutorId() + " - nombres : "+getNombres()+ " - apellidos : "+getApellidos()
                + " - email : "+ getEmail()+ " - web : "+getWeb() + " - sexo : "+getSexo()
                + " - estado : "+getEstado() + " - pais : "+getPaisId();
    }

    /**
     * @return the autorId
     */
    public Integer getAutorId() {
        return autorId;
    }

    /**
     * @param autorId the autorId to set
     */
    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
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
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
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
     * @return the paisId
     */
    public String getPaisId() {
        return paisId;
    }

    /**
     * @param paisId the paisId to set
     */
    public void setPaisId(String paisId) {
        this.paisId = paisId;
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

}
