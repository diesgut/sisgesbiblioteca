package com.sisgesbib.util;

import com.sisgesbib.dao.beans.SesionUsuarioBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;
import javax.faces.context.FacesContext;

public class GeneralUtil {

    private static Map<String, String> parameterList = null;
    private static Properties prop = null;
    private static Properties propLog = null;
    private static Properties propPathMenues = null;
    public static final SimpleDateFormat sdfParseLong = new SimpleDateFormat(MensajesUtil.getProperty("dateParsePatternLong"));

    public static String getProperty(String key) {
        if (prop == null) {
            prop = getPropertyFile();
        }

        return prop.getProperty(key);
    }

    public static String getPropertyPathMenu(String key) {
        if (propPathMenues == null) {
            propPathMenues = getPropertyMenuPathFile();
        }
        return propPathMenues.getProperty(key);
    }

    private static Properties getPropertyFile() {
        //InputStream is = null;
        try {
            //is = new FileInputStream("d:/Intervision/CustomerRegistration/src/java/GeneralParams.properties");
            //prop.load(is);
            prop = new Properties();
            System.out.println("##################### cargara "+GeneralUtil.class.getResourceAsStream("/GeneralParams.properties"));
            prop.load(GeneralUtil.class.getResourceAsStream("/GeneralParams.properties"));
            return prop;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    private static Properties getPropertyMenuPathFile() {
        //InputStream is = null;
        try {
            //is = new FileInputStream("d:/Intervision/CustomerRegistration/src/java/GeneralParams.properties");
            //prop.load(is);
            prop = new Properties();
            prop.load(GeneralUtil.class.getResourceAsStream("/PathMenues.properties"));
            return prop;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public static void savePathMenu(String key, String value) {
        try {
            InputStream is = GeneralUtil.class.getResourceAsStream("/PathMenus.properties");
            prop = new Properties();
            prop.load(is);
            is.close();
            prop.setProperty(key, value);

            FileOutputStream os = new FileOutputStream(GeneralUtil.class.getResource("/PathMenus.properties").getPath());
            prop.store(os, null);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> sortHashMap(Map<String, String> input) {
        Map<String, String> tempMap = new HashMap<String, String>();
        for (String wsState : input.keySet()) {
            tempMap.put(input.get(wsState), wsState);
        }
        List<String> mapKeys = new ArrayList<String>(tempMap.keySet());
        List<String> mapValues = new ArrayList<String>(tempMap.values());
        Map sortedMap = new LinkedHashMap();
        TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
        Object[] sortedArray = sortedSet.toArray();
        int size = sortedArray.length;

        for (int i = 0; i < size; i++) {
            sortedMap.put(sortedArray[i], mapKeys.get(mapValues.indexOf(sortedArray[i])));
        }
        return sortedMap;
    }

    public static Map<String, String> ordenarAngelo(Map map) throws SQLException {

        List listCountries = new LinkedList(map.entrySet());

        Collections.sort(listCountries, new Comparator() {

            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) o1).getKey()).compareTo((((Map.Entry) o2).getKey()));
            }
        });

        Map resultCountries = new LinkedHashMap();
        for (Iterator it = listCountries.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            resultCountries.put(entry.getKey(), entry.getValue());
        }

        return resultCountries;
    }

    /*Copiar a su GeneralUtil*/
    public static Properties getPropertyFile(String property) {
        /*Valida si el properties es nulo, en ese caso lee del parametro
        sino retorna el properties ya leido*/
        if (propLog == null) {
            /*lo obtiene en un is para poder validarlo*/
            InputStream is = GeneralUtil.class.getResourceAsStream(property);
            propLog = new Properties();

            if (is != null) {//si lo encontro muestra un mensaje (en syso ya que en este prop esta antes del log)
                try {
                    //si lo encontro muestra un mensaje (en syso ya que en este prop esta antes del log)
                    propLog.load(is);
                } catch (IOException ex) {
                    System.out.println("ERROR: ***" + ex.getMessage());
                }
            } else {//sino lanza una excepcion e imprime una local
                System.out.println("ERROR: ***" + property + " no cargo***");
            }

        }

        return propLog;
    }

    public static Map<String, String> sortMap(Map map) throws SQLException {

        List listCountries = new LinkedList(map.entrySet());

        Collections.sort(listCountries, new Comparator() {

            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) o1).getKey()).compareTo((((Map.Entry) o2).getKey()));
            }
        });

        Map resultCountries = new LinkedHashMap();
        for (Iterator it = listCountries.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            resultCountries.put(entry.getKey(), entry.getValue());
        }

        return resultCountries;
    }

    @SuppressWarnings("unchecked")
    public static void sortList(List lista, final String propiedad) {
        Collections.sort(lista, new Comparator() {

            public int compare(Object obj1, Object obj2) {

                Class clase = obj1.getClass();
                String getter = "get" + Character.toUpperCase(propiedad.charAt(0)) + propiedad.substring(1);
                try {
                    Method getPropiedad = clase.getMethod(getter);

                    Object propiedad1 = getPropiedad.invoke(obj1);
                    Object propiedad2 = getPropiedad.invoke(obj2);

                    if (propiedad1 instanceof Comparable && propiedad2 instanceof Comparable) {
                        Comparable prop1 = (Comparable) propiedad1;
                        Comparable prop2 = (Comparable) propiedad2;
                        return prop1.compareTo(prop2);
                    }//CASO DE QUE NO SEA COMPARABLE
                    else {
                        if (propiedad1.equals(propiedad2)) {
                            return 0;
                        } else {
                            return 1;
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    public static SesionUsuarioBean getSessionUser() {
        SesionUsuarioBean sesionUsuario = (SesionUsuarioBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioBean");
        return sesionUsuario;
    }

    public static Object getRequestMap(String name) {
        return FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().get(name);
    }

    public static String getParameterMap(String name) {
        String param = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get(name);
        if (param != null && param.trim().length() > 0) {
            return param;
        } else {
            return null;
        }
    }

    public static Object getViewMap(String name) {
        return FacesContext.getCurrentInstance().
                getViewRoot().getViewMap().get(name);
    }

    public static Object getSessionMap(String name) {
        return FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get(name);
    }

    public static void putRequestMap(String name, Object object) {
        FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().put(name, object);
    }

    public static void putViewMap(String name, Object object) {
        FacesContext.getCurrentInstance().
                getViewRoot().getViewMap().put(name, object);
    }

    public static void putSessionMap(String name, Object object) {
        FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().put(name, object);
    }

    public static void removeRequestMap(String name) {
        FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().remove(name);
    }

    public static void removeVewMap(String name) {
        FacesContext.getCurrentInstance().
                getViewRoot().getViewMap().remove(name);
    }

    public static void removeSessionMap(String name) {
        FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().remove(name);
    }
}
