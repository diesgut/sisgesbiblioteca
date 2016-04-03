/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.AutorPaginationBean;
import com.sisgesbib.dao.beans.AutorBean;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class AutorDAO extends SqlMapClientDaoSupport{

    public List<Map> getListaAuthors(AutorPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("autor.getListaAuthors", bean);
    }

    public void insertaAutor(AutorBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("autor.insertaAutor", bean);
    }

    public int validateFullNameAuthor(AutorBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("autor.validateFullNameAuthor", bean).toString() );
    }

    public int validateChangeFullName(AutorBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("autor.validateChangeFullName",bean).toString() );
    }

    public void updateAutor(AutorBean bean) throws SQLException{
        getSqlMapClientTemplate().update("autor.updateAutor",bean);
    }
    
    public List<AutorBean> getAuthorsByTexto(int textoId) throws SQLException{
        return getSqlMapClientTemplate().queryForList("autor.getAuthorsByTexto", textoId);
    }

    public String getAutoresPorTexto(int textoId) throws SQLException{
        return getSqlMapClientTemplate().queryForObject("autor.getAutoresPorTexto", textoId).toString();
    }
    

}
