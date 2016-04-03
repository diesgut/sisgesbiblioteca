/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.AutorPaginationBean;
import com.sisgesbib.beans.filters.MateriaPaginationBean;
import com.sisgesbib.beans.filters.TemaPaginationBean;
import com.sisgesbib.beans.filters.TextoPaginationBean;
import com.sisgesbib.dao.AutorDAO;
import com.sisgesbib.dao.LectorDAO;
import com.sisgesbib.dao.MateriaDAO;
import com.sisgesbib.dao.PrestamoDAO;
import com.sisgesbib.dao.SolicitudDAO;
import com.sisgesbib.dao.TemaDAO;
import com.sisgesbib.dao.TextoDAO;
import com.sisgesbib.dao.beans.CategoriaBean;
import com.sisgesbib.dao.beans.EditorialBean;
import com.sisgesbib.dao.beans.LectorBean;
import com.sisgesbib.dao.beans.MateriaBean;
import com.sisgesbib.dao.beans.SolicitudBean;
import com.sisgesbib.dao.beans.TemaBean;
import com.sisgesbib.dao.beans.TextoBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ConsultarTextosMB {

    TextoDAO textoDAO = null;
    AutorDAO autorDAO = null;
    TemaDAO temaDAO = null;
    LectorDAO lectorDAO = null;
    MateriaDAO materiaDAO = null;
    LectorBean lectorBean = null;
    SolicitudBean solicitudBean = null;
    TextoBean textoBean = null;
    SolicitudDAO solicitudDAO = null;
    PrestamoDAO prestamoDAO = null;

    /** Creates a new instance of ConsultarTextosMB */
    public ConsultarTextosMB() {
        try {
            textoDAO = DAOFactory.getTextoDAO();
            autorDAO = DAOFactory.getAutorDAO();
            temaDAO = DAOFactory.getTemaDAO();
            materiaDAO = DAOFactory.getMateriaDAO();
            lectorDAO = DAOFactory.getLectorDAO();
            solicitudDAO = DAOFactory.getSolicitudDAO();
            prestamoDAO = DAOFactory.getPrestamoDAO();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void searchText() {
        try {
            TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");
            if (pgnTextoBean.getCriterioBusqueda().equals("text")) {
                listaTextos();
            } else if (pgnTextoBean.getCriterioBusqueda().equals("aut")) {
                listaAuthors();
            } else if (pgnTextoBean.getCriterioBusqueda().equals("tem")) {
                listaThemes();
            } else if (pgnTextoBean.getCriterioBusqueda().equals("mat")) {
                listaMatters();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaTextos() throws SQLException {
        TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");
        if (pgnTextoBean == null) {
            pgnTextoBean = new TextoPaginationBean();
        }
        System.out.println(pgnTextoBean.toString());
        List<Map> listaTexto = textoDAO.getListaTextoForSearch(pgnTextoBean);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de textos es " + listaTexto.size());
        pgnTextoBean.setListaTexto(listaTexto);
        GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);

        GeneralUtil.removeVewMap("autorPaginationBean");
        GeneralUtil.removeVewMap("temaPaginationBean");
        GeneralUtil.removeVewMap("materiaPaginationBean");
    }

    public void listaAuthors() throws SQLException {
        TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");

        AutorPaginationBean pgnAuthor = (AutorPaginationBean) GeneralUtil.getViewMap("autorPaginationBean");
        pgnAuthor.setNombres(pgnTextoBean.getTextoBusqueda());
        if (pgnAuthor == null) {
            pgnAuthor = new AutorPaginationBean();
        }
        List<Map> lstAuthors = autorDAO.getListaAuthors(pgnAuthor);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de autores es : " + lstAuthors.size());
        pgnAuthor.setLstAuthors(lstAuthors);
        GeneralUtil.putViewMap("autorPaginationBean", pgnAuthor);

        pgnTextoBean.setListaTexto(new ArrayList<Map>());
        GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
        GeneralUtil.removeVewMap("temaPaginationBean");
        GeneralUtil.removeVewMap("materiaPaginationBean");
    }

    public void listaThemes() throws SQLException {
        TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");

        TemaPaginationBean pgnBean = (TemaPaginationBean) GeneralUtil.getViewMap("temaPaginationBean");
        pgnBean.setNombre(pgnTextoBean.getTextoBusqueda());

        if (pgnBean == null) {
            pgnBean = new TemaPaginationBean();
            System.out.println("entro null");
        }
        List<TemaBean> lstTemas = temaDAO.getListaTheme(pgnBean);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de temas es  : " + lstTemas.size());
        pgnBean.setListaTema(lstTemas);
        GeneralUtil.putViewMap("temaPaginationBean", pgnBean);

        pgnTextoBean.setListaTexto(new ArrayList<Map>());
        GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
        GeneralUtil.removeVewMap("autorPaginationBean");
        GeneralUtil.removeVewMap("materiaPaginationBean");
    }

    public void listaMatters() throws SQLException {
        TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");

        MateriaPaginationBean pgnBean = (MateriaPaginationBean) GeneralUtil.getViewMap("materiaPaginationBean");
        pgnBean.setNombre(pgnTextoBean.getTextoBusqueda());
        if (pgnBean == null) {
            pgnBean = new MateriaPaginationBean();
            System.out.println("entro null");
        }
        List<MateriaBean> lstMatters = materiaDAO.getListaMateria(pgnBean);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de materias es  : " + lstMatters.size());
        pgnBean.setLstMateria(lstMatters);
        GeneralUtil.putViewMap("materiaPaginationBean", pgnBean);

        pgnTextoBean.setListaTexto(new ArrayList<Map>());
        GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
        GeneralUtil.removeVewMap("autorPaginationBean");
        GeneralUtil.removeVewMap("temaPaginationBean");
    }

    public void selectTexto(ActionEvent event) {
        try {
            Map textSelected = (Map) event.getComponent().getAttributes().get("textSelected");
            TextoBean textoBean = new TextoBean();
            textSelected.put("fechaIns", null);
            textSelected.put("fechaAct", null);
            BeanUtils.populate(textoBean, textSelected);
            System.out.println(textoBean.toString());

            CategoriaBean categoriaBean = new CategoriaBean();
            categoriaBean.setNombre(textSelected.get("nombreCategoria").toString());
            EditorialBean editorialBean = new EditorialBean();
            editorialBean.setNombre(textSelected.get("nombreEditorial").toString());
            String autores = autorDAO.getAutoresPorTexto(textoBean.getTextoId());

            GeneralUtil.putRequestMap("textoBean", textoBean);

            GeneralUtil.putViewMap("viewCategoria", categoriaBean.getNombre());
            GeneralUtil.putViewMap("viewEditorial", editorialBean.getNombre());
            GeneralUtil.putViewMap("viewAutores", autores);
            GeneralUtil.putViewMap("viewPais", textSelected.get("nombrePais"));
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectAuthor() {
        try {
            String autorId = GeneralUtil.getRequestMap("reqAutorId").toString();


            TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");
            pgnTextoBean.setCriterioBusqueda("aut");
            pgnTextoBean.setTextoBusqueda(autorId);
            GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
            listaTextos();

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectTheme() {
        try {
            TemaBean temaBean = (TemaBean) GeneralUtil.getRequestMap("temaBean");
            System.out.println(temaBean.toString());

            TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");
            pgnTextoBean.setCriterioBusqueda("tem");
            pgnTextoBean.setTextoBusqueda(temaBean.getTemaId().toString());
            GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
            listaTextos();

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectMatter() {
        try {
            MateriaBean materiaBean = (MateriaBean) GeneralUtil.getRequestMap("materiaBean");

            TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");
            pgnTextoBean.setCriterioBusqueda("mat");
            pgnTextoBean.setTextoBusqueda(materiaBean.getMateriaId().toString());
            GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
            listaTextos();

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void validateReader() {
        try {
            lectorBean = (LectorBean) GeneralUtil.getRequestMap("lectorBean");
            lectorBean = lectorDAO.findOneReader(lectorBean);
            textoBean = (TextoBean) GeneralUtil.getRequestMap("textoBean");
            String tipoPrestamo = GeneralUtil.getRequestMap("reqTipoPrestamo").toString();

            if (textoBean.getTextoId() == null) {
                GeneralUtil.putRequestMap("confirmar", "error");
                String error = MensajesUtil.getProperty("messageErrorContextRequiredSelectText");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmSolicitud:cmbConsultarLector", message);
                return;
            }

            if (lectorBean == null) {
                GeneralUtil.putRequestMap("confirmar", "error");
                String error = MensajesUtil.getProperty("messageErrorCtxReaderNotFound");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmSolicitud:cmbConsultarLector", message);
                return;
            }

            //   System.out.println(textoBean.toString());
            //    System.out.println("el nombre es " + lectorBean.getNombres() + " ape " + lectorBean.getApeMaterno() + " ape ma " + lectorBean.getApeMaterno());
            boolean studetType = lectorBean.getTipo().equals(GeneralUtil.getProperty("lectorTypeStudent"));
            boolean persAdmType = lectorBean.getTipo().equals(GeneralUtil.getProperty("lectorTypePerAdm"));
            System.out.println("el lector id es " + lectorBean.getLectorId());
    //        int cantSolPend = solicitudDAO.cantSolicitudesPend(lectorBean.getLectorId());
            int cantPrestCur = prestamoDAO.getCountPrestamosEnCursoPorLectorId(lectorBean.getLectorId());
           
//            if (studetType) {
//                if (cantSolPend == 1) {
//                    GeneralUtil.putRequestMap("confirmar", "solPend");
////                    String error = MensajesUtil.getProperty("messageErrorCtxOrderPending");
////                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
////                    FacesContext.getCurrentInstance().addMessage("frmSolicitud:cmbConsultarLector", message);
//                    return;
//                }
//            } else if (persAdmType) {
//                if (cantSolPend == 2) {
//                    GeneralUtil.putRequestMap("confirmar", "solPend");
////                    String error = MensajesUtil.getProperty("messageErrorCtxOrderPending");
////                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
////                    FacesContext.getCurrentInstance().addMessage("frmSolicitud:cmbConsultarLector", message);
//                    return;
//                }
//            }

            if (studetType) {
               
                if (cantPrestCur >= 1) {
                    GeneralUtil.putRequestMap("confirmar", "presCur");
//                    String error = MensajesUtil.getProperty("messageErrorCtxLoanPending");
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
//                    FacesContext.getCurrentInstance().addMessage("frmSolicitud:cmbConsultarLector", message);
                    return;
                }
            } else if (persAdmType) {
               
                if (cantPrestCur >= 2) {
                    GeneralUtil.putRequestMap("confirmar", "presCur");
//                    String error = MensajesUtil.getProperty("messageErrorCtxLoanPending");
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
//                    FacesContext.getCurrentInstance().addMessage("frmSolicitud:cmbConsultarLector", message);
                    return;
                }
            }


            GeneralUtil.putRequestMap("lectorBean", lectorBean);
            GeneralUtil.putRequestMap("textoBean", textoBean);
            GeneralUtil.putRequestMap("reqTipoPrestamo", tipoPrestamo);

            GeneralUtil.putRequestMap("confirmar", "success");


        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void cancelOrder() {
        try {
            GeneralUtil.removeVewMap("viewCategoria");
            GeneralUtil.removeVewMap("viewEditorial");
            GeneralUtil.removeVewMap("viewAutores");
            GeneralUtil.removeVewMap("viewPais");
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void confirmaSolicitud() {
        try {
            lectorBean = (LectorBean) GeneralUtil.getRequestMap("lectorBean");
            textoBean = (TextoBean) GeneralUtil.getRequestMap("textoBean");
            String tipoPrestamo = (String) GeneralUtil.getRequestMap("reqTipoPrestamo");

            System.out.println("el codigo de lector es " + lectorBean.getLectorId());
            System.out.println("el codigo del texto es " + textoBean.getTextoId());
            System.out.println("el tipo de prestamo es " + tipoPrestamo);
            solicitudBean = new SolicitudBean();
            solicitudBean.setTipoPrestamo(tipoPrestamo);
            solicitudBean.setUsuarioAte(GeneralUtil.getSessionUser().getUsuarioId());
            solicitudBean.setTextoId(textoBean.getTextoId());
            solicitudBean.setLectorId(lectorBean.getLectorId());
            solicitudBean.setSedeId(GeneralUtil.getSessionUser().getSedeId());
            solicitudDAO.insertaSolicitud(solicitudBean);

            GeneralUtil.removeRequestMap("lectorBean");
            GeneralUtil.removeRequestMap("textoBean");
            GeneralUtil.removeVewMap("viewCategoria");
            GeneralUtil.removeVewMap("viewEditorial");
            GeneralUtil.removeVewMap("viewAutores");
            GeneralUtil.removeVewMap("viewPais");
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }
}
