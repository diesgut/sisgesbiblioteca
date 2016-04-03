/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.EjemplarPaginationBean;
import com.sisgesbib.beans.filters.SolicitudPaginacionBean;
import com.sisgesbib.dao.EjemplarDAO;
import com.sisgesbib.dao.LectorDAO;
import com.sisgesbib.dao.PrestamoDAO;
import com.sisgesbib.dao.SolicitudDAO;
import com.sisgesbib.dao.beans.EjemplarBean;
import com.sisgesbib.dao.beans.LectorBean;
import com.sisgesbib.dao.beans.PrestamoBean;
import com.sisgesbib.dao.beans.SolicitudBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Diego
 */
public class LoanRegisterMB {

    SolicitudDAO solicitudDAO = null;
    SolicitudBean solicitudBean = null;
    EjemplarDAO ejemplarDAO = null;
    PrestamoDAO prestamoDAO = null;
    LectorDAO lectorDAO = null;
    PrestamoBean prestamoBean = null;
    EjemplarBean ejemplarBean = null;
    LectorBean lectorBean = null;
    private String initPage;

    /** Creates a new instance of LoanRegisterMB */
    public LoanRegisterMB() {
        solicitudDAO = DAOFactory.getSolicitudDAO();
        ejemplarDAO = DAOFactory.getEjemplarDAO();
        prestamoDAO = DAOFactory.getPrestamoDAO();
        lectorDAO = DAOFactory.getLectorDAO();
        if (GeneralUtil.getViewMap("solicitudPaginacionBean") == null) {
            try {
                SolicitudPaginacionBean pgnBean = new SolicitudPaginacionBean();
                pgnBean.setEstado("E");
                GeneralUtil.putViewMap("solicitudPaginacionBean", pgnBean);
                listaSolicitudes();
            } catch (Exception e) {
                Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
            }
        }
    }

    public void listaSolicitudes() throws SQLException {
        SolicitudPaginacionBean pgnBean = (SolicitudPaginacionBean) GeneralUtil.getViewMap("solicitudPaginacionBean");

        List<Map> listaSolicitudes = solicitudDAO.getListaSolicitudes(pgnBean);
        pgnBean.setListaSolicitudes(listaSolicitudes);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de solicitudes es ; " + listaSolicitudes.size());
        GeneralUtil.putViewMap("solicitudPaginacionBean", pgnBean);
    }

