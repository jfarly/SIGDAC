/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import app.conexionppi;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jsanchez
 */
public class FiltroEmpleados {

    public static void filtroEmpleados() {
        JFrame frame = createFrame();
        TableModel tableModel = createTableModel();
        JTable table = new JTable(tableModel);

        JTextField filterField = UtilidadFiltro.createRowFilter(table);
        JPanel txtBuscar = new JPanel();
        txtBuscar.add(filterField);
        frame.add(txtBuscar, BorderLayout.NORTH);
       
        
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

    private static TableModel createTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList("Documento", "Nombre ", "Apellido","Empresa" ,"Telefono" ,"Municipio", "Departamento"));
        Vector<Vector<Object>> rows = new Vector<>();
        conexionppi conexion = new conexionppi();
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT documentoempleado, nombreempleado, apellidoempleado, tblempleados.nitempresa, tblempresas.tipoempresa, telefono, idmunicipioresidencia, tblmunicipios.municipio, tblmunicipios.iddepartamento, tbldepartamentos.departamento from tblempleados, tblempresas, tblmunicipios, tbldepartamentos WHERE tblempleados.nitempresa = tblempresas.nitempresa AND tblempleados.idmunicipioresidencia = tblmunicipios.idmunicipio and tblmunicipios.iddepartamento = tbldepartamentos.iddepartamento");
            rs = ps.executeQuery();
        
        //DataFactory dataFactory = new DataFactory();
            while (rs.next())  {
                Vector<Object> vectorpro = new Vector<>();
                vectorpro.add(rs.getString("documentoempleado"));
                vectorpro.add(rs.getString("nombreempleado"));
                vectorpro.add(rs.getString("apellidoempleado"));
                vectorpro.add(rs.getString("tipoempresa"));
                vectorpro.add(rs.getString("telefono"));
                vectorpro.add(rs.getString("municipio"));
                vectorpro.add(rs.getString("departamento"));
                rows.add(vectorpro);
            }    
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        
        DefaultTableModel dtm = new DefaultTableModel(rows, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Integer.class : String.class;
            }
        };
        return dtm;
     } 
      

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Filtro Empleados");
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(900, 450));
        return frame;
    }
}
