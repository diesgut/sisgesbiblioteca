/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.dao.MateriaDAO;
import com.sisgesbib.dao.TextoDAO;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Diego
 */
public class TextForMatterReportMB {

    MateriaDAO materiaDAO = null;
    TextoDAO textoDAO = null;
    private String initPage;

    /** Creates a new instance of TextForMatterReportMB */
    public TextForMatterReportMB() {
        try {
            materiaDAO = DAOFactory.getMateriaDAO();
            textoDAO = DAOFactory.getTextoDAO();
            if (GeneralUtil.getViewMap("lstMatterResume") == null) {
                listaMatterResume();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }

    }

    public void listaMatterResume() throws SQLException {
        List<Map> lstMatterResume = materiaDAO.getLstMatterResume(GeneralUtil.getSessionUser().getSedeId());
        Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                + "El tamaño de la lista del resumen de materias es ; " + lstMatterResume.size());
        
        Map totalsMatterResume=materiaDAO.totalsMatterResume(GeneralUtil.getSessionUser().getSedeId());
        
        GeneralUtil.putViewMap("lstMatterResume", lstMatterResume);
        GeneralUtil.putViewMap("viewMptotalsMatterResume", totalsMatterResume);
    }

    public void selectMatter(ActionEvent event) {
        try {
            //materiaId nombre
            Map matterSelected = (Map) event.getComponent().getAttributes().get("matterSelected");
//        System.out.println("el id de la materia es "+matterSelected.get("materiaId"));
//        System.out.println("el nombre es "+matterSelected.get("nombre"));
            List<Map> lstTextForMatter = textoDAO.getListTextForMatter(GeneralUtil.getSessionUser().getSedeId(), Integer.parseInt(matterSelected.get("materiaId").toString()));
            Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                    + "El tamaño de la lista de textos por materia es ; " + lstTextForMatter.size());
            GeneralUtil.putViewMap("lstTextForMatter", lstTextForMatter);
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
