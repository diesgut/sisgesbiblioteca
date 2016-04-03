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
public class EjemplarBean implements Serializable{

    private Integer ejemplarId;
    private String estado;
    private String condicion;
    private String tipoPrestamo;
    private String procedencia;
    private String tipo;
    private String ubicacion;
    private String stand;
    private String columna;
    private String fila;
    private String moneda;
    private Double precio;
    private String observacion;
    private Integer usuarioIns;
    private Integer usuarioAct;
    private Date fechaAdquisicion;
    private Date fechaIns;
    private Date fechaAct;
    private String nomUsuIns;
    private String nomUsuAct;
    private Integer sedeId;
    private Integer textoId;
    private Integer especialidadId;

    /** Creates a new instance of EjemplarBean */
    public EjemplarBean() {
    }

    @Override
    public String toString() {
        return "EjemplarBean{" + "ejemplarId=" + ejemplarId + ", estado=" + estado + ", condicion=" + condicion + ", tipoPrestamo=" + tipoPrestamo + ", procedencia=" + procedencia + ", tipo=" + tipo + ", ubicacion=" + ubicacion + ", stand=" + stand + ", columna=" + columna + ", fila=" + fila + ", moneda=" + moneda + ", precio=" + precio + ", observacion=" + observacion + ", usuarioIns=" + usuarioIns + ", usuarioAct=" + usuarioAct + ", fechaAdquisicion=" + fechaAdquisicion + ", fechaIns=" + fechaIns + ", fechaAct=" + fechaAct + ", nomUsuIns=" + nomUsuIns + ", nomUsuAct=" + nomUsuAct + ", sedeId=" + sedeId + ", textoId=" + textoId + ", especialidadId=" + especialidadId + '}';
    }

    /**
     * @return the ejemplarId
     */
    public Integer getEjemplarId() {
        return ejemplarId;
    }

    /**
     * @param ejemplarId the ejemplarId to set
     */
    public void setEjemplarId(Integer ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the condicion
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the tipoPrestamo
     */
    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * @param tipoPrestamo the tipoPrestamo to set
     */
    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    /**
     * @return the procedencia
     */
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia the procedencia to set
     */
    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
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
     * @return the ubicacion
     */
    public String getUbicacion() {
        if (ubicacion==null) {
            ubicacion = stand + "/" + columna + "/" + fila;
        }
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        String[] argUbicacion=ubicacion.split("/");
        stand=argUbicacion[0];
        columna=argUbicacion[1];
        fila=argUbicacion[2];
        this.ubicacion = ubicacion;
    }

    /**
     * @return the stand
     */
    public String getStand() {
        return stand;
    }

    /**
     * @param stand the stand to set
     */
    public void setStand(String stand) {
        this.stand = stand;
    }

    /**
     * @return the columna
     */
    public String getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(String columna) {
        this.columna = columna;
    }

    /**
     * @return the fila
     */
    public String getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(String fila) {
        this.fila = fila;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
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
     * @return the fechaAdquisicion
     */
    public Date getFechaAdquisicion() {
        if (fechaAdquisicion == null) {
            fechaAdquisicion = new Date();
        }
        return fechaAdquisicion;
    }

    /**
     * @param fechaAdquisicion the fechaAdquisicion to set
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
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
}
