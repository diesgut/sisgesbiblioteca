/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.util;

import com.sisgesbib.dao.beans.SesionUsuarioBean;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego
 */
public class LoggedInCheck implements PhaseListener {

    private final String LOGIN_PAGE = "/login.xhtml";
    private final String INDEX_PAGE="/sisgesbiblioteca/index.jsp";

    @Override
    public void afterPhase(PhaseEvent event) {
        try {

            FacesContext fc = event.getFacesContext();
            String pagina = fc.getViewRoot().getViewId();
      //      System.out.println("la pagina es " + pagina);
//        if (WebXml.getInstance(fc).getFacesResourceKey((HttpServletRequest) fc.getExternalContext().getRequest()) != null
//                || pagina.indexOf("/css/") != -1
//                || pagina.indexOf("/js/") != -1
//                || pagina.indexOf("/images/") != -1
//                || pagina.equals("/BlankPage.jsp")
//                || pagina.equals("/ExpiredSession.jsp")) {
//            return;
//        }
            if (pagina.equals(LOGIN_PAGE) && loggedIn(fc)) {
                NavigationHandler navi = fc.getApplication().getNavigationHandler();
                navi.handleNavigation(fc, null, "successLogin");
                return;
            }
            if(!pagina.equals(LOGIN_PAGE) && !loggedIn(fc)){
//                NavigationHandler navi = fc.getApplication().getNavigationHandler();
//                navi.handleNavigation(fc, null, "logOff");
//                return;
                fc.getExternalContext().redirect(INDEX_PAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private boolean loggedIn(FacesContext ctx) {
        try {
            FacesContext context = ctx;
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
       //     SesionUsuarioBean sessionBean = (SesionUsuarioBean) session.getAttribute("sesionUsuarioBean");

            if (session.getAttribute("sesionUsuarioBean") == null
                    || (session.getAttribute("sesionUsuarioBean") instanceof SesionUsuarioBean) == false) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.logger.error("[Cerrando Sesion] ", e);
            return false;
        }

    }
}
