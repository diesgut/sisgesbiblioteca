/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.dao.factory;

import com.sisgesbib.dao.AutorDAO;
import com.sisgesbib.dao.CategoriaDAO;
import com.sisgesbib.dao.EditorialDAO;
import com.sisgesbib.dao.EjemplarDAO;
import com.sisgesbib.dao.EmpresaDAO;
import com.sisgesbib.dao.LectorDAO;
import com.sisgesbib.dao.MateriaDAO;
import com.sisgesbib.dao.MenuDAO;
import com.sisgesbib.dao.PaisDAO;
import com.sisgesbib.dao.PerfilDAO;
import com.sisgesbib.dao.PrestamoDAO;
import com.sisgesbib.dao.SedeDAO;
import com.sisgesbib.dao.SolicitudDAO;
import com.sisgesbib.dao.TemaDAO;
import com.sisgesbib.dao.TextoDAO;
import com.sisgesbib.util.SpringWebApplicationContext;
import com.sisgesbib.dao.UsuarioDAO;
import com.sisgesbib.dao.UtilDAO;

/**
 * Permite obtener los DAOs Beans que genera el Spring.
 * @author ymedina
 */
public class DAOFactory {

    public static UsuarioDAO getUsuarioDAO() {
        return (UsuarioDAO) SpringWebApplicationContext.getInstance().getBean("usuarioDAO");
    }

    public static EmpresaDAO getEmpresaDAO() {
        return (EmpresaDAO) SpringWebApplicationContext.getInstance().getBean("empresaDAO");
    }

    public static SedeDAO getSedeDAO() {
        return (SedeDAO) SpringWebApplicationContext.getInstance().getBean("sedeDAO");
    }

    public static AutorDAO getAutorDAO() {
        return (AutorDAO) SpringWebApplicationContext.getInstance().getBean("autorDAO");
    }

    public static PaisDAO getPaisDAO() {
        return (PaisDAO) SpringWebApplicationContext.getInstance().getBean("paisDAO");
    }

    public static TemaDAO getTemaDAO() {
        return (TemaDAO) SpringWebApplicationContext.getInstance().getBean("temaDAO");
    }

    public static MateriaDAO getMateriaDAO() {
        return (MateriaDAO) SpringWebApplicationContext.getInstance().getBean("materiaDAO");
    }

    public static CategoriaDAO getCategoriaDAO() {
        return (CategoriaDAO) SpringWebApplicationContext.getInstance().getBean("categoriaDAO");
    }

    public static MenuDAO getMenuDAO() {
        return (MenuDAO) SpringWebApplicationContext.getInstance().getBean("menuDAO");
    }

    public static PerfilDAO getPerfilDAO() {
        return (PerfilDAO) SpringWebApplicationContext.getInstance().getBean("perfilDAO");
    }

    public static EditorialDAO getEditorialDAO() {
        return (EditorialDAO) SpringWebApplicationContext.getInstance().getBean("editorialDAO");
    }

    public static UtilDAO getUtilDAO() {
        return (UtilDAO) SpringWebApplicationContext.getInstance().getBean("utilDAO");
    }

    public static TextoDAO getTextoDAO() {
        return (TextoDAO) SpringWebApplicationContext.getInstance().getBean("textoDAO");
    }

    public static EjemplarDAO getEjemplarDAO() {
        return (EjemplarDAO) SpringWebApplicationContext.getInstance().getBean("ejemplarDAO");
    }
    
    public static LectorDAO getLectorDAO(){
        return (LectorDAO)SpringWebApplicationContext.getInstance().getBean("lectorDAO");
    }
    
    public static SolicitudDAO getSolicitudDAO(){
        return (SolicitudDAO)SpringWebApplicationContext.getInstance().getBean("solicitudDAO");
    }
    
    public static PrestamoDAO getPrestamoDAO(){
        return (PrestamoDAO)SpringWebApplicationContext.getInstance().getBean("prestamoDAO");
    }
    
}
