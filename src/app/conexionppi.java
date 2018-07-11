package app;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jsanchez
 */
public class conexionppi {
    public Connection conectar(){
            Connection Rconectar = null; 
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Rconectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbppi","root","");            
               // JOptionPane.showMessageDialog(null, "Conexion exitosa");
                }   
            catch (ClassNotFoundException | SQLException e){
    
                    JOptionPane.showMessageDialog(null,"Error" +e);
    
                }
            return Rconectar;
        }
}