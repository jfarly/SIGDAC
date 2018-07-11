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
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author jsanchez
 */
public class MunicipiosDAO {
    
    private conexionppi conexion = new conexionppi();
    private PreparedStatement ps;
    private ResultSet rs;
    private MunicipiosVO selectedItem;
    private Vector<MunicipiosVO> datos = new Vector<MunicipiosVO>();
    private Vector<ListDataListener> list = new Vector<ListDataListener>();
    private MunicipiosVO dat = null;
    
    
    
    public Vector<MunicipiosVO> mostrarMunicipios(Integer iddepartamento) {
       try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tblmunicipios WHERE iddepartamento=?");
            ps.setInt(1, iddepartamento);
            rs = ps.executeQuery();

            dat = new MunicipiosVO();
            dat.setIdmunicipio(0);
            dat.setMunicipio("Seleccione un Municipio");
            datos.add(dat);

            while (rs.next()) {
                dat = new MunicipiosVO();
                dat.setIdmunicipio(rs.getInt("idmunicipio"));
                dat.setMunicipio(rs.getString("municipio"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error Consulta :" + ex.getMessage());
        }
        return datos;
    }
    public MunicipiosVO searchSelectedItem(Integer Mpio) {
        for (MunicipiosVO o : datos) {
            if (Mpio.equals(o.getIdmunicipio())) {
                return o;
            }
        }
        return null;
    }
    public MunicipiosVO searchSelectedItem(String SMpio) {
        for (MunicipiosVO o : datos) {
            if (SMpio.equals(o.getMunicipio())) {
                return o;
            }
        }
        return null;
    }
    public void setSelectedItem(Object anItem) {
        selectedItem = anItem instanceof MunicipiosVO ? (MunicipiosVO) anItem : null;
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

    public Integer getSelectedIdMunicipio() {
        return selectedItem == null ? null : selectedItem.getIdmunicipio();
    }

    public String getSelectedMunicipio() {
        return selectedItem == null ? null : selectedItem.getMunicipio();
    }
}
