/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao.beans;

import com.sisgesbib.util.GeneralUtil;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author despantoso
 */
public class UsuarioBean implements Serializable{
    
    private Integer usuarioId;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String email;
    private String login;
    private String contraseña;
    private Date fechaIns;
    private Date fechaAct;
    private Integer intInvalidos;
    private String estado;
    private Integer perfilId;
    private Boolean selected;
    private String keyAes=GeneralUtil.getProperty("keyAes");


    /** Creates a new instance of UsuarioBean */
    public UsuarioBean() {
    }

    @Override
    public String toString(){
        return "UsuarioId : "+getUsuarioId() + " - Login : "+getLogin()+
                " - Nombre : "+getNombre() + " - Ape Paterno : "+getApePaterno()+
                " - ApeMaterno : "+getApeMaterno() + " - Email : "+getEmail()+
                " - ContraseÃ±a "+getContraseña();
    }
    
    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the nombres
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the usuario
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
     * @return the status
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param status the status to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the perfilId
     */
    public Integer getPerfilId() {
        return perfilId;
    }

    /**
     * @param perfilId the perfilId to set
     */
    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    /**
     * @return the selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the keyAes
     */
    public String getKeyAes() {
        return keyAes;
    }

    /**
     * @param keyAes the keyAes to set
     */
    public void setKeyAes(String keyAes) {
        this.keyAes = keyAes;
    }

    /**
     * @return the intInvalidos
     */
    public Integer getIntInvalidos() {
        return intInvalidos;
    }

    /**
     * @param intInvalidos the intInvalidos to set
     */
    public void setIntInvalidos(Integer intInvalidos) {
        this.intInvalidos = intInvalidos;
    }

}
