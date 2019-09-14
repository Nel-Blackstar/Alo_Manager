package com.live.reports;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.stereotype.Service;

import com.live.paie.entities.BulletinPaie;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BulletinsReportService {
	public void generateBulletinsPdfReport(List<BulletinPaie> bulletinList) {
        try {
            // Compile the Jasper report from .jrxml to .japser
            //JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/reports/employee-rpt.jrxml");
            InputStream bulletinsReportStream = getClass().getResourceAsStream("/static/reports/bulletins.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(bulletinsReportStream);
            JRSaver.saveObject(jasperReport, "bulletins.jasper");
            // Get your data source
            JasperPrint jasperPrint = pointCommun(jasperReport, bulletinList);
            /* Export the report to a PDF file*/
            JasperExportManager.exportReportToPdfFile(jasperPrint,  System.getProperty("user.home")+"/alo/rapports/bulletin.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	private JasperPrint pointCommun(JasperReport jasperReport, List<BulletinPaie> bulletins) throws JRException {
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(bulletins);
        Map<String, Object> parameters = new HashMap<>();
        return JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
    }
	

}
