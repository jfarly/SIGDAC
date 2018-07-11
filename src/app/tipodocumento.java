/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.conexionppi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


/**
 *
 * @author jsanchez
 */
public class tipodocumento {

    private int idtipodocumento;
    private String tipodocumento;

    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String toString() {
        return this.tipodocumento;
    }

    public Vector<tipodocumento> mostrartipodocumento() {
        PreparedStatement ps;
        ResultSet rs;
        conexionppi conexion = new conexionppi();

        Vector<tipodocumento> datos = new Vector<tipodocumento>();
        tipodocumento dat = null;

        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT * FROM tbltipodocumentos");

            rs = ps.executeQuery();

            dat = new tipodocumento();
            dat.setIdtipodocumento(0);
            dat.setTipodocumento("Seleccione un tipo documento..");
            datos.add(dat);

            while (rs.next()) {
                dat = new tipodocumento();  
                dat.setIdtipodocumento(rs.getInt("idtipodocumento"));
                dat.setTipodocumento(rs.getString("tipodocumento"));
                datos.add(dat);
            }

        } catch (SQLException e) {
            System.err.println("Error consulta :" + e.getMessage());
        }
        return datos;
    }
}
