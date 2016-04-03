/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.dao.MenuDAO;
import com.sisgesbib.dao.PerfilDAO;
import com.sisgesbib.dao.UtilDAO;
import com.sisgesbib.dao.beans.MenuBean;
import com.sisgesbib.dao.beans.PerfilBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author despantoso
 */
public class RoleMaintenanceMB {

    private String initPage;
    private PerfilDAO perfilDAO = DAOFactory.getPerfilDAO();
    private MenuDAO menuDAO = DAOFactory.getMenuDAO();
    private UtilDAO utilDAO = DAOFactory.getUtilDAO();
    private PerfilBean perfilBean = null;

    /** Creates a new instance of RoleMaintenanceMB */
    public RoleMaintenanceMB() {
        try {
            if (GeneralUtil.getViewMap("viewLstRol") == null) {
                listasRoles();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listasRoles() throws SQLException {
        List<PerfilBean> lstPerfil = perfilDAO.getListaPerfil();
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de perfiles es  : " + lstPerfil.size());
        GeneralUtil.putViewMap("viewLstRol", lstPerfil);
    }

    public void insertNewPerfil() {
        try {
            perfilBean = (PerfilBean) GeneralUtil.getRequestMap("perfilBean");
            System.out.println(perfilBean.toString());
            if (perfilDAO.validateName(perfilBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            perfilDAO.insertNewPerfil(perfilBean);
            listasRoles();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void updatePerfil() {
        try {
            perfilBean = (PerfilBean) GeneralUtil.getRequestMap("perfilBean");
            System.out.println(perfilBean.toString());
            if (perfilDAO.validateChangeName(perfilBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            perfilDAO.updatePerfil(perfilBean);
            listasRoles();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectRole(ActionEvent event) {
        try {
            perfilBean = (PerfilBean) event.getComponent().getAttributes().get("rolSelected");
            GeneralUtil.putRequestMap("perfilBean", perfilBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectRoleForAssign(ActionEvent event) {
        try {
            perfilBean = (PerfilBean) event.getComponent().getAttributes().get("rolSelected");

            GeneralUtil.putRequestMap("perfilBean", perfilBean);
            GeneralUtil.putViewMap("expandedTree", true);

            listaMenuForAssign(perfilBean.getPerfilId());
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void assignMenu() {
        try {
            perfilBean = (PerfilBean) GeneralUtil.getRequestMap("perfilBean");
            List<MenuBean> lstMenu = (List<MenuBean>) GeneralUtil.getViewMap("lstMenu");
            List<Map> listaInsertar = new ArrayList<Map>();
            Map params = null;
            for (MenuBean parentMenu : lstMenu) {
                for (MenuBean childMenu : parentMenu.getMenusHijos()) {
                    if (childMenu.isSelected() && childMenu.getMenuParentId() != 0) {
                        params = new HashMap();
                        params.put("roleId", perfilBean.getPerfilId());
                        params.put("menuId", childMenu.getMenuId());
                        listaInsertar.add(params);
                    }
                }
            }

            utilDAO.deletePerfilMenu(perfilBean.getPerfilId());
            if (!listaInsertar.isEmpty()) {
                utilDAO.insertPerfilMenu(listaInsertar);
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaMenuForAssign(int roleId) throws SQLException {
        List<MenuBean> listaMenu = menuDAO.getListaMenusForAssign(roleId);
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de munus es  : " + listaMenu.size());
        List<MenuBean> listaParentMenu = new ArrayList<MenuBean>();
        List<MenuBean> listaChildrenMenu = null;

        for (MenuBean menu : listaMenu) {
            if (menu.getMenuParentId() == 0) {
                listaParentMenu.add(menu);
            }
        }
        GeneralUtil.sortList(listaParentMenu, "orden");
        Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de munus padres es  : " + listaParentMenu.size());

        for (MenuBean menuParent : listaParentMenu) {
            listaChildrenMenu = new ArrayList<MenuBean>();
            for (MenuBean menu : listaMenu) {
                if (menu.getMenuParentId().intValue() == menuParent.getMenuId().intValue()) {
                    System.out.println("agrego");
                    System.out.println(menu.isSelected());
                    listaChildrenMenu.add(menu);
                }
            }
            GeneralUtil.sortList(listaMenu, "orden");
            menuParent.setMenusHijos(listaChildrenMenu);
            Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista de munus hijos de  " + menuParent.getNombre() + " es : " + listaChildrenMenu.size());
        }

//        for (MenuBean menuParent : listaParentMenu) {
//            System.out.println("El menu padre es " + menuParent.getNombre());
//            for (MenuBean menuChildren : menuParent.getMenusHijos()) {
//                System.out.println("-- " + menuChildren.getNombre());
//            }
//        }

        GeneralUtil.putViewMap("lstMenu", listaParentMenu);

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
