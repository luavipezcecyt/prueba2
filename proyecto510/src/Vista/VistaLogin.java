package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Datos.LoginDAO;
import Modelo.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VistaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtusuario;
	private JTextField txyContrasenia;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(10, 11, 46, 14);
		contentPane.add(lblid);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 66, 66, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(0, 129, 92, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(10, 198, 46, 14);
		contentPane.add(lblemail);
		
		txtid = new JTextField();
		txtid.setBounds(102, 11, 86, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(102, 63, 86, 20);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		txyContrasenia = new JTextField();
		txyContrasenia.setBounds(102, 126, 86, 20);
		contentPane.add(txyContrasenia);
		txyContrasenia.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(117, 195, 86, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}

		});
		btnGuardar.setBounds(309, 10, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(txtusuario.getText().isEmpty() || txyContrasenia.getText().isEmpty() || txtEmail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Llena todos los campos");
			}else {
				consultar();
			}
			}
		});
		btnConsultar.setBounds(309, 62, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(309, 125, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(323, 178, 89, 23);
		contentPane.add(btnEliminar);
	}
	

	private boolean guardar() {
		Login l = new Login();
		LoginDAO ld = new LoginDAO();		
		l.setUsuario(txtusuario.getText());
		l.setContrasenia(txyContrasenia.getText());
		l.setEmail(txtEmail.getText());
		boolean g =ld.guardarLogin(l);
		return g;
	}
	
	private void consultar() {
		LoginDAO ld = new LoginDAO();
		Login l = new Login();
		l.setUsuario(txtusuario.getText());
		l.setContrasenia(txyContrasenia.getText());
		l.setEmail(txtEmail.getText());
		ld.consultarLogin(l);
	}
	
}
