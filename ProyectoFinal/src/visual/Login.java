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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import visualRegistros.RegistrarUsuario;
import java.awt.Dialog.ModalityType;

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
		setBounds(100, 100, 467, 245);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("C\u00E9dula");
			lblUser.setBounds(12, 69, 56, 16);
			contentPanel.add(lblUser);
		}
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(12, 134, 99, 16);
		contentPanel.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(90, 66, 333, 22);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(90, 131, 333, 22);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					RegistrarUsuario regUs = new RegistrarUsuario("Registrar Usuario",null, false);
					regUs.setModal(true);
					regUs.setVisible(true);
					/*
					RegistrarUsuario registro = new RegistrarUsuario("Paciente", null);
					registro.setModal(true);
					registro.setVisible(true);*/
					
				}
			});
			buttonPane.add(btnRegistrar);
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
