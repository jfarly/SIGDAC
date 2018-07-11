/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.TipoEstadoContractualVO;
import app.conexionppi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author jsanchez
 */
public class TipoEstadoContractualDAO {
    public void LlenarEstadoContractual(JComboBox box){
        DefaultComboBoxModel value;
        value = null;
        conexionppi conexion = new conexionppi();
        PreparedStatement ps;
        ResultSet rs;
        
     //   value.addElement(new TipoDocumentosVO(0,"Seleccione un tipo de documento"));
         try{
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tblestadoslaborales");
            rs = ps.executeQuery();
            
            value = new DefaultComboBoxModel();
            box.setModel(value);
            value.addElement(new TipoEstadoContractualVO(0, "Seleccione un estado laboral"));
            while(rs.next()){
                value.addElement(new TipoEstadoContractualVO(rs.getInt(1),rs.getString(2)));
            }
        }catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }
    }
}
