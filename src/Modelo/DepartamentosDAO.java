/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import app.conexionppi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author jsanchez
 */
public class DepartamentosDAO implements ComboBoxModel {

//    private DefaultComboBoxModel value;
    private conexionppi conexion = new conexionppi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DepartamentosVO selectedItem;
    private Vector<DepartamentosVO> datos = new Vector<DepartamentosVO>();
    private Vector<ListDataListener> list = new Vector<ListDataListener>();
    private DepartamentosVO dat = null;
    

   
    public Vector<DepartamentosVO> mostrarDepartamentos() {
       try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tbldepartamentos");
            rs = ps.executeQuery();

            dat = new DepartamentosVO();
            dat.setIddepartamento(0);
            dat.setDepartamento("Seleccione un Departamento");
            datos.add(dat);

            while (rs.next()) {
                dat = new DepartamentosVO();
                dat.setIddepartamento(rs.getInt("idDepartamento"));
                dat.setDepartamento(rs.getString("Departamento"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error Consulta :" + ex.getMessage());
        }
        return datos;
    }
    public DepartamentosVO searchSelectedItem(Integer Depar) {
        JOptionPane.showMessageDialog(null, Depar);
        for (DepartamentosVO o : datos) {
            if (Depar.equals(o.getIddepartamento())) {
                JOptionPane.showMessageDialog(null, o.getDepartamento());
                return o;
            }
        }
        JOptionPane.showMessageDialog(null, "Codigo Departamento no encontrado o no recibido");
        return null;
        
    }
    public DepartamentosVO searchSelectedItem(String SDepart) {
        for (DepartamentosVO o : datos) {
            if (SDepart.equals(o.getDepartamento())) {
                return o;
            }
        }
        return null;
    }
    public void setSelectedItem(Object anItem) {
        selectedItem = anItem instanceof DepartamentosVO ? (DepartamentosVO) anItem : null;
        for (ListDataListener l : list) {
            l.contentsChanged(new ListDataEvent(this, javax.swing.event.ListDataEvent.CONTENTS_CHANGED, 0, 0));
        }
    }
    public Object getSelectedItem() {
        return selectedItem;
    }

    public int getSize() {
        return datos.size();
    }

    public Object getElementAt(int index) {
        return datos.get(index);
    }

    public void addListDataListener(ListDataListener l) {
        list.add(l);
    }

    public void removeListDataListener(ListDataListener l) {
        list.remove(l);
    }

    public Integer getSelectedIdDepartamento() {
        return selectedItem == null ? null : selectedItem.getIddepartamento();
    }

    public String getSelectedDepartamento() {
        return selectedItem == null ? null : selectedItem.getDepartamento();
    }
    
}
