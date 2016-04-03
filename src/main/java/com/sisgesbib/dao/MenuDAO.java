/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.dao.beans.MenuBean;
import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class MenuDAO extends SqlMapClientDaoSupport{

    public List<MenuBean> getListMenu() throws SQLException{
        return getSqlMapClientTemplate().queryForList("menu.getListMenu");
    }
    
    public void insertParentMenu(MenuBean menu) throws SQLException{
        getSqlMapClientTemplate().insert("menu.insertParentMenu", menu);
    }
    
    public int getMaxOrdenParentMenu() throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("menu.getMaxOrdenParentMenu").toString() );
    }
    
    public int validateNameParentMenu(MenuBean menu) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("menu.validateNameParentMenu", menu).toString() );
    }
    
    public Map getMenuParentForCombo() throws SQLException{
        Map combo= getSqlMapClientTemplate().queryForMap("menu.getMenuParentForCombo", null, "nombre", "menuId");
        return GeneralUtil.sortMap(combo);
    }
    
    public int validateNameChildMenu(MenuBean menu) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("menu.validateNameChildMenu", menu).toString() );
    }

    public int getMaxOrderChildMenu(MenuBean menu) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("menu.maxOrderChildMenu", menu).toString() );
    }
    
    public void insertChildMenu(MenuBean menu) throws SQLException{
        getSqlMapClientTemplate().insert("menu.insertChildMenu", menu);
    }
    
    public List<MenuBean>getListaMenusForAssign(int roleId) throws SQLException{
    return getSqlMapClientTemplate().queryForList("menu.getListaMenusForAssign", roleId);
    }
    
    public List<MenuBean>getListMenuesByRoleId(int roleId) throws SQLException{
        return getSqlMapClientTemplate().queryForList("menu.getListMenuesByRoleId",roleId);
    }
    
}
