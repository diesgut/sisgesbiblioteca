/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.SolicitudPaginacionBean;
import com.sisgesbib.dao.beans.SolicitudBean;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author Diego
 */
public class SolicitudDAO extends SqlMapClientDaoSupport{
    
    public void insertaSolicitud(SolicitudBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("solicitud.insertaSolicitud", bean);
    }
    
    public int cantSolicitudesPend(int lectorId) throws SQLException{
        return Integer.parseInt(getSqlMapClientTemplate().queryForObject("solicitud.cantSolicitudesPend", lectorId).toString());
    }
    
    public List<Map> getListaSolicitudes(SolicitudPaginacionBean pgnBean) throws SQLException{
        return (List<Map>)getSqlMapClientTemplate().queryForList("solicitud.getListaSolicitudes", pgnBean);
    }
    
    public void updateOrderState(SolicitudBean orderBean) throws SQLException{
        getSqlMapClientTemplate().update("solicitud.updateOrderState", orderBean);
    }
    
}
