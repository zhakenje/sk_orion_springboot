package kz.orion.sk_orion_springboot.controller.report;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public abstract class Report {

    private static JasperReport jReport;

    private static JasperViewer jViewer;

    private static JasperPrint jPrint;

    public static void createReport(Connection connect, Map<String, Object> map, InputStream by){
        try {
            jReport = (JasperReport) JRLoader.loadObject(by);
            jPrint = JasperFillManager.fillReport(jReport, map, connect);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showReport(){
        jViewer = new JasperViewer(jPrint, false);
        jViewer.setVisible(true);
    }


}
