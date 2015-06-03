/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Unknown
 */
public class Conexion_Postgresql {
    String url="jdbc:postgresql://localhost:5432/filmstore";
    
    public Connection getconexion(){
        Connection cn=null;
        try{
            Class.forName("org.postgresql.Driver");
            cn=DriverManager.getConnection(url,"postgres","postgresql");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return cn;
    }
    
   
}