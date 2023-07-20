package visualRegistros;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;

import logico.Usuario;

import logico.Hospital;

import logico.Paciente;
import logico.Doctor;
import logico.Secretaria;

import javax.swing.SpinnerDateModel;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class RegistrarUsuario extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	int mode;
	JPanel panel_paciente;
	JPanel panel_doctor;
	JPanel panel_secretaria;
	JRadioButton rdbtnPaciente;
	JRadioButton rdbtnDoctor;
	JRadioButton rdbtnSecretaria;
	private JTextField txtTelefono;
	private JTextField txtDir;
	private Usuario aux;
	private JTextField txtCedula;
	private JTextField txtPassword;
	private JTextField txtArea;
	private JTextField txtSueldo;
	
	public RegistrarUsuario(String title, int mode, Usuario entrada) {
		System.out.println("Registrar IN");
		setResizable(false);
		setBounds(100, 100, 475, 400);
		this.mode = mode;
		setTitle(title);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 439, 138);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(33, 26, 39, 14);
		panel.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 63, 48, 14);
		panel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(235, 63, 58, 14);
		panel.add(lblTelefono);
		
		txtId = new JTextField();
		txtId.setBounds(86, 23, 139, 21);
		txtId.setText("USR-"+String.valueOf(Hospital.getInstance().generadorUsuario));
		txtId.setEditable(false);
		panel.add(txtId);
		txtId.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(86, 60, 139, 21);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(290, 60, 118, 21);
		panel.add(txtTelefono);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(22, 102, 48, 14);
		panel.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(86, 99, 139, 21);
		panel.add(txtCedula);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setBounds(235, 102, 70, 14);
		panel.add(lblContrasenia);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(290, 99, 118, 21);
		panel.add(txtPassword);
		
		panel_paciente = new JPanel();
		panel_paciente.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_paciente.setBounds(10, 219, 439, 104);
		contentPanel.add(panel_paciente);
		panel_paciente.setLayout(null);
		panel_paciente.setVisible(false);
		
		JLabel lblDir = new JLabel("Direcci\u00F3n:");
		lblDir.setBounds(7, 23, 61, 14);
		panel_paciente.add(lblDir);
		
		JLabel lblAlergia = new JLabel("Alergias:");
		lblAlergia.setBounds(222, 23, 54, 14);
		panel_paciente.add(lblAlergia);
		
		JComboBox cbxAlergia = new JComboBox();
		cbxAlergia.setBounds(286, 23, 116, 22);
		panel_paciente.add(cbxAlergia);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(62, 20, 140, 21);
		panel_paciente.add(txtDir);
		
		JLabel lblGenero = new JLabel("G\u00E9nero:");
		lblGenero.setBounds(7, 66, 54, 14);
		panel_paciente.add(lblGenero);
		
		JComboBox cbxSexo = new JComboBox();
		cbxSexo.setBounds(62, 62, 116, 22);
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Hombre", "Mujer"}));
		panel_paciente.add(cbxSexo);
		
		//Falta check de ver si es secretaria
		/*
		JLabel lblSupervisor = new JLabel("Supervisor:");
		lblSupervisor.setBounds(7, 23, 75, 16);
		panel_secretaria.add(lblSupervisor);
		
		
		JComboBox cbxSupervisor = new JComboBox();
		cbxSupervisor.setBounds(62, 20, 224, 22);
		panel_secretaria.add(cbxSupervisor);
		*/
		
		panel_doctor = new JPanel();
		panel_doctor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_doctor.setBounds(10, 219, 439, 104);
		contentPanel.add(panel_doctor);
		panel_doctor.setLayout(null);
		
		JLabel lblAreaMedica = new JLabel("Area M\u00E9dica:");
		lblAreaMedica.setBounds(7, 23, 75, 14);
		panel_doctor.add(lblAreaMedica);
		
		txtArea = new JTextField();
		txtArea.setColumns(10);
		txtArea.setBounds(62, 20, 140, 21);
		panel_doctor.add(txtArea);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(222, 23, 54, 14);
		panel_doctor.add(lblSueldo);
		
		txtSueldo = new JTextField();
		txtSueldo.setColumns(10);
		txtSueldo.setBounds(270, 20, 140, 21);
		panel_doctor.add(txtSueldo);
		
		
		panel_secretaria = new JPanel();
		panel_secretaria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_secretaria.setBounds(10, 219, 439, 104);
		contentPanel.add(panel_secretaria);
		panel_secretaria.setLayout(null);
		
		JPanel panel_tipos = new JPanel();
		panel_tipos.setBorder(new TitledBorder(null, "Tipo de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_tipos.setBounds(10, 160, 439, 48);
		contentPanel.add(panel_tipos);
		panel_tipos.setLayout(null);
		
		rdbtnPaciente = new JRadioButton("Paciente");
		rdbtnPaciente.setSelected(true);
		rdbtnPaciente.setBounds(17, 18, 88, 23);
		panel_tipos.add(rdbtnPaciente);
		rdbtnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Paciente Seleccionado");
				rdbtnPaciente.setSelected(true);
				rdbtnSecretaria.setSelected(false);
				rdbtnDoctor.setSelected(false);
				panel_paciente.setVisible(false);
				panel_paciente.setVisible(true);
				panel_doctor.setVisible(false);
			}
		});
		
		rdbtnDoctor = new JRadioButton("Doctor");
		rdbtnDoctor.setBounds(162, 18, 88, 23);
		panel_tipos.add(rdbtnDoctor);
		
		rdbtnSecretaria = new JRadioButton("Secretaria");
		rdbtnSecretaria.setBounds(330, 18, 88, 23);
		panel_tipos.add(rdbtnSecretaria);
		rdbtnSecretaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Secretaria Seleccionado");

				rdbtnPaciente.setSelected(false);
				rdbtnDoctor.setSelected(false);
				rdbtnSecretaria.setSelected(true);
				panel_secretaria.setVisible(true);
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(false);
			}
		});
		rdbtnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnPaciente.setSelected(false);
				rdbtnSecretaria.setSelected(false);
				rdbtnDoctor.setSelected(true);
				panel_paciente.setVisible(false);
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(true);
				/*

				*/
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegister = new JButton("Registrar");
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Faltan los chequeos de que no falte info
						//Falta chequeo de que la cedula no se repita
						
						String codigo = txtId.getText();
						String nombre = txtNombre.getText();
						String cedula = txtCedula.getText();
						String telefono = txtTelefono.getText();
						String contrasenia = txtPassword.getText();
						String dir = "";
						String area = txtArea.getText();
						char genero = ' ';
						Doctor dependiente = null;
						
						if (rdbtnPaciente.isSelected()) {
							System.out.println("Registrar.contraseña: " + contrasenia);

							aux = new Paciente(codigo, nombre, cedula, telefono, contrasenia, dir, false, genero, new ArrayList<>());
							System.out.println("Registrar.contraseñaInAux: " + aux.getContrasenia());

						}
						
						if (rdbtnDoctor.isSelected()) {
							aux = new Doctor(codigo,nombre,cedula,telefono,contrasenia,area);
						}
						
						if (rdbtnSecretaria.isSelected()) {
							aux = new Secretaria(codigo,nombre,cedula,telefono,contrasenia,dependiente);
						}
						Hospital.getInstance().insertarUsuario(aux);
						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}
				});
				btnRegister.setActionCommand("OK");
				buttonPane.add(btnRegister);
				getRootPane().setDefaultButton(btnRegister);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		//aux = entrada;
		loadUser();
		panel_doctor.setVisible(false);
		panel_paciente.setVisible(true);


	}
	
	private void loadUser() {
		if(aux != null){
			/*txtId.setText(aux.getCodigo());
			txtPrecioBase.setText(Float.toString(aux.getPrecioBase()));
			txtCostoUnitario.setText(Float.toString(aux.getCostoUnitario()));
			textRadio.setText(Float.toString(aux.getRadio()));
			
			if (aux instanceof QueEsferico) {
				rdbtnEsferico.setSelected(true);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilHueco.setSelected(false);
			}
			
			if (aux instanceof QueCilindrico) {
				rdbtnEsferico.setSelected(false);
				rdbtnCilindrico.setSelected(true);
				rdbtnCilHueco.setSelected(false);
				textLongitud_1.setText(Float.toString(((QueCilindrico)aux).getLongitud()));
			}
			
			if (aux instanceof QueCilHueco) {
				rdbtnEsferico.setSelected(false);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilHueco.setSelected(true);
				textLongitud.setText(Float.toString(((QueCilindrico)aux).getLongitud()));
				txtRadioInterior.setText(Float.toString(((QueCilHueco)aux).getRadioInterior()));
			}
		*/}
	
	}
	
	private void clean() {
		txtId.setText("USR-"+String.valueOf(Hospital.generadorUsuario));
		txtNombre.setText("");
		txtCedula.setText("");
		txtPassword.setText("");
		txtTelefono.setText("");
		
		txtDir.setText("");
		txtArea.setText("");
		txtSueldo.setText("");
	}
}
