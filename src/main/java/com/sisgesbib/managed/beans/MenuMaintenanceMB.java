/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.dao.MenuDAO;
import com.sisgesbib.dao.beans.MenuBean;
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
import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;

/**
 *
 * @author despantoso
 */
public class MenuMaintenanceMB {

    private String initPage;
    private MenuBean menuBean = null;
    MenuDAO menuDAO = DAOFactory.getMenuDAO();

    /** Creates a new instance of MenuMaintenanceMB */
    public MenuMaintenanceMB() {

        try {
            if (GeneralUtil.getViewMap("lstMenu") == null) {
                listaMenu();
                loadComboParentMenu();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaMenu() throws SQLException {
        List<MenuBean> listaMenu = menuDAO.getListMenu();
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

    public void loadComboParentMenu() {
        try {
            Map mapParentMenu = menuDAO.getMenuParentForCombo();
            GeneralUtil.putViewMap("viewMapParentMenu", mapParentMenu);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) {
        try {
            List<Object> selection = new ArrayList<Object>(selectionChangeEvent.getNewSelection());
            Object currentSelectionKey = selection.get(0);
            UITree tree = (UITree) selectionChangeEvent.getSource();

            Object storedKey = tree.getRowKey();
            tree.setRowKey(currentSelectionKey);
            menuBean = (MenuBean) tree.getRowData();
            System.out.println(menuBean);
            System.out.println(menuBean.getNombre());
            tree.setRowKey(storedKey);

            GeneralUtil.putRequestMap("menuBean", menuBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void insertParentMenu() {
        try {
            menuBean = (MenuBean) GeneralUtil.getRequestMap("menuBean");

            if (menuDAO.validateNameParentMenu(menuBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            int maxOrder = menuDAO.getMaxOrdenParentMenu();
            menuBean.setOrden(maxOrder);
            menuDAO.insertParentMenu(menuBean);
            listaMenu();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void insertChildMenu() {
        try {
            menuBean = (MenuBean) GeneralUtil.getRequestMap("menuBean");
            System.out.println(menuBean.toString());

            if (menuDAO.validateNameChildMenu(menuBean) > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextNameInUse");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            int maxOrder=menuDAO.getMaxOrderChildMenu(menuBean);
            
            menuBean.setOrden(maxOrder);
            menuDAO.insertChildMenu(menuBean);
            GeneralUtil.savePathMenu(menuBean.getPathId(), menuBean.getPath());
            
            listaMenu();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    /**
     * @return the initPage
     */
    public String getInitPage() {
        System.out.println("entro");
        return initPage;
    }

    /**
     * @param initPage the initPage to set
     */
    public void setInitPage(String initPage) {
        this.initPage = initPage;
    }
}
