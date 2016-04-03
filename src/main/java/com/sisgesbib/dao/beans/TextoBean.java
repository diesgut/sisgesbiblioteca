/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class TextoBean implements Serializable{

    private Integer textoId;
    private String codigoInterno;
    private String nombre;
    private String observacion;
    private Integer tomo;
    private Integer añoEdicion;
    private Integer edicion;
    private String dimension;
    private Integer numeroPaginas;
    private String ISBN;
    private Boolean estado;
    private Integer temaId;
    private Integer editorialId;
    private Integer categoriaId;
    private Integer materiaId;
    private Integer idiomaId;
    private Integer usuarioIns;
    private Integer usuarioAct;
    private Date fechaIns;
    private Date fechaAct;
    private String nomUsuIns;
    private String nomUsuAct;

    @Override
    public String toString() {
        return "TextoBean{" + "textoId=" + textoId + ", codigoInterno=" + codigoInterno + ", nombre=" + nombre + ", observacion=" + observacion + ", tomo=" + tomo + ", a\u00f1oEdicion=" + añoEdicion + ", edicion=" + edicion + ", dimension=" + dimension + ", numeroPaginas=" + numeroPaginas + ", ISBN=" + ISBN + ", estado=" + estado + ", temaId=" + temaId + ", editorialId=" + editorialId + ", categoriaId=" + categoriaId + ", materiaId=" + materiaId + ", idiomaId=" + idiomaId + ", usuarioIns=" + usuarioIns + ", usuarioAct=" + usuarioAct + ", fechaIns=" + fechaIns + ", fechaAct=" + fechaAct + ", nomUsuIns=" + nomUsuIns + ", nomUsuAct=" + nomUsuAct + '}';
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
     * @return the codigoInterno
     */
    public String getCodigoInterno() {
        return codigoInterno;
    }

    /**
     * @param codigoInterno the codigoInterno to set
     */
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
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
     * @return the tomo
     */
    public Integer getTomo() {
        return tomo;
    }

    /**
     * @param tomo the tomo to set
     */
    public void setTomo(Integer tomo) {
        this.tomo = tomo;
    }

    /**
     * @return the aÃ±oEdicion
     */
    public Integer getAñoEdicion() {
        return añoEdicion;
    }

    /**
     * @param aÃ±oEdicion the aÃ±oEdicion to set
     */
    public void setAñoEdicion(Integer añoEdicion) {
        this.añoEdicion = añoEdicion;
    }

    /**
     * @return the edicion
     */
    public Integer getEdicion() {
        return edicion;
    }

    /**
     * @param edicion the edicion to set
     */
    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    /**
     * @return the dimension
     */
    public String getDimension() {
        return dimension;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the numeroPaginas
     */
    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * @param numeroPaginas the numeroPaginas to set
     */
    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the estado
     */
    public Boolean getEstado() {
        if(estado==null){
            estado=true;
        }
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
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
     * @return the categoriaId
     */
    public Integer getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
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
     * @return the idiomaId
     */
    public Integer getIdiomaId() {
        return idiomaId;
    }

    /**
     * @param idiomaId the idiomaId to set
     */
    public void setIdiomaId(Integer idiomaId) {
        this.idiomaId = idiomaId;
    }

    /**
     * @return the usuariosIns
     */
    public Integer getUsuarioIns() {
        return usuarioIns;
    }

    /**
     * @param usuariosIns the usuariosIns to set
     */
    public void setUsuarioIns(Integer usuariosIns) {
        this.usuarioIns = usuariosIns;
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
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

   
}
