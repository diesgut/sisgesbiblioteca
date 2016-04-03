/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao.beans;

import java.io.Serializable;


/**
 *
 * @author despantoso
 */
public class PerfilBean implements Serializable{
    private Integer perfilId;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    
    /** Creates a new instance of PerfilBean */
    public PerfilBean() {
    }


    @Override
    public String toString(){
        String dataBean="PerfilId : "+perfilId+", Nombre : "+nombre+", Descripcion : "+descripcion + ", Estado : "+estado;
        return dataBean;
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


}
