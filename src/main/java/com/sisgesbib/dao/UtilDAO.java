/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao;

import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author Diego
 */
public class UtilDAO extends SqlMapClientDaoSupport {

    public void deletePerfilMenu(int roleId) throws SQLException {
        getSqlMapClientTemplate().delete("perfilMenu.deletePerfilMenu", roleId);
    }

    public void insertPerfilMenu(List<Map> listaInsertar) throws SQLException {
        getSqlMapClientTemplate().insert("perfilMenu.insertPerfilMenu", listaInsertar);
    }

    public Map getPaisForCombo() throws SQLException {
        Map mapResult = getSqlMapClientTemplate().queryForMap("util.getPaisForCombo", null, "nombre", "paisId");
        return GeneralUtil.sortMap(mapResult);
    }

    public Map getCiudadPorPais(String countryId) throws SQLException {
        Map mapResult = getSqlMapClientTemplate().queryForMap("util.getCiudadPorPais", countryId, "nombre", "ciudadId");
        return GeneralUtil.sortMap(mapResult);
    }
    
    public String getCountryByCity(int cityId) throws SQLException{
        String retorno=getSqlMapClientTemplate().queryForObject("pais.getCountryByCity", cityId).toString();
        return retorno;
    }
    
    public Map getLanguageForCombo() throws SQLException{
        Map mapLanguage=getSqlMapClientTemplate().queryForMap("util.getLanguageForCombo", null, "nombre","idiomaId");
        mapLanguage=GeneralUtil.sortMap(mapLanguage);
        return mapLanguage;
    }
}