    public void selectOrder(ActionEvent event) {
        try {
            Map orderSelected = (Map) event.getComponent().getAttributes().get("orderSelected");
            String readerType = "";
            SimpleDateFormat sf = GeneralUtil.sdfParseLong;
            Date fechaIns = null;

            fechaIns = sf.parse(orderSelected.get("fechaIns").toString());
            orderSelected.put("fechaIns", null);
            orderSelected.put("fechaAte", null);

            solicitudBean = new SolicitudBean();
            BeanUtils.populate(solicitudBean, orderSelected);
            solicitudBean.setFechaIns(fechaIns);
            orderSelected.put("fechaIns", sf.format(fechaIns));

            //nombreLector nombreTexto tipoLector tipoPrestamo fieldOrderDate
            //         System.out.println(solicitudBean.toString());
            if (orderSelected.get("tipoLector").toString().equals("E")) {
                readerType = MensajesUtil.getProperty("fieldStudent");
            } else {
                readerType = MensajesUtil.getProperty("fieldPerAdm");
            }

            prestamoBean = new PrestamoBean();
            prestamoBean.setSolicitudId(solicitudBean.getSolicitudId());
            prestamoBean.setTipoPrestamo(solicitudBean.getTipoPrestamo());
            //      System.out.println(prestamoBean.toString());

            GeneralUtil.putViewMap("viewLoanType", solicitudBean.getTipoPrestamo());
            GeneralUtil.putViewMap("viewReaderName", orderSelected.get("nombreLector"));
            GeneralUtil.putViewMap("viewReaderType", readerType);
            GeneralUtil.putViewMap("viewText", orderSelected.get("nombreTexto"));
            GeneralUtil.putRequestMap("prestamoBean", prestamoBean);
            GeneralUtil.putRequestMap("solicitudBean", solicitudBean);

            listaEjemplares();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void registerLoan() {
        try {
            solicitudBean = (SolicitudBean) GeneralUtil.getRequestMap("solicitudBean");
            lectorBean = new LectorBean();
            lectorBean.setLectorId(solicitudBean.getLectorId());
            lectorBean = (LectorBean) lectorDAO.findOneReader(lectorBean);
            prestamoBean = (PrestamoBean) GeneralUtil.getRequestMap("prestamoBean");


            StringBuilder error = new StringBuilder();
            if (GeneralUtil.getViewMap("exemplarySelected") == null || GeneralUtil.getViewMap("exemplarySelected").toString().equals("-1")) {
                error.append(MensajesUtil.getProperty("messageErrorCtxSelectExemplaryToLoan"));
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                return;
            }

            int ejemplarId = Integer.parseInt(GeneralUtil.getViewMap("exemplarySelected").toString());
            prestamoBean.setEjemplarId(ejemplarId);
            prestamoBean.setUsuarioAte(GeneralUtil.getSessionUser().getUsuarioId());

            solicitudBean = new SolicitudBean();
            solicitudBean.setSolicitudId(prestamoBean.getSolicitudId());
            solicitudBean.setEstado("P");

            ejemplarBean = new EjemplarBean();
            ejemplarBean.setEjemplarId(prestamoBean.getEjemplarId());
            ejemplarBean.setEstado("P");

            boolean studetType = lectorBean.getTipo().equals(GeneralUtil.getProperty("lectorTypeStudent"));
            boolean persAdmType = lectorBean.getTipo().equals(GeneralUtil.getProperty("lectorTypePerAdm"));
            int cantPrestCur = prestamoDAO.getCountPrestamosEnCursoPorLectorId(lectorBean.getLectorId());

            if (prestamoBean.getTipoPrestamo().equals("D")) {

                if (prestamoBean.getTipoDocGarantia() == null || prestamoBean.getTipoDocGarantia().isEmpty()) {
                    error.delete(0, error.length());
                    error.append(MensajesUtil.getProperty("messageErrorCtxSelectWarrantyDocument"));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                    FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                    return;
                }
                if (prestamoBean.getNumDocGarantia().isEmpty()) {
                    error.delete(0, error.length());
                    error.append(MensajesUtil.getProperty("messageErrorCtxEnterDocumentNum"));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                    FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                    return;
                }
                if (studetType && cantPrestCur >= 1) {
                    error.delete(0, error.length());
                    error.append(MensajesUtil.getProperty("messageErrorCtxMaxLoan"));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                    FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                    return;
                }
                if (persAdmType && cantPrestCur >= 2) {
                    error.delete(0, error.length());
                    error.append(MensajesUtil.getProperty("messageErrorCtxMaxLoan"));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                    FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                    return;
                }
                ejemplarDAO.updateExemplaryState(ejemplarBean);
                solicitudDAO.updateOrderState(solicitudBean);
                if (lectorBean.getTipo().equals("E")) {
                    prestamoDAO.insertStudentHomeLoan(prestamoBean);
                } else if (lectorBean.getTipo().equals("P")) {
                    prestamoDAO.insertPerAdmHomeLoan(prestamoBean);
                }
            } else if (prestamoBean.getTipoPrestamo().equals("S")) {
                if (studetType && cantPrestCur >= 1) {
                    error.delete(0, error.length());
                    error.append(MensajesUtil.getProperty("messageErrorCtxMaxLoan"));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                    FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                    return;
                }
                if (persAdmType && cantPrestCur >= 2) {
                    error.delete(0, error.length());
                    error.append(MensajesUtil.getProperty("messageErrorCtxMaxLoan"));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                    FacesContext.getCurrentInstance().addMessage("frmOrderProcess:cmbOrderProc", message);
                    return;
                }
                ejemplarDAO.updateExemplaryState(ejemplarBean);
                solicitudDAO.updateOrderState(solicitudBean);
                prestamoDAO.insertRomeLoan(prestamoBean);
            }
            GeneralUtil.removeVewMap("exemplarySelected");

            listaSolicitudes();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void cancelOrder() {
        try {
            System.out.println("entro");
            prestamoBean = (PrestamoBean) GeneralUtil.getRequestMap("prestamoBean");
            solicitudBean = (SolicitudBean) GeneralUtil.getRequestMap("solicitudBean");
            solicitudBean.setSolicitudId(prestamoBean.getSolicitudId());
            solicitudDAO.updateOrderState(solicitudBean);
            GeneralUtil.putRequestMap("dataOrderCancel", true);
            
            GeneralUtil.removeVewMap("exemplarySelected");
            listaSolicitudes();
        } catch (Exception e) {
            GeneralUtil.putRequestMap("dataOrderCancel", false);
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaEjemplares() throws SQLException {
        EjemplarPaginationBean pgnBean = new EjemplarPaginationBean();
        pgnBean.setTextoId(solicitudBean.getTextoId());
        pgnBean.setSedeId(GeneralUtil.getSessionUser().getSedeId());
        List<Map> listaEjemplares = ejemplarDAO.getListaEjemplarPorTexto(pgnBean);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de ejemplares ; " + listaEjemplares.size());
        pgnBean.setListaEjemplares(listaEjemplares);
        GeneralUtil.putViewMap("ejemplarPaginationBean", pgnBean);
    }

    public void selectExemplaryToLoan() {
        try {

            EjemplarPaginationBean pgn = (EjemplarPaginationBean) GeneralUtil.getViewMap("ejemplarPaginationBean");
            int indexExemplary = Integer.parseInt(GeneralUtil.getRequestMap("indexExemplary").toString());
            for (Map mapita : pgn.getListaEjemplares()) {
                mapita.put("selected", "false");
            }
            if (indexExemplary != -1) {
                pgn.getListaEjemplares().get(indexExemplary).put("selected", "true");
            }
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
