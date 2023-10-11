package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Seconecta {
      private  Connection con;
        
  public Connection conectar(){
    try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto510","root","");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null,"Error al conectar"+e);
	}	
    return con;
    }
}
