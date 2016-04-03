/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisgesbib.managed.beans;

import com.sisgesbib.beans.filters.PrestamoPaginationBean;
import com.sisgesbib.dao.PrestamoDAO;
import com.sisgesbib.dao.factory.DAOFactory;
import com.sisgesbib.util.GeneralUtil;
import com.sisgesbib.util.Log;
import com.sisgesbib.util.MensajesUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Diego
 */
public class LoanReportMB {

    private String initPage;
    public PrestamoDAO prestamoDAO = null;

    /** Creates a new instance of LoanReportMB */
    public LoanReportMB() {
        try {
            prestamoDAO = DAOFactory.getPrestamoDAO();

            if (GeneralUtil.getViewMap("prestamoPaginationBean") == null) {
                PrestamoPaginationBean presPagBean = new PrestamoPaginationBean();
                presPagBean.setEstado("C");
                presPagBean.setSedeId(GeneralUtil.getSessionUser().getSedeId());
                GeneralUtil.putViewMap("prestamoPaginationBean", presPagBean);
                listaPrestamos();
            }
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }
    }

    public void listaPrestamos() {
        try {
            PrestamoPaginationBean presPagBean = (PrestamoPaginationBean) GeneralUtil.getViewMap("prestamoPaginationBean");
            List<Map> listaPrestamos = prestamoDAO.getListaPrestamos(presPagBean);
            Log.logger.info("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "] "
                    + "El tamaño de la lista de prestamos es ; " + listaPrestamos.size());
            presPagBean.setListaPrestamos(listaPrestamos);
            GeneralUtil.putViewMap("prestamoPaginationBean", presPagBean);
        } catch (Exception e) {
            Log.logger.error("[Usuario : " + GeneralUtil.getSessionUser().getUsuarioId() + "]", e);
        }

    }

    public void exportToExcel() {
        try {

            HttpServletResponse response = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse());
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=ReportePrestamos.xls");
            ServletOutputStream salida = response.getOutputStream();
            WritableWorkbook workbook = Workbook.createWorkbook(salida);

            PrestamoPaginationBean pgnPres = (PrestamoPaginationBean) GeneralUtil.getViewMap("prestamoPaginationBean");

            WritableSheet sheet = workbook.createSheet("Hoja 1", 0);
            WritableCellFormat formato_cab = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE));
            WritableCellFormat formato_der = new WritableCellFormat();
            WritableCellFormat formato_num = new WritableCellFormat(new NumberFormat("###,###,##0.00"));
            WritableCellFormat formato_int = new WritableCellFormat(new NumberFormat("###,###,##0"));
            WritableCellFormat formato_tit = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE));
            WritableCellFormat formato_subtit = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE));

            formato_tit.setAlignment(Alignment.CENTRE);
            formato_subtit.setAlignment(Alignment.LEFT);
            formato_der.setAlignment(Alignment.RIGHT);
            formato_num.setAlignment(Alignment.RIGHT);

            CellView vista_celda = new CellView();
            CellView vista_celda2 = new CellView();

            vista_celda2.setSize(100);
            //-- Formateas Excel
            formato_tit.setBackground(Colour.BLUE_GREY);
            formato_subtit.setBackground(Colour.BLUE_GREY);
            formato_cab.setBackground(Colour.BLUE_GREY);
            formato_cab.setAlignment(Alignment.CENTRE);
            //vista_celda.setAutosize(true);
            vista_celda.setSize(8000);


            SimpleDateFormat dfShort = new SimpleDateFormat(MensajesUtil.getProperty("datePattern"));
            SimpleDateFormat dfLong = new SimpleDateFormat(MensajesUtil.getProperty("datePatternLong"));

            sheet.mergeCells(0, 0, 9, 0);
            sheet.addCell(new Label(0, 0, MensajesUtil.getProperty("tittleLoanReport"), formato_tit));
            sheet.mergeCells(0, 1, 9, 1);
            sheet.mergeCells(0, 2, 9, 2);
            sheet.mergeCells(0, 3, 9, 3);

            String subTittle = MensajesUtil.getProperty("fieldDateFrom") + " " + dfShort.format(pgnPres.getDateFrom()) + " " + MensajesUtil.getProperty("fieldDateTo") + " " + dfShort.format(pgnPres.getDateTo());
            sheet.addCell(new Label(0, 1, subTittle, formato_subtit));

            subTittle = MensajesUtil.getProperty("fieldLoanState") + " : " + (pgnPres.getEstado().equals("C") ? MensajesUtil.getProperty("fieldEnCurso") : MensajesUtil.getProperty("fieldFinalizado"));
            sheet.addCell(new Label(0, 2, subTittle, formato_subtit));

            String loanType = "";
            if (pgnPres.getArgTipoPrestamo() == null || pgnPres.getArgTipoPrestamo().length == 2) {
                loanType = MensajesUtil.getProperty("fieldTextTipoPrestamoS") + " y " + MensajesUtil.getProperty("fieldTextTipoPrestamoD");
            }
            if (pgnPres.getArgTipoPrestamo() == null || pgnPres.getArgTipoPrestamo().length == 0) {
                loanType = MensajesUtil.getProperty("fieldTextTipoPrestamoS") + " y " + MensajesUtil.getProperty("fieldTextTipoPrestamoD");
            }
            if (pgnPres.getArgTipoPrestamo() != null && pgnPres.getArgTipoPrestamo().length == 1) {
                loanType = pgnPres.getArgTipoPrestamo()[0].equals("S") ? MensajesUtil.getProperty("fieldTextTipoPrestamoS") : MensajesUtil.getProperty("fieldTextTipoPrestamoD");
            }
            subTittle = MensajesUtil.getProperty("fieldLoanType") + " : " + loanType;
            sheet.addCell(new Label(0, 3, subTittle, formato_subtit));

            sheet.addCell(new Label(0, 5, MensajesUtil.getProperty("fieldReaderName"), formato_cab));
            sheet.addCell(new Label(1, 5, MensajesUtil.getProperty("fieldTextTitle"), formato_cab));
            sheet.addCell(new Label(2, 5, MensajesUtil.getProperty("fieldAuthors"), formato_cab));
            sheet.addCell(new Label(3, 5, MensajesUtil.getProperty("fieldLoanType"), formato_cab));
            sheet.addCell(new Label(4, 5, MensajesUtil.getProperty("fieldDateTimeStart"), formato_cab));
            sheet.addCell(new Label(5, 5, MensajesUtil.getProperty("fieldDateTimeEnd"), formato_cab));
            sheet.addCell(new Label(6, 5, MensajesUtil.getProperty("fieldVolume"), formato_cab));
            sheet.addCell(new Label(7, 5, MensajesUtil.getProperty("fieldEditionDate"), formato_cab));
            sheet.addCell(new Label(8, 5, MensajesUtil.getProperty("fieldEdition"), formato_cab));
            sheet.addCell(new Label(9, 5, MensajesUtil.getProperty("fieldRenovado"), formato_cab));

            int i = 0;
            for (Map map : pgnPres.getListaPrestamos()) {
                sheet.addCell(new Label(0, (i + 6), map.get("nombreLector").toString()));
                sheet.addCell(new Label(1, (i + 6), map.get("nombreTexto").toString()));
                sheet.addCell(new Label(2, (i + 6), map.get("authors").toString()));
                sheet.addCell(new Label(3, (i + 6), map.get("tipoPrestamo").toString().equals("S") ? MensajesUtil.getProperty("fieldTextTipoPrestamoS") : MensajesUtil.getProperty("fieldTextTipoPrestamoD")));
                sheet.addCell(new Label(4, (i + 6), dfShort.format(dfShort.parse(map.get("fechaInicio").toString()))));
                sheet.addCell(new Label(5, (i + 6), dfShort.format(dfShort.parse(map.get("fechaFin").toString()))));
                sheet.addCell(new jxl.write.Number(6, (i + 6), Integer.parseInt(map.get("tomoTexto").toString()), formato_der));
                sheet.addCell(new Label(7, (i + 6), map.get("añoEdicionTexto").toString(), formato_der));
                sheet.addCell(new jxl.write.Number(8, (i + 6), Integer.parseInt(map.get("edicionTexto").toString()), formato_der));
                sheet.addCell(new Label(9, (i + 6), map.get("renovado").toString().equals("0") ? "NO" : "SI"));
                i++;
            }

            sheet.setColumnView(0, vista_celda);
            sheet.setColumnView(1, vista_celda2);
            sheet.setColumnView(2, vista_celda2);
            sheet.setColumnView(3, vista_celda2);
            sheet.setColumnView(4, vista_celda2);
            sheet.setColumnView(5, vista_celda2);
            sheet.setColumnView(6, vista_celda2);
            sheet.setColumnView(7, vista_celda2);
            sheet.setColumnView(8, vista_celda2);
            sheet.setColumnView(9, vista_celda2);

            workbook.write();
            workbook.close();
            salida.flush();
            salida.close();

            FacesContext faces = FacesContext.getCurrentInstance();
            faces.responseComplete();
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
