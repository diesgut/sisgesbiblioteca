/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.TextoPaginationBean;
import com.sisgesbib.dao.beans.TextoBean;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class TextoDAO extends SqlMapClientDaoSupport{

    public List<Map> getListaTextos(TextoPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("texto.getListaTextos", bean);
    }
    
    
    public List<Map> getListaTextoForSearch(TextoPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("texto.getListaTextoForSearch", bean);
    }
    
    public void insertText(TextoBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("texto.insertText",bean);
    }
    
    public int validaCodIntText(TextoBean bean) throws  SQLException {
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("texto.validaCodIntText", bean).toString() );
    }
    
    public int validaNameText(TextoBean bean) throws  SQLException {
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("texto.validaNameText", bean).toString() );
    }
    
    public int validaISBNText(TextoBean bean) throws  SQLException {
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("texto.validaISBNText", bean).toString() );
    }

    public int validaUpdateCodIntText(TextoBean bean) throws  SQLException {
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("texto.validaUpdateCodIntText", bean).toString() );
    }
    
    public int validaUpdateNameText(TextoBean bean) throws  SQLException {
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("texto.validaUpdateNameText", bean).toString() );
    }
    public int validaUpdateISBNText(TextoBean bean) throws SQLException{
         return Integer.parseInt( getSqlMapClientTemplate().queryForObject("texto.validaUpdateISBNText", bean).toString() );
    }
    
    public void insertTextoAutor(Map params) throws SQLException{
        getSqlMapClientTemplate().insert("texto.insertTextoAutor", params);
    }
    
    public List<Map> getListTextForMatter(Integer sedeId, Integer materiaId) throws SQLException{
        Map<String,Integer> params=new HashMap<String, Integer>();
        params.put("sedeId", sedeId);
        params.put("materiaId", materiaId);
        return getSqlMapClientTemplate().queryForList("texto.getListTextForMatter", params);
    }
    
    public void updateTexto(TextoBean textoBean) throws SQLException{
        getSqlMapClientTemplate().update("texto.updateTexto", textoBean);
    }
    
    
    public void deleteTextoAutor(int textoId){
        getSqlMapClientTemplate().delete("texto.deleteTextoAutor", textoId);
    }
    
}
