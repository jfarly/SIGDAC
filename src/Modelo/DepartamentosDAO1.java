/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.DepartamentosVO;
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
public class DepartamentosDAO1 {
    public void llenardepartamentos(JComboBox box){
        DefaultComboBoxModel value;
        conexionppi conexion = new conexionppi();
        PreparedStatement ps;
        ResultSet rs;
        
        
        try{
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tbldepartamentos");
            rs = ps.executeQuery();
            
            value = new DefaultComboBoxModel();
            box.setModel(value);
            value.addElement(new DepartamentosVO(0, "Seleccione un Departamento"));
            while(rs.next()){
                value.addElement(new DepartamentosVO(rs.getInt(1),rs.getString(2)));
            }
        }catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }
        }
                                 //(int Mpio, JComboBox box) {
    public void buscardepartamentos(int Depar, JComboBox box){
        DefaultComboBoxModel value;
        conexionppi conexion = new conexionppi();
        PreparedStatement ps1;
        PreparedStatement ps;
        ResultSet rs;
        ResultSet rs1;
        
        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tbldepartamentos where iddepartamento = ?");
            ps.setInt(1, Depar);
            rs = ps.executeQuery();
            
            value = new DefaultComboBoxModel();
            box.setModel(value);
            
            while (rs.next()) {
                value.addElement(new MunicipiosVO(rs.getInt(1), rs.getString(2)));
            }
            ps1 = Rconectar.prepareStatement("SELECT * FROM tbldepartamentos");
            rs1 = ps1.executeQuery();
                   
            while (rs1.next()) {
                value.addElement(new MunicipiosVO(rs1.getInt(1), rs1.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }      
    }
    
    }