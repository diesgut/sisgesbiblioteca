package com.sisgesbib.util;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitSystemResources extends HttpServlet implements Servlet {

    @Override
    public void init() throws ServletException {
        try {
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            SpringWebApplicationContext.getInstance().setWebApplicationContext(wac);

//            PropertyConfigurator.configure("/SisGesBibLog4j.properties");
             PropertyConfigurator.configure(GeneralUtil.getPropertyFile(GeneralUtil.getProperty("LogConfigFile")));
            //Propiedades.inicializaConObjeto(SpringWebApplicationContext.getInstance().getBean("propiedades"));

            System.out.println("*** sistema levantado");

        } catch (Throwable t) {
            System.out.println("***Error inicializando sistema***");
            t.printStackTrace();
        }

        super.init();
    }
}
