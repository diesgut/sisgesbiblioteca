/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.LectorPaginacionBean;
import com.sisgesbib.dao.beans.LectorBean;
import com.sisgesbib.dao.beans.LectorBean;
import java.sql.SQLException;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class LectorDAO extends SqlMapClientDaoSupport{

    public List<LectorBean> getListaLectores(LectorPaginacionBean bean) throws SQLException{
        return (List<LectorBean>) getSqlMapClientTemplate().queryForList("lector.getListaLectores", bean);
    }
    
    public int validaCodigoLector(LectorBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("lector.validaCodigoLector", bean).toString() );
    }
    
    public void insertaLector(LectorBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("lector.insertaLector", bean);
    }
    
    public int validaChangeCodigoLector(LectorBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("lector.validaChangeCodigoLector", bean).toString() );
    }
    
    public void updateLector(LectorBean bean) throws SQLException{
        getSqlMapClientTemplate().update("lector.updateLector", bean);
    }
    
    public LectorBean findOneReader(LectorBean bean) throws SQLException{
        return (LectorBean)getSqlMapClientTemplate().queryForObject("lector.findOneReader",bean);
    }
    
   
}
