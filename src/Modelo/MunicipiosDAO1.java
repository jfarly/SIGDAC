/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.MunicipiosVO;
import app.conexionppi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author jsanchez
 */
public class MunicipiosDAO1 {

    public void llenarmunicipios(JComboBox box, int iddepart) {
        DefaultComboBoxModel value;
        conexionppi conexion = new conexionppi();
        PreparedStatement ps;
        ResultSet rs;

        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tblmunicipios WHERE iddepartamento=?");
            ps.setInt(1, iddepart);
            rs = ps.executeQuery();

            value = new DefaultComboBoxModel();
            box.setModel(value);
            value.addElement(new MunicipiosVO(0, "Seleccione un Municipio"));
            while (rs.next()) {
                value.addElement(new MunicipiosVO(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }
    }

    public void buscarmunicipios(int Mpio, int Depar, JComboBox box) {
        DefaultComboBoxModel value;
        conexionppi conexion = new conexionppi();
        PreparedStatement ps1;
        PreparedStatement ps;
        ResultSet rs;
        ResultSet rs1;
        
        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps1 = Rconectar.prepareStatement("SELECT * FROM tblmunicipios WHERE idmunicipio=?");
            ps1.setInt(1, Mpio);
            rs1 = ps1.executeQuery();
            
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while (rs1.next()) {
                value.addElement(new MunicipiosVO(rs1.getInt(1), rs1.getString(2)));
            }
            ps = Rconectar.prepareStatement("SELECT * FROM tblmunicipios WHERE iddepartamento=?");
            ps.setInt(1, Depar);
            //JOptionPane.showMessageDialog(null,"Departamento " + Depar);
            rs = ps.executeQuery();
            while (rs.next()) {
                value.addElement(new MunicipiosVO(rs.getInt(1), rs.getString(2)));
            }
            
        } catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }      
    }
    public void buscarcodigomunicipios(String Munic, int idMpio) {
        conexionppi conexion = new conexionppi();
        PreparedStatement ps;
        ResultSet rs;
                
        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT idmunicipio, municipio FROM tblmunicipios WHERE municipio=?");
            ps.setString(1, Munic);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                idMpio = (rs.getInt(1));
            }
        //    JOptionPane.showMessageDialog(null,"Id Municipio " + idMpio);
                       
        } catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }      
    }
}
