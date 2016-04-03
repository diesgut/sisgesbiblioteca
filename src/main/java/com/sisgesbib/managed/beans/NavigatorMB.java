/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.util.GeneralUtil;
import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Diego
 */
public class NavigatorMB implements Serializable {

    private String sampleURI;

    /** Creates a new instance of NavigatorMB */
    public NavigatorMB() {
    }

    /**
     * @return the sampleURI
     */
    public String getSampleURI() {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler handler = context.getApplication().getNavigationHandler();

        if (sampleURI == null) {
            if (handler instanceof ConfigurableNavigationHandler) {
                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().
                        getExternalContext().getRequest();
                //   String queryString = GeneralUtil.getParameterMap("page");
                String queryString = req.getParameter("page");
                queryString = queryString == null ? "defaultPage" : queryString;
                System.out.println("query str " + queryString);
                System.out.println("la pagina es " + GeneralUtil.getPropertyPathMenu("usermaintenance/usermaintenance"));
                sampleURI = GeneralUtil.getPropertyPathMenu(queryString);
                // return sampleURI;
                ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) handler;

                NavigationCase navCase = navigationHandler.getNavigationCase(context, null, sampleURI);
                return navCase.getToViewId(context);
            }
        }
        return null;
    }
}
