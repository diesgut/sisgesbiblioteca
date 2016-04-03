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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.richfaces.component.Mode;
import org.richfaces.component.UIDropDownMenu;
import org.richfaces.component.UIMenuItem;
import org.richfaces.component.UIToolbar;

/**
 *
 * @author Diego
 */
public class MenuMB {

    MenuDAO menuDAO = null;
    private UIToolbar tbarMainMenu;

    /** Creates a new instance of MenuMB */
    public MenuMB() {
        menuDAO = DAOFactory.getMenuDAO();
    }

    /**
     * @return the tbarMainMenu
     */
    public UIToolbar getTbarMainMenu() {
        try {
            if (tbarMainMenu == null) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                tbarMainMenu = (UIToolbar) ctx.getApplication().
                        createComponent(ctx, UIToolbar.COMPONENT_TYPE, "org.richfaces.renderkit.html.ToolbarRenderer");

                tbarMainMenu.setId("tbarMainMenu");
                tbarMainMenu.setStyle("width:100%");
                tbarMainMenu.setHeight("26px");

                List<MenuBean> listaMenus = menuDAO.getListMenuesByRoleId(GeneralUtil.getSessionUser().getPerfilId());
                Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "El tamaño de la lista de menus disponibles es : " + listaMenus.size());

                List<MenuBean> listaParentMenu = new ArrayList<MenuBean>();
                List<MenuBean> listaChildMenu = new ArrayList<MenuBean>();

                for (MenuBean menuBean : listaMenus) {
                    if (menuBean.getMenuParentId() == 0) {
                        listaParentMenu.add(menuBean);
                    }
                }
                Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "El tamaño de la lista de menus padres : " + listaParentMenu.size());

                GeneralUtil.sortList(listaParentMenu, "orden");
                for (MenuBean menuBean : listaMenus) {
                    if (menuBean.getMenuParentId() != 0) {
                        listaChildMenu.add(menuBean);
                    }
                }
                Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "El tamaño de la lista de menus hijos : " + listaChildMenu.size());

                String rootPage = GeneralUtil.getProperty("rootPage");

                for (MenuBean x : listaParentMenu) {
                    UIDropDownMenu ddMenu = (UIDropDownMenu) ctx.getApplication().
                            createComponent(ctx, UIDropDownMenu.COMPONENT_TYPE, "org.richfaces.renderkit.html.DropDownMenuRenderer");
                    ddMenu.setLabel(x.getNombre());
                    ddMenu.setHideDelay(0);
                    ddMenu.setHideDelay(0);
                    ddMenu.setMode(Mode.client);
                    ddMenu.setId(FacesContext.getCurrentInstance().getViewRoot().createUniqueId());
                    for (MenuBean j : listaChildMenu) {
                        if (x.getMenuId().intValue() == j.getMenuParentId().intValue()) {
                            UIMenuItem menuItem = (UIMenuItem) ctx.getApplication().createComponent(ctx, UIMenuItem.COMPONENT_TYPE, "org.richfaces.renderkit.html.MenuItemRenderer");
                            menuItem.setMode(Mode.client);
                            menuItem.setId(FacesContext.getCurrentInstance().getViewRoot().createUniqueId());
                            menuItem.setLabel(j.getNombre());
                            menuItem.setValue(j.getNombre());
                            menuItem.setMode(Mode.client);
                            menuItem.setOnclick("document.location.href='" +  j.getPathId() + "';return false;");
                            
                            ddMenu.getChildren().add(menuItem);
                        }
                    }
                    tbarMainMenu.getChildren().add(ddMenu);
                }
            }
        } catch (Exception e) {
            Log.logger.error("[UserId:" + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
        return tbarMainMenu;
    }

    /**
     * @param tbarMainMenu the tbarMainMenu to set
     */
    public void setTbarMainMenu(UIToolbar tbarMainMenu) {
        this.tbarMainMenu = tbarMainMenu;
    }
}
