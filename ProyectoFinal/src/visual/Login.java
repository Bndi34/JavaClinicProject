package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.image.impl.IntArgb;

import logico.Admin;
import logico.Hospital;
import logico.Secretaria;
import logico.Usuario;
import visualRegistros.RegistrarEnfermedad;
import visualRegistros.RegistrarUsuario;

import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import visualRegistros.RegistrarUsuario;
import java.awt.Dialog.ModalityType;
import java.awt.Font;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JTextField txtPassword;
	private String user;
	private String password;
	private Usuario usu = null;
	private Admin adm = null;
	private boolean AdminCheck = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setModal(true);
		try {
			Hospital.load();
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		//Pendiente sistema para revisar que haya algun admin registrado.
		//si no hay, se crea el admin admin
		
		if (!Hospital.getInstance().adminFound())
		{
			Usuario aux = new Admin("USR-0","admin","admin" ,"N/A","admin");
			Hospital.getInstance().getMisCuentas().add(aux);
		}
		
		
		setTitle("Centro M\u00E9dico");
		setBounds(100, 100, 467, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblUser = new JLabel("C\u00E9dula");
			lblUser.setBounds(72, 118, 56, 16);
			contentPanel.add(lblUser);
		}
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(72, 171, 99, 16);
		contentPanel.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(72, 134, 318, 22);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(72, 193, 318, 22);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		Date tempDate = new Date();
		System.out.println("Hora: " + tempDate.getHours());
		
		JLabel lblBienvenida = new JLabel();
		
		if (tempDate.getHours() > 5 && tempDate.getHours() <= 12 )
		{
			lblBienvenida.setText("Buen D\u00EDa");
		}
		else if (tempDate.getHours() > 12 && tempDate.getHours() <= 18)
		{
			lblBienvenida.setText("Buenas Tardes");
		}
		else 
		{
			lblBienvenida.setText("Buenas Noches");
		}
		
		//lblBienvenida = new JLabel("Buen D\u00EDa");
		lblBienvenida.setFont(new Font("Impact", Font.ITALIC, 36));
		lblBienvenida.setBounds(105, 13, 285, 57);
		contentPanel.add(lblBienvenida);
		
		JLabel lblLogIn = new JLabel("Iniciar Sesi\u00F3n");
		lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLogIn.setBounds(53, 89, 118, 16);
		contentPanel.add(lblLogIn);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton adminLogin = new JButton("adminLogin");
			adminLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Admin temp = new Admin("Temp-0", "admin", "admin", "admin", "admin");
					Dashboard adminDashboard = new Dashboard(temp);
					adminDashboard.setVisible(true);
					dispose();
					
				}
			});
			buttonPane.add(adminLogin);
			{
				JButton btnIniciar = new JButton("Iniciar Sesi\u00F3n");
				btnIniciar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						user = txtUser.getText();
						password = txtPassword.getText();
						
						try {
							/*
							Admin adm = (Admin) Hospital.getInstance().buscarUsuarioByCode("USR-0");  //buscarAdminByUser(user);
							
							if (adm != null)
								if (adm.getContrasenia().equalsIgnoreCase(password))
								{
								SecretariaMenu secrMenu = new SecretariaMenu(user, null);
								secrMenu.setVisible(true);
								dispose();
								}
							*/
							
							usu = Hospital.getInstance().buscarUsuarioByCedula(user);
							
							if (usu.getContrasenia().equalsIgnoreCase(password))
							{
								
									Dashboard dash = new Dashboard(usu);
									dash.setVisible(true);
									dispose();

							}
							/*else {

									System.out.println(((Secretaria) usu).getDependiente());
									SecretariaMenu secrMenu = new SecretariaMenu(usu.getCodigo(), ((Secretaria) usu).getDependiente());
									secrMenu.setVisible(true);
									dispose();

							}		*/					
									
							}
							
							
						 catch (Exception nullException) {
							
							JOptionPane.showMessageDialog(null, "Debe ingresar sus datos", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
					}
				});
				btnIniciar.setActionCommand("OK");
				buttonPane.add(btnIniciar);
				getRootPane().setDefaultButton(btnIniciar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
	
	public boolean isAdmin()
	{
		return this.AdminCheck;
	}
	
	public Usuario getUsuario ()
	{
		return this.usu;
	}
	
	public Admin getAdmin()
	{
		return this.adm;
	}
}
