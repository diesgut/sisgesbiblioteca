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
public class PaisDAO extends SqlMapClientDaoSupport{

    public Map getCountryForCombo() throws SQLException{
        Map mapCountryCombo=getSqlMapClientTemplate().queryForMap("pais.getCountryForCombo", null,"nombre", "paisId");
        return GeneralUtil.sortMap(mapCountryCombo);
    }

}
