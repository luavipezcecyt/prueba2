package proyectoSellers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DataSeller {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private Connection conectar() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoseller","root","");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al conectar: " +e);
		}
		return con;
	}
	
	public boolean saveSeller(NegocioSeller ns) {
		boolean save = false;
		String query ="insert into sellers values(?,?,?)";
		conectar();
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, ns.getSellerCode());
			ps.setString(2, ns.getFirstName());
			ps.setString(3, ns.getEmailAddres());
			int guarda = ps.executeUpdate();
			if(guarda>0) {
				JOptionPane.showMessageDialog(null, "Seller saved");
				save = true;
			}else {
				JOptionPane.showMessageDialog(null, "Error on save");				
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error on save: " +e);
		}
		return save;
	}
	
	
	public boolean updateSeller(NegocioSeller ns) {
		boolean updated = false;
		String query ="update sellers set firstname=?, emailaddres=? where sellercode=?";
		conectar();
		try {
			ps = con.prepareStatement(query);
			ps.setString(3, ns.getSellerCode());
			ps.setString(1, ns.getFirstName());
			ps.setString(2, ns.getEmailAddres());
			int actualiza = ps.executeUpdate();
			if(actualiza>0) {
				JOptionPane.showMessageDialog(null, "Seller updated");
				updated = true;
			}else {
				JOptionPane.showMessageDialog(null, "Error on update");				
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error on update: " +e);
		}
		return updated;
	}
	
	
	
	public boolean findSeller(NegocioSeller ns) {
		conectar();
		String sql ="select * from sellers where sellercode=?";
		boolean encontrado =false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ns.getSellerCode());
			rs = ps.executeQuery();
			if(rs.next()) {
				ns.setFirstName(rs.getString(2));
				ns.setEmailAddress(rs.getString(3));
				encontrado = true;
			}else {
				JOptionPane.showMessageDialog(null, "No seller found");
			}			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"No seller found " +e);
		}	
		return encontrado;
	}
	public boolean deleteSeller(NegocioSeller ns) {
		conectar();
		String sql ="delete from sellers where sellercode=?";
		boolean deleted =false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ns.getSellerCode());
			int elimindo = ps.executeUpdate();
			if(elimindo>0) {
				JOptionPane.showMessageDialog(null, "Seller deleted");			
				deleted = true;
			}else {
				JOptionPane.showMessageDialog(null, "error on delete");
			}			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"error on delete " +e);
		}	
		return deleted;
	}
	
}


