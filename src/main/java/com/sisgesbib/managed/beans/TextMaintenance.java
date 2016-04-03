/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.TextoPaginationBean;
import com.sisgesbib.constants.ViewIdPagesCons;
import com.sisgesbib.dao.AutorDAO;
import com.sisgesbib.dao.CategoriaDAO;
import com.sisgesbib.dao.MateriaDAO;
import com.sisgesbib.dao.TextoDAO;
import com.sisgesbib.dao.UtilDAO;
import com.sisgesbib.dao.beans.AutorBean;
import com.sisgesbib.dao.beans.EditorialBean;
import com.sisgesbib.dao.beans.TemaBean;
import com.sisgesbib.dao.beans.TextoBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
public class TextMaintenance {

    TextoDAO textoDAO = null;
    CategoriaDAO categoryDAO = null;
    MateriaDAO materiaDAO = null;
    AutorDAO autorDAO = null;
    UtilDAO utilDAO = null;
    private String initPage;
    private String viewPageId = null;
    private TextoBean textoBean = null;
    private Map generalMap = null;

    /** Creates a new instance of TextMaintenance */
    public TextMaintenance() {
        textoDAO = DAOFactory.getTextoDAO();
        categoryDAO = DAOFactory.getCategoriaDAO();
        materiaDAO = DAOFactory.getMateriaDAO();
        utilDAO = DAOFactory.getUtilDAO();
        autorDAO = DAOFactory.getAutorDAO();
        viewPageId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        ///sisgesbib/maintenance/textmaintenance/textmaintenance.xhtml
        //   String pagina2=FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("referer");
//http://localhost:8080/SisGesBiblioteca/sisgesbib/maintenance/rolemaintenance/rolemaintenance.jsf
        try {
            System.out.println("la pagina es " + viewPageId);
            if (viewPageId.equals(ViewIdPagesCons.VIEWID_TEXTMAINTENANCE_INDEX)) {
                if (GeneralUtil.getViewMap("textoPaginationBean") == null) {
                    Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] Entro al pseudo load de textmaintenance (index)");
                    listaTextos();
                }
            } else if (viewPageId.equals(ViewIdPagesCons.VIEWID_TEXTMAINTENANCE_NEWTEXT)) {
                if (GeneralUtil.getViewMap("viewMapCategory") == null) {
                    Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] Entro al pseudo load de textmaintenance (New Text)");
                    loadComboCategory();
                    loadComboMatter();
                    loadComboLanguage();
                    //   textoBean = (TextoBean) GeneralUtil.getRequestMap("textoBean");
                    //  System.out.println(textoBean.toString());
                }
            } else if (viewPageId.equals(ViewIdPagesCons.VIEWID_TEXTMAINTENANCE_UPDTEXT)) {
                if (GeneralUtil.getViewMap("viewMapCategory") == null) {
                    Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] Entro al pseudo load de textmaintenance (New Text)");
                    loadComboCategory();
                    loadComboMatter();
                    loadComboLanguage();

                    generalMap = (Map) GeneralUtil.getSessionMap("textoBeanSession");
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    generalMap.put("fechaIns", df.parse(generalMap.get("fechaIns").toString()));
                    if (generalMap.get("fechaAct") != null) {
                        generalMap.put("fechaAct", df.parse(generalMap.get("fechaAct").toString()));
                    }
                    textoBean = new TextoBean();
                    BeanUtils.populate(textoBean, generalMap);

                    System.out.println(textoBean.toString());

                    GeneralUtil.putRequestMap("textoBean", textoBean);
                    GeneralUtil.putViewMap("viewPublisherName", generalMap.get("nombreEditorial"));
                    GeneralUtil.putViewMap("viewThemeName", generalMap.get("nombreTema"));

                    List<AutorBean> listaAutores = autorDAO.getAuthorsByTexto(textoBean.getTextoId());
                    System.out.println("tamaño " + listaAutores.size());
                    GeneralUtil.putViewMap("viewListSelectedAuthors", listaAutores);

                    GeneralUtil.removeSessionMap("textoBeanSession");

                }
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void saveNewText() {
        try {
            textoBean = (TextoBean) GeneralUtil.getRequestMap("textoBean");
            System.out.println(textoBean.toString());
            textoBean.setUsuarioIns(GeneralUtil.getSessionUser().getUsuarioId());

            List<AutorBean> listaAutor = (List<AutorBean>) GeneralUtil.getViewMap("viewListSelectedAuthors");
            if (listaAutor == null || listaAutor.isEmpty()) {
                String error = MensajesUtil.getProperty("messageErrorCtxRequiredAuthors");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:dtbAuthors", message);
                return;
            }
            if (textoDAO.validaCodIntText(textoBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorCtxTextCodeIntInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:txtCodInt", message);
                return;
            }
            if (textoDAO.validaNameText(textoBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:txtName", message);
                return;
            }
            if (textoDAO.validaISBNText(textoBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorCtxTextISBNInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:txtISBN", message);
                return;
            }
            textoDAO.insertText(textoBean);
            System.out.println("el id generado es " + textoBean.getTextoId());
            generalMap = new HashMap();
            generalMap.put("textoId", textoBean.getTextoId());
            generalMap.put("listaAutor", listaAutor);
            textoDAO.insertTextoAutor(generalMap);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updateText() {
        try {
            textoBean = (TextoBean) GeneralUtil.getRequestMap("textoBean");
            System.out.println(textoBean.toString());
            textoBean.setUsuarioAct(GeneralUtil.getSessionUser().getUsuarioId());
            
            List<AutorBean> listaAutor = (List<AutorBean>) GeneralUtil.getViewMap("viewListSelectedAuthors");
            if (listaAutor == null || listaAutor.isEmpty()) {
                String error = MensajesUtil.getProperty("messageErrorCtxRequiredAuthors");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:dtbAuthors", message);
                return;
            }
            
            if (textoDAO.validaUpdateCodIntText(textoBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorCtxTextCodeIntInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:txtCodInt", message);
                return;
            }
            if (textoDAO.validaUpdateNameText(textoBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:txtName", message);
                return;
            }
            if (textoDAO.validaUpdateISBNText(textoBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorCtxTextISBNInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmNewUser:txtISBN", message);
                return;
            }
      
            textoDAO.updateTexto(textoBean);
            textoDAO.deleteTextoAutor(textoBean.getTextoId());
            generalMap = new HashMap();
            generalMap.put("textoId", textoBean.getTextoId());
            generalMap.put("listaAutor", listaAutor);
            textoDAO.insertTextoAutor(generalMap);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectText(ActionEvent event) {
        try {
            Map mapTextSelected = (Map) event.getComponent().getAttributes().get("textSelected");

            GeneralUtil.putSessionMap("textoBeanSession", mapTextSelected);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectAuthor(ActionEvent event) {
        try {
            Map mapAuthor = (Map) event.getComponent().getAttributes().get("selectdAuthor");
            List<Map> listaSelectedAuthors = (List<Map>) GeneralUtil.getViewMap("viewListSelectedAuthors");
            if (listaSelectedAuthors == null) {
                System.out.println("entro null");
                listaSelectedAuthors = new ArrayList<Map>();
            }
            int flag = 0;
            for (Map autor : listaSelectedAuthors) {
                if (autor.get("autorId").toString().equals(mapAuthor.get("autorId").toString())) {
                    flag++;
                }
            }
            if (flag == 0) {
                listaSelectedAuthors.add(mapAuthor);
            }

            GeneralUtil.putViewMap("viewListSelectedAuthors", listaSelectedAuthors);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectPublishing(ActionEvent event) {
        try {
            EditorialBean editorialSelected = (EditorialBean) event.getComponent().getAttributes().get("pubSelected");
            textoBean = new TextoBean();
            textoBean.setEditorialId(editorialSelected.getEditorialId());
            GeneralUtil.putRequestMap("textoBean", textoBean);
            GeneralUtil.putViewMap("viewPublisherName", editorialSelected.getNombre());
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectTheme(ActionEvent event) {
        try {
            TemaBean temaBean = (TemaBean) event.getComponent().getAttributes().get("themeSelected");
            textoBean = new TextoBean();
            textoBean.setTemaId(temaBean.getTemaId());

            GeneralUtil.putRequestMap("textoBean", textoBean);
            GeneralUtil.putViewMap("viewThemeName", temaBean.getNombre());
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void deleteAuthor(ActionEvent event) {
        try {
            Map mapAuthor = (Map) event.getComponent().getAttributes().get("authorToDelete");
            List<Map> listaSelectedAuthors = (List<Map>) GeneralUtil.getViewMap("viewListSelectedAuthors");
            listaSelectedAuthors.remove(mapAuthor);
            GeneralUtil.putViewMap("viewListSelectedAuthors", listaSelectedAuthors);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void searchText() {
        try {
            listaTextos();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaTextos() throws SQLException {
        TextoPaginationBean pgnTextoBean = (TextoPaginationBean) GeneralUtil.getViewMap("textoPaginationBean");
        if (pgnTextoBean == null) {
            pgnTextoBean = new TextoPaginationBean();
        }

        List<Map> listaTexto = textoDAO.getListaTextos(pgnTextoBean);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de textos es " + listaTexto.size());
        pgnTextoBean.setListaTexto(listaTexto);
        GeneralUtil.putViewMap("textoPaginationBean", pgnTextoBean);
    }

    public void loadComboCategory() throws SQLException {
        Map mapCategory = categoryDAO.getCategoryForCombo();
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de categorias es : " + mapCategory.size());
        GeneralUtil.putViewMap("viewMapCategory", mapCategory);
    }

    public void loadComboMatter() throws SQLException {
        Map mapMateria = materiaDAO.getMattersForCombo();
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de materia es : " + mapMateria.size());
        GeneralUtil.putViewMap("viewMapMatter", mapMateria);
    }

    public void loadComboLanguage() throws SQLException {
        Map mapLanguage = utilDAO.getLanguageForCombo();
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de idiomas es : " + mapLanguage.size());
        GeneralUtil.putViewMap("viewMapLanguage", mapLanguage);
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
