/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class SedeDAO extends  SqlMapClientDaoSupport{

    public Map getSedeByCompanyAndUser(int userId,int companyId) throws SQLException{
        Map params=new HashMap();
        params.put("userId", userId);
        params.put("companyId", companyId);
        Map mapita=getSqlMapClientTemplate().queryForMap("sede.getSedeByCompanyAndUser", params, "nombre","sedeId");
        return GeneralUtil.sortHashMap(mapita);
    }
    
    public Map getSedePorEmpresa(int empresaId) throws SQLException{
        Map mapita=getSqlMapClientTemplate().queryForMap("sede.getSedePorEmpresa", empresaId, "nombre","sedeId");
        return GeneralUtil.sortHashMap(mapita);
    }

}
