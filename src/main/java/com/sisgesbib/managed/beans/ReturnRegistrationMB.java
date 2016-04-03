/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.PrestamoPaginationBean;
import com.sisgesbib.dao.EjemplarDAO;
import com.sisgesbib.dao.PrestamoDAO;
import com.sisgesbib.dao.beans.EjemplarBean;
import com.sisgesbib.dao.beans.PrestamoBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Diego
 */
public class ReturnRegistrationMB {

    public PrestamoDAO prestamoDAO = null;
    private EjemplarDAO ejemplarDAO = null;
    private PrestamoBean prestamoBean = null;
    private String initPage;
    private EjemplarBean ejemplarBean = null;

    /** Creates a new instance of ReturnRegistrationMB */
    public ReturnRegistrationMB() {
        try {
            prestamoDAO = DAOFactory.getPrestamoDAO();
            ejemplarDAO = DAOFactory.getEjemplarDAO();
            if (GeneralUtil.getViewMap("prestamoPaginationBean") == null) {
                PrestamoPaginationBean presPagBean = new PrestamoPaginationBean();
                presPagBean.setEstado("C");
                GeneralUtil.putViewMap("prestamoPaginationBean", presPagBean);
                listaPrestamos();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaPrestamos() throws SQLException {
        PrestamoPaginationBean presPagBean = (PrestamoPaginationBean) GeneralUtil.getViewMap("prestamoPaginationBean");
        List<Map> listaPrestamos = prestamoDAO.getListaPrestamos(presPagBean);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de prestamos es ; " + listaPrestamos.size());
        presPagBean.setListaPrestamos(listaPrestamos);
        GeneralUtil.putViewMap("prestamoPaginationBean", presPagBean);
    }

    public void renewLoan(ActionEvent event) {
        try {
            SimpleDateFormat sdf = GeneralUtil.sdfParseLong;
            Map loanSelected = (Map) event.getComponent().getAttributes().get("selectedLoan");

            String tipoLector = loanSelected.get("tipoLector").toString();

            Date fechaIns = null;
            Date fechaIni = null;
            Date fechaFin = null;

            fechaIns = sdf.parse(loanSelected.get("fechaIns").toString());
            fechaIni = sdf.parse(loanSelected.get("fechaInicio").toString());
            fechaFin = sdf.parse(loanSelected.get("fechaFin").toString());

            loanSelected.put("fechaIns", null);
            loanSelected.put("fechaInicio", null);
            loanSelected.put("fechaFin", null);
//            System.out.println("fechains " + fechaIns);
//            System.out.println("fechaIni " + fechaIni);
//            System.out.println("fechaFin " + fechaFin);

            prestamoBean = new PrestamoBean();
            BeanUtils.populate(prestamoBean, loanSelected);
            loanSelected.put("fechaIns", sdf.format(fechaIns));
            loanSelected.put("fechaInicio", sdf.format(fechaIni));
            loanSelected.put("fechaFin", sdf.format(fechaFin));


            //  GeneralUtil.putRequestMap("prestamoBean", prestamoBean);

            if (tipoLector.equals("E")) {
                prestamoDAO.renewLoanStudent(prestamoBean);
                Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "Prestamo tipo estudiante renovado satisfatoriamente");
            } else if (tipoLector.equals("P")) {
                prestamoDAO.renewLoanPersAdm(prestamoBean);
                Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "Prestamo tipo pers. administrativo renovado satisfatoriamente");
            }
            listaPrestamos();
            GeneralUtil.putRequestMap("successRenew", true);
        } catch (Exception e) {
            GeneralUtil.putRequestMap("successRenew", false);
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void returnBook(ActionEvent event) {
        try {
            SimpleDateFormat sdf = GeneralUtil.sdfParseLong;
            Map loanSelected = (Map) event.getComponent().getAttributes().get("selectedLoan");

            Date fechaIns = null;
            Date fechaIni = null;
            Date fechaFin = null;

            fechaIns = sdf.parse(loanSelected.get("fechaIns").toString());
            fechaIni = sdf.parse(loanSelected.get("fechaInicio").toString());
            fechaFin = sdf.parse(loanSelected.get("fechaFin").toString());

            loanSelected.put("fechaIns", null);
            loanSelected.put("fechaInicio", null);
            loanSelected.put("fechaFin", null);
//            System.out.println("fechains " + fechaIns);
//            System.out.println("fechaIni " + fechaIni);
//            System.out.println("fechaFin " + fechaFin);

            prestamoBean = new PrestamoBean();
            BeanUtils.populate(prestamoBean, loanSelected);
            loanSelected.put("fechaIns", sdf.format(fechaIns));
            loanSelected.put("fechaInicio", sdf.format(fechaIni));
            loanSelected.put("fechaFin", sdf.format(fechaFin));

            GeneralUtil.putRequestMap("prestamoBean", prestamoBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void returnBook() {
        try {
            prestamoBean = (PrestamoBean) GeneralUtil.getRequestMap("prestamoBean");
            ejemplarBean = (EjemplarBean) GeneralUtil.getRequestMap("ejemplarBean");

            prestamoBean.setEstado("F");
            ejemplarBean.setEjemplarId(prestamoBean.getEjemplarId());
            ejemplarBean.setEstado("D");

            prestamoDAO.updateLoanState(prestamoBean);
            ejemplarDAO.updateExemplaryStateAndCond(ejemplarBean);
            Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                    + "Prestamo finalizado correctamente");

            listaPrestamos();

//            System.out.println("prestamo");
//            System.out.println("prestamoid "+prestamoBean.getPrestamoId());
//            System.out.println("estado "+prestamoBean.getEstado());
//            System.out.println("ejemplar");
//            System.out.println("ejemplarId "+ejemplarBean.getEjemplarId());
//            System.out.println("estado "+ejemplarBean.getEstado());
//            System.out.println("condicion "+ejemplarBean.getCondicion());

            //     prestamoDAO.updateLoanState(prestamoBean);

//            System.out.println("el prestamo es "+prestamoBean.getPrestamoId());
//            System.out.println("el ejemplar id es "+prestamoBean.getEjemplarId());
//            System.out.println("la condicion es "+ejemplarBean.getCondicion());
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    /**
     * @return the initPage
     */
    public String getInitPage() {
        return initPage;
    }

    /**
     * @param initPage the initPage to set
     */
    public void setInitPage(String initPage) {
        this.initPage = initPage;
    }
}
