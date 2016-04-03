package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.UsuarioPaginationBean;
import com.sisgesbib.dao.beans.SesionUsuarioBean;
import com.sisgesbib.dao.beans.UsuarioBean;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UsuarioDAO extends SqlMapClientDaoSupport {

    public UsuarioBean loginUsuario(UsuarioBean user) throws SQLException {
        return (UsuarioBean) getSqlMapClientTemplate().queryForObject("usuario.loginUsuario", user);
    }

    public UsuarioBean validaLogin(UsuarioBean user) throws SQLException {
        return (UsuarioBean) getSqlMapClientTemplate().queryForObject("usuario.validaLogin", user);
    }

    public void invalidAttemps(UsuarioBean user) throws SQLException {
        getSqlMapClientTemplate().update("usuario.invalidAttemps", user);
    }

    public void usuarioUpdateStatus(UsuarioBean user) throws SQLException {
        getSqlMapClientTemplate().update("usuario.usuarioUpdateStatus", user);
    }

    public SesionUsuarioBean getInfoForUserSession(Integer userId, Integer sedeId) throws SQLException {
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("sedeId", sedeId);
        return (SesionUsuarioBean) getSqlMapClientTemplate().queryForObject("usuario.getInfoForUserSession", params);
    }

    public List<UsuarioBean> getListaUsers(UsuarioPaginationBean bean) throws SQLException {
        return (List<UsuarioBean>) getSqlMapClientTemplate().queryForList("usuario.getListaUsers", bean);
    }

    public int usernameValidate(UsuarioBean bean) throws SQLException {
        return Integer.parseInt(getSqlMapClientTemplate().queryForObject("usuario.usernameValidate",bean).toString());
    }

    public void insertaUsuario(UsuarioBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("usuario.insertaUsuario",bean);
    }
    
    public void updateUsuario(UsuarioBean bean) throws SQLException{
        getSqlMapClientTemplate().update("usuario.updateUsuario",bean);
    }
    
    public int validateLoginChange(UsuarioBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate(). 
                queryForObject("usuario.validateLoginChange",bean).toString());
    }
    
    public void changeUserPassword(UsuarioBean bean) throws SQLException{
        getSqlMapClientTemplate().update("usuario.changeUserPassword",bean);
    }
    
}
