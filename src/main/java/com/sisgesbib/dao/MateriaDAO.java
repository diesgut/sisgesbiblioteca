/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.MateriaPaginationBean;
import com.sisgesbib.dao.beans.MateriaBean;
import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class MateriaDAO extends SqlMapClientDaoSupport{

    public List<MateriaBean> getListaMateria(MateriaPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("materia.getListaMateria", bean);
    }

    public int validateName(MateriaBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("materia.validateName", bean).toString() );
    }

    public void insertMatter(MateriaBean bean) throws SQLException{
        getSqlMapClientTemplate().insert("materia.insertMatter", bean);
    }

    public int validateChangeName(MateriaBean bean) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("materia.validateChangeName", bean).toString() );
    }

    public void updateMatter(MateriaBean bean) throws SQLException{
        getSqlMapClientTemplate().update("materia.updateMatter",bean);
    }
    
    public Map getMattersForCombo() throws SQLException{
        Map mapMateria= getSqlMapClientTemplate().queryForMap("materia.getMattersForCombo", null, "nombre", "materiaId");
        return GeneralUtil.sortMap(mapMateria);
    }
    
    public List<Map> getLstMatterResume(Integer sedeId) throws SQLException{
        return getSqlMapClientTemplate().queryForList("materia.getLstMatterResume", sedeId);
    }
    
    public Map totalsMatterResume(Integer sedeId) throws SQLException{
        return (Map)getSqlMapClientTemplate().queryForObject("materia.totalsMatterResume", sedeId);
    }

}
