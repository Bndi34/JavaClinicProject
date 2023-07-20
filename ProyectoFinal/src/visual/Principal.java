package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Hospital;
import logico.Usuario;
import visualRegistros.RegistrarUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import visualRegistros.RegistrarUsuario;

public class Principal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JTextField txtPassword;
	private String user;
	private String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Principal dialog = new Principal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		setTitle("Centro M\u00E9dico");
		setBounds(100, 100, 363, 189);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("C\u00E9dula");
			lblUser.setBounds(12, 23, 56, 16);
			contentPanel.add(lblUser);
		}
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(12, 52, 99, 16);
		contentPanel.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(90, 20, 221, 22);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(90, 49, 221, 22);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegistrarUsuario registro = new RegistrarUsuario("Paciente", 1, null);
					registro.setVisible(true);
					
				}
			});
			buttonPane.add(btnRegistrar);
			{
				JButton btnIniciar = new JButton("Iniciar Sesi\u00F3n");
				btnIniciar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						user = txtUser.getText();
						password = txtPassword.getText();
						
						Usuario usu = Hospital.getInstance().buscarUsuarioByCedula(user);
						
						try {
							System.out.println("Usu: " + usu);
							System.out.println("Usu password: " + usu.getContrasenia());
							System.out.println("Recibido: " + password);
							System.out.println(usu.getContrasenia().equalsIgnoreCase(password));

							if (usu.getContrasenia().equalsIgnoreCase(password))
							{
								Dashboard dash = new Dashboard();
								dash.setVisible(true);
								setVisible(false);
							}
						} catch (Exception nullException) {
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
}
