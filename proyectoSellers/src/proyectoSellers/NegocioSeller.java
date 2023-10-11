package proyectoSellers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class NegocioSeller {

	private JFrame frmSellers;
	private String sellerCode;
	private String firstName;
	private String emailAddress;
	private JTextField txtSellerCode;
	private JTextField txtFirstName;
	private JTextField txtEmailAddress;


	

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public JFrame getFrmSellers() {
		return frmSellers;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmailAddres() {
		return emailAddress;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NegocioSeller window = new NegocioSeller();
					window.frmSellers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NegocioSeller() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSellers = new JFrame();
		frmSellers.setBounds(100, 100, 450, 300);
		frmSellers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSellers.getContentPane().setLayout(null);

		JLabel lblSellerCode = new JLabel("Seller code:");
		lblSellerCode.setBounds(57, 33, 95, 14);
		frmSellers.getContentPane().add(lblSellerCode);

		txtSellerCode = new JTextField();		
		txtSellerCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtSellerCode.getText().length()>=5) {
					e.consume();
					JOptionPane.showMessageDialog(null, "La longitud máxima es de 5");
				}
			}
		});
		txtSellerCode.setBounds(178, 30, 115, 20);
		frmSellers.getContentPane().add(txtSellerCode);
		txtSellerCode.setColumns(10);

		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(57, 64, 95, 14);
		frmSellers.getContentPane().add(lblFirstName);

		txtFirstName = new JTextField();
		txtFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtFirstName.getText().length()>=50) {
					e.consume();
					JOptionPane.showMessageDialog(null, "La longitud máxima es de 50");
				}
			}
		});
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(178, 61, 115, 20);
		frmSellers.getContentPane().add(txtFirstName);

		JLabel lblEmailAddress = new JLabel("Email address:");
		lblEmailAddress.setBounds(57, 92, 95, 14);
		frmSellers.getContentPane().add(lblEmailAddress);

		txtEmailAddress = new JTextField();
		txtEmailAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtEmailAddress.getText().length()>=50) {
					e.consume();
					JOptionPane.showMessageDialog(null, "La longitud máxima es de 50");
				}
			}
		});
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(178, 89, 115, 20);
		frmSellers.getContentPane().add(txtEmailAddress);

		JButton btnSaveSeller = new JButton("Save Seller");
		btnSaveSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(txtSellerCode.getText().isEmpty() || txtFirstName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El código y el nombre son obligatorios");
				}else {	 
					
				 if(saveSeller()) {
					 limpiarCampos();
				 }				 
				}			
			}
		});
		btnSaveSeller.setBounds(44, 163, 89, 23);
		frmSellers.getContentPane().add(btnSaveSeller);

		JButton btnFindSeller = new JButton("Find Seller");
		btnFindSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(txtSellerCode.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingresa el código a buscar");
			}else {
				findSeller();
			}}
		});
		btnFindSeller.setBounds(159, 163, 89, 23);
		frmSellers.getContentPane().add(btnFindSeller);

		JButton btnUpdateSeller = new JButton("Update Seller");
		btnUpdateSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtSellerCode.getText().isEmpty() || txtFirstName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El código y el nombre son obligatorios");
				}else {	
					
				 if(updateSeller()) {
					 limpiarCampos();
				 }				 
				}
				
			}
		});
		btnUpdateSeller.setBounds(273, 163, 132, 23);
		frmSellers.getContentPane().add(btnUpdateSeller);

		JButton btnDeleteSeller = new JButton("Delete Seller");
		btnDeleteSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSellerCode.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Llena el campo Seller code");
				}else if(deleteSeller()) {
					limpiarCampos();
				}
				
				
			}
		});
		btnDeleteSeller.setBounds(57, 199, 132, 23);
		frmSellers.getContentPane().add(btnDeleteSeller);
	}

	public void limpiarCampos() {
		txtSellerCode.setText("");
		txtFirstName.setText("");
		txtEmailAddress.setText("");
	}
	
	
	
		public boolean saveSeller() {
		sellerCode = txtSellerCode.getText();
		firstName = txtFirstName.getText();
		emailAddress = txtEmailAddress.getText();
		DataSeller ds = new DataSeller();
		return ds.saveSeller(this);
	}
		
		
		
		
		
		
	public boolean findSeller() {
		DataSeller ds = new DataSeller();
		sellerCode = txtSellerCode.getText();
		boolean encontrado = ds.findSeller(this);
		txtFirstName.setText(firstName);
		txtEmailAddress.setText(emailAddress);		
		return encontrado;
	}
	public boolean updateSeller() {
		sellerCode = txtSellerCode.getText();
		firstName = txtFirstName.getText();
		emailAddress = txtEmailAddress.getText();
		DataSeller ds = new DataSeller();
		return ds.updateSeller(this);
	}
	public boolean deleteSeller() {
		sellerCode = txtSellerCode.getText();
		DataSeller ds = new DataSeller();
		
		return ds.deleteSeller(this);
	}
}






