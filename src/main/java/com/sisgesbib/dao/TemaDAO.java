/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.TemaPaginationBean;
import com.sisgesbib.dao.beans.TemaBean;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author Diego
 */
public class TemaDAO extends SqlMapClientDaoSupport{
    
    public List<TemaBean> getListaTheme(TemaPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("tema.getListaTheme",bean);
    }
    
    public int validateNombre(TemaBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("tema.validateNombre",bean).toString() );
    }
    
    public void insertTheme(TemaBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("tema.insertTheme",bean);
    }

    public int validateChangedName(TemaBean bean) throws SQLException{
       return Integer.parseInt( getSqlMapClientTemplate().queryForObject("tema.validateChangedName", bean).toString() );
    }

    public void updateTheme(TemaBean bean) throws SQLException{
        getSqlMapClientTemplate().update("tema.updateTheme",bean);
    }
    
    
}
