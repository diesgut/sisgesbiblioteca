/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.EjemplarPaginationBean;
import com.sisgesbib.dao.beans.EjemplarBean;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author Diego
 */
public class EjemplarDAO extends SqlMapClientDaoSupport{
    
    public void insertaEjemplar(EjemplarBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("ejemplar.insertaEjemplar", bean);
    }
    
    public List<Map> getListaEjemplarPorTexto(EjemplarPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("ejemplar.getListaEjemplarPorTexto", bean);
    }
    
    public void updateEjemplar(EjemplarBean bean) throws SQLException{
        getSqlMapClientTemplate().update("ejemplar.updateEjemplar", bean);
    }
    
    public void updateExemplaryState(EjemplarBean bean) throws SQLException{
        getSqlMapClientTemplate().update("ejemplar.updateExemplaryState", bean);
    }
    
    public void updateExemplaryStateAndCond(EjemplarBean ejemplarBean) throws SQLException{
        getSqlMapClientTemplate().update("ejemplar.updateExemplaryStateAndCond", ejemplarBean);
    }
    
}
