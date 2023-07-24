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
	JComboBox cbxAlergia = new JComboBox();
	JRadioButton rdbtnSecretaria;
	JComboBox cbxSexo = new JComboBox();
	private JTextField txtTelefono;
	private JTextField txtDir;
	private Usuario aux;
	private JTextField txtCedula;
	private JTextField txtPassword;
	private JTextField txtArea;
	private JComboBox cbxSupervisor;
	private JButton btnBorrarAlergia;
	private ArrayList<String> SupervisorCedula;
	
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario("",null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RegistrarUsuario(String title, Usuario entrada) {
		SupervisorCedula = new ArrayList<String>();
		setModal(true);
		
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
		lblNombre.setBounds(12, 63, 60, 14);
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
		txtTelefono.setBounds(317, 60, 91, 21);
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
		txtPassword.setBounds(317, 99, 91, 21);
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
		
		JLabel lblAlergia = new JLabel("Alergias Conocidas:");
		lblAlergia.setBounds(222, 23, 122, 14);
		panel_paciente.add(lblAlergia);
		
		cbxAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedAlergia = cbxAlergia.getSelectedItem().toString();
				if ( cbxAlergia.isEditable() ) {
					
					System.out.println("Alergia Registrado");
					
					
					cbxAlergia.addItem(cbxAlergia.getSelectedItem().toString());
					System.out.println(cbxAlergia.getSelectedItem().toString());
					System.out.println(cbxAlergia.getItemAt(cbxAlergia.getItemCount() - 2));
					cbxAlergia.removeItemAt(cbxAlergia.getItemCount() - 2);
					cbxAlergia.addItem("Agregar...");
					btnBorrarAlergia.setText("Borrar");
					cbxAlergia.setEditable(false);
			
				}
				if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
					btnBorrarAlergia.setText("Ok");
					cbxAlergia.setEditable(true);
					cbxAlergia.setSelectedItem("");
					
				}
			}
		});
		cbxAlergia.setBounds(222, 62, 122, 22);
		cbxAlergia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Agregar..."}));
		panel_paciente.add(cbxAlergia);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(80, 20, 122, 21);
		panel_paciente.add(txtDir);
		
		JLabel lblGenero = new JLabel("G\u00E9nero:");
		lblGenero.setBounds(7, 66, 54, 14);
		panel_paciente.add(lblGenero);
		
		cbxSexo.setBounds(80, 62, 116, 22);
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Hombre", "Mujer"}));
		panel_paciente.add(cbxSexo);		
		
		btnBorrarAlergia = new JButton("Borrar");
		btnBorrarAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnBorrarAlergia.getText() == "Borrar")
				{
					if (cbxAlergia.getSelectedItem().toString() != "<Seleccione>" && cbxAlergia.getSelectedItem().toString() != "Agregar...")
					{
						cbxAlergia.removeItemAt(cbxAlergia.getSelectedIndex());
					}
				}
				else 
				{
					System.out.println("Boton OK pushed");
					
					
					cbxAlergia.addItem(cbxAlergia.getSelectedItem().toString());					
					cbxAlergia.removeItemAt(cbxAlergia.getItemCount() - 2);
					cbxAlergia.addItem("Agregar...");
					btnBorrarAlergia.setText("Borrar");
				}


			}
		});
		btnBorrarAlergia.setBounds(356, 61, 83, 25);
		panel_paciente.add(btnBorrarAlergia);
		
		panel_doctor = new JPanel();
		panel_doctor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_doctor.setBounds(10, 219, 439, 104);
		contentPanel.add(panel_doctor);
		panel_doctor.setLayout(null);
		
		JLabel lblAreaMedica = new JLabel("Area M\u00E9dica:");
		lblAreaMedica.setBounds(7, 23, 103, 14);
		panel_doctor.add(lblAreaMedica);
		
		txtArea = new JTextField();
		txtArea.setColumns(10);
		txtArea.setBounds(86, 20, 116, 21);
		panel_doctor.add(txtArea);
		
		
		panel_secretaria = new JPanel();
		panel_secretaria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_secretaria.setBounds(10, 219, 439, 104);
		contentPanel.add(panel_secretaria);
		panel_secretaria.setLayout(null);
		
		
		JLabel lblSupervisor = new JLabel("Supervisor:");
		lblSupervisor.setBounds(7, 23, 75, 16);
		panel_secretaria.add(lblSupervisor);
		
		
		cbxSupervisor = new JComboBox();
		cbxSupervisor.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		cbxSupervisor.setBounds(86, 20, 200, 22);
		panel_secretaria.add(cbxSupervisor);
		cbxSupervisor.setVisible(false);

		
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
				cbxSupervisor.setVisible(false);

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
				cbxSupervisor.setVisible(true);
				loadSupervisores();
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
				cbxSupervisor.setVisible(false);

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
						
						
						String codigo = txtId.getText();
						String nombre = txtNombre.getText();
						String cedula = txtCedula.getText();
						String telefono = txtTelefono.getText();
						String contrasenia = txtPassword.getText();
						String dir = txtDir.getText();
						String area = txtArea.getText();
						char genero = genero(cbxSexo.getSelectedItem().toString());
						
						Doctor dependiente = null;
						
						if (rdbtnPaciente.isSelected()) {
							System.out.println("Registrar.contraseña: " + contrasenia);

							aux = new Paciente(codigo, nombre, cedula, telefono, contrasenia, dir, false, genero, new ArrayList<String>());
							System.out.println("Registrar.contraseñaInAux: " + aux.getContrasenia());

						}
						
						else if (rdbtnDoctor.isSelected()) {
							aux = new Doctor(codigo,nombre,cedula,telefono,contrasenia,area);
						}
						
						else if (rdbtnSecretaria.isSelected()) {
							
							if (cbxSupervisor.getSelectedIndex() > 0)
							{
								String codeDependiente = SupervisorCedula.get(cbxSupervisor.getSelectedIndex() - 1);
								
								dependiente = ((Doctor) Hospital.getInstance().buscarUsuarioByCedula(codeDependiente));
							}
							
							aux = new Secretaria(codigo,nombre,cedula,telefono,contrasenia,dependiente);
						}
						
						if (!hayEspacioVacio())
						{
							if (Hospital.getInstance().buscarUsuarioByCedula(cedula) == null)
							{
								Hospital.getInstance().insertarUsuario(aux);
								JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
								clean();
								dispose();

							}
							else
							{
								JOptionPane.showMessageDialog(null, "Esta Cédula ya se encuentra en el sistema", "Alerta", JOptionPane.INFORMATION_MESSAGE);
								
							}
							
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Alerta", JOptionPane.INFORMATION_MESSAGE);

						}
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
		//loadUser();
		panel_doctor.setVisible(false);
		panel_paciente.setVisible(true);


	}
	
	private void loadUser() {
		if(aux != null){
			txtId.setText(aux.getCodigo());
			txtNombre.setText(aux.getNombre());
			txtCedula.setText(aux.getCedula());
			txtPassword.setText(aux.getContrasenia());
			txtTelefono.setText(aux.getTelefono());
			
			if (aux instanceof Paciente){
				setGenero( ((Paciente)aux).getSexo() );
				txtDir.setText( ((Paciente)aux).getDireccion());
				
				cbxAlergia.removeAll();
				//Considero que sería más práctico directamente conectar a la dirección de Alergias.
				int limit = ((Paciente)aux).getAlergias().size();
				for (int i = 0; i != limit; i++ ) {
					cbxAlergia.addItem( ((Paciente)aux).getAlergias().get(i) );
				}
				cbxAlergia.addItem("Agregar...");
			}
			
			if (aux instanceof Doctor){
				txtArea.setText( ((Doctor)aux).getAreaMedica());
			}
			
			if (aux instanceof Secretaria){
				txtArea.setText( ((Secretaria)aux).getDependiente().getCodigo() );
			}
		}
	
	}
	
	private void clean() {
		txtId.setText("USR-"+String.valueOf(Hospital.generadorUsuario));
		txtNombre.setText("");
		txtCedula.setText("");
		txtPassword.setText("");
		txtTelefono.setText("");
		
		txtDir.setText("");
		txtArea.setText("");
	}
	
	
	private char genero(String opc) {
		if (opc == "Hombre") {
			return 'H';
		}
		
		if (opc == "Mujer") {
			return 'M';
		}
		return ' ';
	}
	
	private void setGenero(char opc) {
		cbxSexo.setSelectedIndex(-1);
		if (opc == 'H') {
			cbxSexo.setSelectedIndex(1);
		}
		
		if (opc == 'M') {
			cbxSexo.setSelectedIndex(2);
		}
	}
	
	/*
	Funcion que revisa todos los campos a llenar
	Retorna falso si encuentra alguno vacío
	Retorna verdadero si todos estan llenos
	
	Esto probablemente funcionaria como un ciclo si se consigue una forma
	de cambiar de donde se consigue la info segun cada ciclo
	
	ciclo 1: temp = txtNombre
	ciclo 2: temp = txtCedula
	etc
	*/
	private boolean hayEspacioVacio()
	{
		String temp;
		
		temp = txtNombre.getText();
		if (temp == null || temp.equals(""))
		{
			return true;
		}
		temp = txtCedula.getText();
		if (temp == null || temp.equals(""))
		{
			return true;
		}
		temp = txtPassword.getText();
		if (temp == null || temp.equals(""))
		{
			return true;
		}		
		temp = txtTelefono.getText();
		if (temp == null || temp.equals(""))
		{
			return true;
		}
		
		if (rdbtnPaciente.isSelected())
		{
			temp = txtDir.getText();
			if (temp == null || temp.equals(""))
			{
				return true;
			}
			if (cbxSexo.getSelectedIndex() <= 0)
			{
				return true;
			}
			
		}
		
		else if (rdbtnDoctor.isSelected())
		{
			temp = txtArea.getText();
			if (temp == null || temp.equals(""))
			{
				return true;
			}
		}

		
		return false;
	}
	
	private void loadSupervisores()
	{
		cbxSupervisor.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));

		for (Usuario temp : Hospital.getInstance().getMisCuentas())
		{
			if (temp instanceof Doctor)
			{
				cbxSupervisor.addItem(temp.getNombre());
				SupervisorCedula.add(temp.getCedula());
			}
		}
	}
}
