/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.EditorialPaginationBean;
import com.sisgesbib.dao.beans.EditorialBean;
import java.sql.SQLException;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author Diego
 */
public class EditorialDAO extends SqlMapClientDaoSupport{
    
    public List<EditorialBean> getListaEditorial(EditorialPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("editorial.getListaEditorial", bean);
    }
    
    public void insertaEditorial(EditorialBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("editorial.insertaEditorial", bean);
    }
    
    public void actualizaEditorial(EditorialBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("editorial.actualizaEditorial", bean);
    }
    
    public void updateEditorial(EditorialBean bean) throws SQLException{
        getSqlMapClientTemplate().update("editorial.updateEditorial", bean);
    }
    
    public int validaNombre(EditorialBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("editorial.validaNombre", bean).toString() );
    }
    
    public int validaCambioNombre(EditorialBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("editorial.validaCambioNombre", bean).toString() );
    }
    
}
