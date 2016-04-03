/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.UsuarioPaginationBean;
import com.sisgesbib.dao.EmpresaDAO;
import com.sisgesbib.dao.PerfilDAO;
import com.sisgesbib.dao.UsuarioDAO;
import com.sisgesbib.dao.beans.UsuarioBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import java.sql.SQLException;
import java.util.List;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Diego
 */
public class UserMaintenanceMB {

    private String initPage;
    private UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
    private EmpresaDAO empresaDAO = null;
    private PerfilDAO perfilDAO = null;

    /** Creates a new instance of UserMaintenanceMB */
    public UserMaintenanceMB() {
        try {
            empresaDAO = DAOFactory.getEmpresaDAO();
            perfilDAO = DAOFactory.getPerfilDAO();
            if (GeneralUtil.getViewMap("viewLstUserMaintenance") == null) {
                Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] Entro al pseudo load");
                listaUserForMaintenance();
                loadComboCompany();
                loadComboPerfil();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaUserForMaintenance() throws SQLException {
        UsuarioPaginationBean paginacionBean = (UsuarioPaginationBean) GeneralUtil.getViewMap("usuarioPaginationBean");
        if (paginacionBean == null) {
            paginacionBean = new UsuarioPaginationBean();
        }

        List<UsuarioBean> listaUsers = userDAO.getListaUsers(paginacionBean);
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista de usuarios es " + listaUsers.size());
        GeneralUtil.putViewMap("viewLstUserMaintenance", listaUsers);
    }

    public void loadComboCompany() throws SQLException {
        Map mapCompany = empresaDAO.getMapCompany();
        GeneralUtil.putViewMap("viewMapCompany", mapCompany);
    }

    public void loadComboPerfil() throws SQLException {
        Map mapPerfiles = perfilDAO.getMapPerfiles();
        GeneralUtil.putViewMap("viewMapPerfil", mapPerfiles);
    }

    public void updateUser() {
        try {
            UsuarioBean userBean = (UsuarioBean) GeneralUtil.getRequestMap("usuarioBean");
            System.out.println(userBean.toString());

            int validaLogin = userDAO.validateLoginChange(userBean);
            if (validaLogin > 0) {
                String error = MensajesUtil.getProperty("messageErrorContextLoginInUse");
                System.out.println("el error   " + error);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                FacesContext.getCurrentInstance().addMessage("frmUpdUser:btnUpd", message);
                return;
            }

            userDAO.updateUsuario(userBean);

            listaUserForMaintenance();

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void insertUser() {
        try {
            UsuarioBean usuarioBean = (UsuarioBean) GeneralUtil.getRequestMap("usuarioBean");

            System.out.println(usuarioBean.toString());


            int usernameValida = userDAO.usernameValidate(usuarioBean);
            StringBuilder error = new StringBuilder();

            if (usernameValida > 0) {
                Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El Username ingresado ya esta en uso");
                error.append(MensajesUtil.getProperty("messageErrorContextLoginInUse"));
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error.toString(), error.toString());
                FacesContext.getCurrentInstance().addMessage("frmNewUser:btnInsUser", message);
                return;
            }
            usuarioBean.setIntInvalidos(0);
            usuarioBean.setEstado("A");
            userDAO.insertaUsuario(usuarioBean);
            Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] Se infreso un usuario nuevo");

            GeneralUtil.removeRequestMap("usuarioBean");
            listaUserForMaintenance();

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectUser(ActionEvent event) {
        try {
            UsuarioBean usuBean = (UsuarioBean) event.getComponent().
                    getAttributes().get("userSelected");
            System.out.println("el usuario es " + usuBean.getUsuarioId());

            GeneralUtil.putRequestMap("usuarioBean", usuBean);

        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void selectUserForAssign(ActionEvent event) {
        try {
            UsuarioBean usuBean = (UsuarioBean) event.getComponent().
                    getAttributes().get("userSelected");

            System.out.println("el usuario es " + usuBean.getUsuarioId());

            GeneralUtil.putRequestMap("companyId", GeneralUtil.getSessionUser().getEmpresaId());
            GeneralUtil.putRequestMap("usuarioBean", usuBean);
            loadUserSedeRole();
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void loadUserSedeRole() {
        try {
            UsuarioBean userBean = (UsuarioBean) GeneralUtil.getRequestMap("usuarioBean");
            int companyId = Integer.parseInt(GeneralUtil.getRequestMap("companyId").toString());

            List<Map> listaUsuarioSedeRole =
                    perfilDAO.getRolesAssignedForUserAndCompany(companyId, userBean.getUsuarioId());
            Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista usuario sede perfil es : " + listaUsuarioSedeRole.size());
            GeneralUtil.putViewMap("lstUserSedeRole", listaUsuarioSedeRole);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void assignSedePerfil() {
        try {
            UsuarioBean userBean = (UsuarioBean) GeneralUtil.getRequestMap("usuarioBean");
            List<Map> lstUserSedeRole = (List<Map>) GeneralUtil.getViewMap("lstUserSedeRole");
            Log.logger.debug("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] El tamaño de la lista usuario sede perfil es : " + lstUserSedeRole.size());

            List<Map> listaUserSedeRoleInsert = new ArrayList<Map>();
            List<Integer> listaSedesToDelete = new ArrayList<Integer>();
            for (Map map : lstUserSedeRole) {
                listaSedesToDelete.add(Integer.parseInt(map.get("sedeId").toString()));
                if (Integer.parseInt(map.get("perfilId").toString()) > 0) {
                    listaUserSedeRoleInsert.add(map);
                }
            }


            Map<String, Object> params = new HashMap<String, Object>();
            params.put("usuarioId", userBean.getUsuarioId());
            params.put("lstUserSedeRole", listaUserSedeRoleInsert);
            params.put("listaSedesToDelete", listaSedesToDelete);

            if (!listaSedesToDelete.isEmpty()) {
                perfilDAO.deleteUsuarioSedePerfilByUserAndSedes(params);
                Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "Elimino correctamente los perfiles no asignados, en la empresa seleccionada del usuario : " + userBean.getUsuarioId());
            }

            if (!listaUserSedeRoleInsert.isEmpty()) {
                perfilDAO.multiInsertUsuarioSedePerfil(params);
                Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                        + "Se asignó correctamente perfiles en la empresa seleccionada al usuario : " + userBean.getUsuarioId());
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void changePassword() {
        try {
            UsuarioBean userBean=(UsuarioBean)GeneralUtil.getRequestMap("usuarioBean");
            userDAO.changeUserPassword(userBean);
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
