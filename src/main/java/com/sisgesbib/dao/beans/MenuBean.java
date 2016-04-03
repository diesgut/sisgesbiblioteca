/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author despantoso
 */
public class MenuBean implements Serializable{
    private Integer menuId;
    private Integer menuParentId;
    private String nombre;
    private String descripcion;
    private Integer orden;
    private Boolean estado;
    private String path;
    private String pathId;
    private boolean selected;
    private List<MenuBean> menusHijos;

    /** Creates a new instance of MenuBean */
    public MenuBean() {
    }

    @Override
    public String toString(){
        String  datosBean="El menuId : "+menuId + ", MenuParentId : "+menuParentId + ", Nombre : "+nombre+", Descripcion : "+descripcion+", Orden : "+orden+
                ", Estado : "+estado+", Path : "+path + ", El Path Id : "+pathId;
        return datosBean;
    }


    /**
     * @return the menuId
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * @return the menuParentId
     */
    public Integer getMenuParentId() {
        return menuParentId;
    }

    /**
     * @param menuParentId the menuParentId to set
     */
    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
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
     * @return the orden
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Integer orden) {
        this.orden = orden;
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
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the menusHijos
     */
    public List<MenuBean> getMenusHijos() {
        return menusHijos;
    }

    /**
     * @param menusHijos the menusHijos to set
     */
    public void setMenusHijos(List<MenuBean> menusHijos) {
        this.menusHijos = menusHijos;
    }

    /**
     * @return the pathId
     */
    public String getPathId() {
        return pathId;
    }

    /**
     * @param pathId the pathId to set
     */
    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
