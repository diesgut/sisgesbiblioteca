/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.dao.EmpresaDAO;
import com.sisgesbib.dao.SedeDAO;
import com.sisgesbib.dao.UsuarioDAO;
import com.sisgesbib.dao.beans.EmpresaBean;
import com.sisgesbib.dao.beans.SedeBean;
import com.sisgesbib.dao.beans.SesionUsuarioBean;
import com.sisgesbib.dao.beans.UsuarioBean;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author despantoso
 */
public class LoginMB {

    /** Creates a new instance of LoginMB */
    public LoginMB() {
    }

    public void loginUsuario() {
        try {
            UsuarioBean userBeanReq = (UsuarioBean) FacesContext.getCurrentInstance().
                    getExternalContext().getRequestMap().get("usuarioBean");

            UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
            UsuarioBean userBean = userDAO.loginUsuario(userBeanReq);

            if (userBean == null) {
                //validando que e login sea correcto
                userBean = userDAO.validaLogin(userBeanReq);
                // si userBean esta vacio entonces el username no existe
                if (userBean == null) {
                    Log.logger.debug("[Login]-Usuario y contraseña incorrectos");
                    String error = MensajesUtil.getProperty("messageErrorContextIncorrectUserAndPassword");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                    FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                } else {
                    /*
                    Si entro aqui el username existe, pero le mostraremos un mensaje de acuerdo
                    al estado del usuario
                     */
                    if (userBean.getEstado().equals("B")) {
                        Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] Cuenta bloqueada");
                        String error = MensajesUtil.getProperty("messageErrorContextBlockedUser");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                        FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                    } else if (userBean.getEstado().equals("A")) {
                        /*
                         * Si el estado del usuario es activo entonces
                         *sumamos en uno al campo invalidAttemps de la tabla secUser de la base de datos
                         */
                        userBean.setIntInvalidos(userBean.getIntInvalidos() + 1);
                        userDAO.invalidAttemps(userBean);
                        Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] No accedio al sistema por contraseña equivocada");

                        String error;
                        error = MensajesUtil.getProperty("messageErrorContextWrongPassword");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                        FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);

                        /*
                         * bloqueamos al usuario si los intentos invalidos llegan al maximo permitido obtenido de GeneralParams
                         * y le informamos su estado
                         */
                        int maxInvalidAttemps = Integer.parseInt(GeneralUtil.getProperty("maxInvalidAttemps"));

                        if (userBean.getIntInvalidos() == maxInvalidAttemps) {
                            userBean.setEstado("B");
                            userDAO.usuarioUpdateStatus(userBean);
                            Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] Bloqueado al llegar al maximo de intentos validos");

                            error = MensajesUtil.getProperty("messageErrorContextUserWasBloqued");
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                            FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                        } else {
                            /*
                             *Si sus invalidAttemps no llegan al maximo permitido, le comunicamos los intentos que le quedan
                             */
                            error = new StringBuffer().append(maxInvalidAttemps - userBean.getIntInvalidos()).append(" ").append(MensajesUtil.getProperty("messageErrorContextRemainingAttempts")).toString();
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                            FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                        }
                    } else if (userBean.getEstado().equals("I")) {
                        Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] Cuenta inactiva");
                        String error = MensajesUtil.getProperty("messageErrorContextIncorrectUserAndPassword");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                        FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                    }
                }
            } else {
                /*
                si entro aqui el usuario y contraseña son correctos
                comprobamos el estado del usuario
                 */
                if (userBean.getEstado().equals("A")) {
                    if (userBean.getIntInvalidos() > 0) {
                        userBean.setIntInvalidos(0);
                        userDAO.invalidAttemps(userBean);
                        Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] Intentos ivalidos reseteados");
                    }
                    EmpresaDAO empresaDAO = DAOFactory.getEmpresaDAO();

                    Log.logger.debug("[Usuario : " + userBean.getUsuarioId() + "] Usuario y contraseña correctos");

                    Map mapCompanyForUser = empresaDAO.getCompanyAssignedForUser(userBean.getUsuarioId());
                    Log.logger.info("Usuario : " + userBean.getUsuarioId() + "] El tamaño de la lista de empresas asignadas al usuario : " + mapCompanyForUser.size());

                    FacesContext.getCurrentInstance().
                            getViewRoot().getViewMap().put("viewLstCompany", mapCompanyForUser);

                    GeneralUtil.removeVewMap("viewLstSede");

                    FacesContext.getCurrentInstance().
                            getExternalContext().getRequestMap().put("usuarioBean", userBean);

                } else if (userBean.getEstado().equals("B")) {
                    Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] No accedio al sistema por cuenta bloqueada");
                    String error = MensajesUtil.getProperty("messageErrorContextBlockedUser");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                    FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                } else if (userBean.getEstado().equals("I")) {
                    Log.logger.info("[Usuario : " + userBean.getUsuarioId() + "] No accedio al sistema por cuenta inactiva");
                    String error = MensajesUtil.getProperty("messageErrorContextIncorrectUserAndPassword");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
                    FacesContext.getCurrentInstance().addMessage("frmLogin:btnSignIn", message);
                }
            }
        } catch (Exception e) {
            Log.logger.error("Error en el login", e);
        }
    }

    public void loadSedeByEmpAndUsu() {
        try { //
            SedeDAO sedeDAO = DAOFactory.getSedeDAO();

            EmpresaBean empresaBean = (EmpresaBean) FacesContext.getCurrentInstance().
                    getExternalContext().getRequestMap().get("empresaBean");
            int usuarioId = Integer.parseInt(FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("userId").toString());

            if (empresaBean.getEmpresaId() == -1) {
                FacesContext.getCurrentInstance().
                        getViewRoot().getViewMap().remove("viewLstSede");
                return;
            }
            Map listaSedes = sedeDAO.getSedeByCompanyAndUser(usuarioId, empresaBean.getEmpresaId());
            Log.logger.debug("[Usuario : " + usuarioId + "] El tamaño de la lista de sedes asignadas al usuario por la empresa " + empresaBean.getEmpresaId() + " es : " + listaSedes.size());

            FacesContext.getCurrentInstance().
                    getViewRoot().getViewMap().put("viewLstSede", listaSedes);

        } catch (Exception e) {
            Log.logger.error("Error cargando sedes", e);
        }
    }

    public String accessToSystem() {
        try {
            UsuarioDAO usuDAO = DAOFactory.getUsuarioDAO();

//            EmpresaBean empresaBean = (EmpresaBean) FacesContext.getCurrentInstance().
//                    getExternalContext().getRequestMap().get("empresaBean");

            SedeBean sedeBean = (SedeBean) FacesContext.getCurrentInstance().
                    getExternalContext().getRequestMap().get("sedeBean");

            UsuarioBean userBean = (UsuarioBean) FacesContext.getCurrentInstance().
                    getExternalContext().getRequestMap().get("usuarioBean");


            SesionUsuarioBean sesionUsuario = usuDAO.getInfoForUserSession(userBean.getUsuarioId(), sedeBean.getSedeId());

            sesionUsuario.setUsuarioId(userBean.getUsuarioId());
            sesionUsuario.setLogin(userBean.getLogin());
            sesionUsuario.setNombre(userBean.getNombre());
            sesionUsuario.setApePaterno(userBean.getApePaterno());
            sesionUsuario.setApeMaterno(userBean.getApeMaterno());

            Log.logger.info("[Usuario : " + sesionUsuario.getUsuarioId() + "] Accedio correctamente al sistema, los datos de su sesion : " + sesionUsuario.toString());

            GeneralUtil.putSessionMap("sesionUsuarioBean", sesionUsuario);

//            FacesContext context = FacesContext.getCurrentInstance();
//            NavigationHandler handler = context.getApplication().getNavigationHandler();

//            if (handler instanceof ConfigurableNavigationHandler) {
//                ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) handler;
//                NavigationCase navCase = 
//                        navigationHandler.getNavigationCase(context, null, "welcome");
//                return navCase.getToViewId(context);
//            }

            GeneralUtil.removeSessionMap("menuMB");
            return "successLogin";
        } catch (Exception e) {
            Log.logger.error("Error al acceder al sistema", e);
            return null;
        }
    }
    

    public void logOff() {
        try {
//            FacesContext context = FacesContext.getCurrentInstance();
//            ExternalContext externalContext = context.getExternalContext();
//            Object session = externalContext.getSession(false);
//            HttpSession httpSession = (HttpSession) session;
//            httpSession.invalidate();
            
            GeneralUtil.removeSessionMap("sesionUsuarioBean");
        } catch (Exception e) {
            Log.logger.error("[LogOff] " + Log.getStackTrace(e));
        }
    }
}
