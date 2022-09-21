/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.controller.reports;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Edward Zengeni
 */
public class GenericReport {

    public static void handlePDFSubmit(HttpServletRequest request, HttpServletResponse response, JasperPrint jasperPrint) throws JRException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "filename=" + "Employees.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
    
    public static JasperPrint createJSRObject(List<?> item , Map parameters, InputStream stream) throws JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(stream));
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport, parameters, new JRBeanCollectionDataSource(item));
        return jasperPrint;
    }
    
    
}
