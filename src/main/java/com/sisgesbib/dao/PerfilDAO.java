/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao;

import com.sisgesbib.dao.beans.PerfilBean;
import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class PerfilDAO extends SqlMapClientDaoSupport {

    public List<PerfilBean> getListaPerfil() throws SQLException {
        return getSqlMapClientTemplate().queryForList("perfil.getListaPerfil");
    }

    public int validateName(PerfilBean bean) throws SQLException {
        return Integer.parseInt(getSqlMapClientTemplate().queryForObject("perfil.validateName", bean).toString());
    }

    public void insertNewPerfil(PerfilBean bean) throws SQLException {
        getSqlMapClientTemplate().insert("perfil.insertNewPerfil", bean);
    }

    public int validateChangeName(PerfilBean bean) throws SQLException {
        return Integer.parseInt(getSqlMapClientTemplate().queryForObject("perfil.validateChangeName", bean).toString());
    }

    public void updatePerfil(PerfilBean bean) throws SQLException {
        getSqlMapClientTemplate().insert("perfil.updatePerfil", bean);
    }

    public List<Map> getRolesAssignedForUserAndCompany(int empresaId, int usuarioId) throws SQLException {
        Map<String,Integer> params=new HashMap<String, Integer>();
        params.put("empresaId", empresaId);
        params.put("usuarioId", usuarioId);
        return (List<Map>) getSqlMapClientTemplate().queryForList("perfil.getRolesAssignedForUserAndCompany", params);
    }
    
    public Map getMapPerfiles() throws SQLException{
        Map returnMap=getSqlMapClientTemplate().queryForMap("perfil.getMapPerfiles", null, "nombre", "perfilId");
        return GeneralUtil.sortMap(returnMap);
    }
    
    public void deleteUsuarioSedePerfilByUserAndSedes(Map params) throws SQLException{
        getSqlMapClientTemplate().delete("perfil.deleteUsuarioSedePerfilByUserAndSedes", params);
    }
    
    public void multiInsertUsuarioSedePerfil(Map params) throws SQLException{
        getSqlMapClientTemplate().insert("perfil.multiInsertUsuarioSedePerfil", params);
    }
    
    
}
