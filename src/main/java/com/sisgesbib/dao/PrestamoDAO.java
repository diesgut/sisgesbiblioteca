/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisgesbib.dao;

import com.sisgesbib.beans.filters.PrestamoPaginationBean;
import com.sisgesbib.dao.beans.PrestamoBean;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author despantoso
 */
public class PrestamoDAO extends SqlMapClientDaoSupport{

    public void insertRomeLoan(PrestamoBean prestamoBean) throws SQLException{
        getSqlMapClientTemplate().insert("prestamo.insertRomeLoan", prestamoBean);
    }
     
    public void insertStudentHomeLoan(PrestamoBean prestamoBean) throws SQLException{
        getSqlMapClientTemplate().insert("prestamo.insertStudentHomeLoan", prestamoBean);
    }
    
    public void insertPerAdmHomeLoan(PrestamoBean prestamoBean) throws SQLException{
        getSqlMapClientTemplate().insert("prestamo.insertPerAdmHomeLoan", prestamoBean);
    }
    
    public int getCountPrestamosEnCursoPorLectorId(int lectorId) throws SQLException{
        return Integer.parseInt( getSqlMapClientTemplate().queryForObject("prestamo.getCountPrestamosEnCursoPorLectorId", lectorId).toString() );
    }
    
    public List<Map> getListaPrestamos(PrestamoPaginationBean bean) throws SQLException{
        return getSqlMapClientTemplate().queryForList("prestamo.getListaPrestamos", bean);
    }    
    
    public void updateLoanState(PrestamoBean prestamoBean) throws SQLException{
        getSqlMapClientTemplate().update("prestamo.updateLoanState", prestamoBean);
    }
 
    public void renewLoanStudent(PrestamoBean prestamoBean) throws SQLException{
        getSqlMapClientTemplate().update("prestamo.renewLoanStudent", prestamoBean);
    }
    
    public void renewLoanPersAdm(PrestamoBean prestamoBean) throws SQLException{
        getSqlMapClientTemplate().update("prestamo.renewLoanPersAdm", prestamoBean);
    }
    
}
