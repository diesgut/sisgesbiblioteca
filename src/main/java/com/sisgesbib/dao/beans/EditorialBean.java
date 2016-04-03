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
public class EditorialBean implements Serializable{

    private Integer editorialId;
    private String nombre;
    private String direccion;
    private String email;
    private String web;
    private String telefono;
    private String movil;
    private Boolean estado;
    private Integer ciudadId;
    private Date fechaIns;
    private Date fechaAct;
    private Integer usuarioIns;
    private Integer usuarioAct;
    
    private String nomUsuIns;
    private String nomUsuAct;
    private String nombrePais;
    private String nombreCiudad;
    

    /** Creates a new instance of EditorialBean */
    public EditorialBean() {
    }

    @Override
    public String toString() {
        return "PublishingBean{" + "editorialId=" + editorialId + ", nomnre=" + nombre + ", direccion=" + direccion + ", email=" + email + ", web=" + web + ", telefono=" + telefono + ", movil=" + movil + ", estado=" + estado + ", ciudadId=" + ciudadId + ", fechaIns=" + fechaIns + ", fechaAct=" + fechaAct + ", usuarioIns=" + usuarioIns + ", usuarioAct=" + usuarioAct + '}';
    }

    /**
     * @return the editorialId
     */
    public Integer getEditorialId() {
        return editorialId;
    }

    /**
     * @param editorialId the editorialId to set
     */
    public void setEditorialId(Integer editorialId) {
        this.editorialId = editorialId;
    }

    /**
     * @return the nomnre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nomnre the nomnre to set
     */
    public void setNombre(String nomnre) {
        this.nombre = nomnre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the movil
     */
    public String getMovil() {
        return movil;
    }

    /**
     * @param movil the movil to set
     */
    public void setMovil(String movil) {
        this.movil = movil;
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
     * @return the ciudadId
     */
    public Integer getCiudadId() {
        return ciudadId;
    }

    /**
     * @param ciudadId the ciudadId to set
     */
    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
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
     * @return the nombrePais
     */
    public String getNombrePais() {
        return nombrePais;
    }

    /**
     * @param nombrePais the nombrePais to set
     */
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    /**
     * @return the nombreCiudad
     */
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    /**
     * @param nombreCiudad the nombreCiudad to set
     */
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }


}
