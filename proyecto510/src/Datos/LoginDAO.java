package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Modelo.Login;

public class LoginDAO {
	Seconecta sc = new Seconecta();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;	
	
	public boolean guardarLogin(Login l) {
		boolean guardado = false;
		String sql = "insert into logins values(0,?,?,?)";
		con  = sc.conectar();
		try {			
			int guardada =0;
			boolean exist = validarUsuario(l.getUsuario());
			if(exist == false) {
				ps = con.prepareStatement(sql);
				ps.setString(1, l.getUsuario());
				ps.setString(2, l.getContrasenia());
				ps.setString(3, l.getEmail());
			 guardada= ps.executeUpdate();
			if( guardada > 0) {
				JOptionPane.showMessageDialog(null, "Guardado");
				       guardado = true;
			}else {
				JOptionPane.showMessageDialog(null, "No Guardado");
			}}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al guardar: " +e);
		}				
		return guardado;
	}

	public boolean validarUsuario(String us) {
		String sql ="select * from logins where usuario=?";
		con = sc.conectar();
		boolean existe = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, us);
			rs = ps.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,"Usa otro usuario ");
				existe = true;
			}
;		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al validar: " +e);			
		}
		return existe;
	}

	public void consultarLogin(Login l) {
		con = sc.conectar();		
		String sql = "select * from logins where usuario =? and contrasenia=? and email=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, l.getUsuario());
			ps.setString(2, l.getContrasenia());
			ps.setString(3, l.getEmail());
			rs = ps.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Bienvenido: "+rs.getString(2));
			}else {
				JOptionPane.showMessageDialog(null, "algo Incorrecto!!!");
			}
		} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,"Error al consultar " +e);
		}
	}
	
       
}
