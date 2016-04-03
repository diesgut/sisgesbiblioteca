/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.CategoriaPaginationBean;
import com.sisgesbib.dao.beans.CategoriaBean;
import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class CategoriaDAO extends SqlMapClientDaoSupport{

    public List<CategoriaBean> getListaCategoria(CategoriaPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("categoria.getListaCategoria", bean);
    }

    public void insertCategory(CategoriaBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("categoria.insertCategory", bean);
    }

    public int validateCategory(CategoriaBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("categoria.validateCategory", bean).toString() );
    }

    public int validateChangeName(CategoriaBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("categoria.validateChangeName", bean).toString()  );
    }

    public void updateCategory(CategoriaBean bean) throws SQLException{
        getSqlMapClientTemplate().update("categoria.updateCategory", bean);
    }
    
    public Map getCategoryForCombo() throws SQLException{
        Map mapCategoria=getSqlMapClientTemplate().queryForMap("categoria.getCategoryForCombo", null, "nombre", "categoriaId");
        mapCategoria=GeneralUtil.sortMap(mapCategoria);
        return mapCategoria;
    }

}
