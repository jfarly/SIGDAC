/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
     
/**
 *
 * @author jsanchez
 */
public class Classreport {
    public void GenerarReporte(String Reporte) throws net.sf.jasperreports.engine.JRException{
        InputStream inputStream = null;
        conexionppi conexion = new conexionppi();
        String Ruta = "C:\\Users\\jsanchez\\Documents\\NetBeansProjects\\PPI\\"+ Reporte;
        try{
            inputStream = new FileInputStream (Ruta);
            
            //JOptionPane.showMessageDialog(null, Ruta);
            Map parameters = new HashMap();
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            @SuppressWarnings("unchecked")
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,conexion.conectar());
            JasperViewer.viewReport(jasperPrint,false);
            
            
        }catch(FileNotFoundException ex){
            Logger.getLogger(Classreport.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        
    }
            
}
