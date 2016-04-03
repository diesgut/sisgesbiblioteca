/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao.beans;

import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class SesionUsuarioBean implements Serializable{
    //Datos de usuario
    private Integer usuarioId;
    private String login;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    //Datos de Empresa
    private Integer empresaId;
    private String nombreEmpresa;
    //Datos Sede
    private Integer sedeId;
    private String nombreSede;
    private String direccionSede;
    //Datos del Perfil
    private Integer perfilId;
    private String nombrePerfil;
    
    
    
    /** Creates a new instance of SesionUsuarioBean */
    public SesionUsuarioBean() {
    }
    
    @Override
    public String toString(){
        return "usuarioId : "+usuarioId + " - nombre : "+nombre+
                " - apePaterno : "+apePaterno+" - apeMaterno : "+apeMaterno+
                " - empresaId : "+empresaId + " - nombreEmpresa : "+nombreEmpresa+
                " - sedeId : "+sedeId + " - nombreSede : "+nombreSede+
                " - perFilId : "+perfilId+ " - nombrePerfil : "+nombrePerfil;
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
     * @return the empresaId
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId the empresaId to set
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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
     * @return the nombreSede
     */
    public String getNombreSede() {
        return nombreSede;
    }

    /**
     * @param nombreSede the nombreSede to set
     */
    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
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
     * @return the nombrePerfil
     */
    public String getNombrePerfil() {
        return nombrePerfil;
    }

    /**
     * @param nombrePerfil the nombrePerfil to set
     */
    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the direccionSede
     */
    public String getDireccionSede() {
        return direccionSede;
    }

    /**
     * @param direccionSede the direccionSede to set
     */
    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }
    

}
