package com.sisgesbib.util;

import java.io.IOException;
import java.util.Properties;

public class MensajesUtil {

  private static Properties prop = null;
  private static Properties propEng = null;

  public static String getProperty(String key) {
 //   String locale = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("locale") == null ? "en" : (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("locale");
 String locale="es";
    if (!locale.equals("")) {
      if (locale.equals("es")) {
        if (prop == null) {
          prop = getPropertyFile("es");
        }
        return prop.getProperty(key);
      } else {
        if (propEng == null) {
          propEng = getPropertyFile("en");
        }
        return propEng.getProperty(key);
      }
    } else {
      return "";
    }
  }

  private static Properties getPropertyFile(String locale) {
    try {
      Properties propTemp = new Properties();
     propTemp.load(MensajesUtil.class.getResourceAsStream("/Mensajes_" + locale + ".properties"));
      return propTemp;
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return null;
    }
  }
}
