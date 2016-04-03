/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class EmpresaDAO extends SqlMapClientDaoSupport{

    public Map getCompanyAssignedForUser(int userId) throws SQLException{
        Map mapita=getSqlMapClientTemplate().queryForMap("empresa.getCompanyAssignedForUser", userId, "nombre", "empresaId");
        return GeneralUtil.sortMap(mapita);
    }
    
    public Map getMapCompany() throws SQLException{
        Map mapita=getSqlMapClientTemplate().queryForMap("empresa.getMapCompany", null, "nombre", "empresaId");
        return GeneralUtil.sortMap(mapita);
    }
    
    public int getEmpresaBySede(int sedeId) throws SQLException{
        return Integer.parseInt(getSqlMapClientTemplate().queryForObject("empresa.getEmpresaBySede", sedeId).toString());
    }
}
