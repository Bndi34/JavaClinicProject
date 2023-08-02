package visualRegistros;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import logico.Usuario;

import logico.Hospital;

import logico.Paciente;
import logico.RegistroMedico;
import logico.Admin;
import logico.Cita;
import logico.Doctor;
import logico.Enfermedad;
import logico.Secretaria;

import javax.swing.SpinnerDateModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
	private JPanel panel_paciente;
	private JPanel panel_doctor;
	private JPanel panel_secretaria;
	
	private JRadioButton rdbtnPaciente;
	private JRadioButton rdbtnDoctor;
	private JRadioButton rdbtnSecretaria;
	private JRadioButton rdbtnAdmin;
	private JComboBox cbxSexo = new JComboBox();
	private JComboBox cbxSupervisor;

	private Usuario aux;
	
	private JTextField txtTelefono;
	private JTextField txtDir;
	private JTextField txtCedula;
	private JTextField txtPassword;
	private JTextField txtArea;
	
	private ArrayList<String> SupervisorCedula;
	
	
	private DefaultListModel<String> modelAlergia;
	private JList listAler = new JList();
	private String selectedAlergia;
	private ArrayList<String>AlergiasElegidas = new ArrayList<>();
	private ArrayList<String>AlergiasSinElegir = new ArrayList<>();
	private JTextField txtAlergia;
	private JComboBox cbxAlergia = new JComboBox();
	private JButton btnAlergia = new JButton("A\u00F1adir");
	private int selectedInCuadro = -1;
	
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario("",null, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RegistrarUsuario(String title, final Usuario entrada, boolean adminCheck) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		SupervisorCedula = new ArrayList<String>();
		setModal(true);
		
		System.out.println("Registrar IN");
		setResizable(false);
		setBounds(100, 100, 534, 499);
		setTitle(title);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 495, 138);
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
		panel_paciente.setBounds(10, 219, 495, 208);
		contentPanel.add(panel_paciente);
		panel_paciente.setLayout(null);
		panel_paciente.setVisible(false);
		
		JLabel lblDir = new JLabel("Direcci\u00F3n:");
		lblDir.setBounds(12, 56, 61, 14);
		panel_paciente.add(lblDir);
		
		JLabel lblAlergia = new JLabel("Alergias Conocidas:");
		lblAlergia.setBounds(12, 24, 122, 14);
		panel_paciente.add(lblAlergia);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(22, 83, 174, 21);
		panel_paciente.add(txtDir);
		
		JLabel lblGenero = new JLabel("G\u00E9nero:");
		lblGenero.setBounds(12, 127, 54, 14);
		panel_paciente.add(lblGenero);
		
		cbxSexo.setBounds(74, 123, 122, 22);
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Hombre", "Mujer"}));
		panel_paciente.add(cbxSexo);
		btnAlergia.setBounds(383, 20, 100, 23);
		panel_paciente.add(btnAlergia);
		cbxAlergia.setBounds(146, 20, 225, 22);
		panel_paciente.add(cbxAlergia);
		JPanel panel_alergia = new JPanel();
		panel_alergia.setBounds(237, 56, 246, 130);
		panel_paciente.add(panel_alergia);
		panel_alergia.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_alergia.add(scrollPane_1);
		
		scrollPane_1.setViewportView(listAler);
		modelAlergia = new DefaultListModel<String>();
		listAler.setModel(modelAlergia);
		
		
		
		cbxAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					 selectedAlergia = cbxAlergia.getSelectedItem().toString();
					if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
						btnAlergia.setText("Añadir");
						
						txtAlergia.setVisible(true);
						txtAlergia.setEditable(true);
						txtAlergia.setEnabled(true);
						txtAlergia.setText("");
						
						cbxAlergia.setVisible(false);
						//cbxAlergia.setEditable(true);
						//cbxAlergia.setSelectedItem("");
						
					}
					else if (!selectedAlergia.equalsIgnoreCase("<Seleccione>"))
					{
						AlergiasElegidas.add(selectedAlergia);
						AlergiasSinElegir.remove(selectedAlergia);
						
						System.out.println("Reload Cuadro Combobox");
						reloadAlergia();
						reloadAlergiaCuadro();
					}
					else
					{
						btnAlergia.setText("Borrar");
						
						txtAlergia.setVisible(false);
						txtAlergia.setEditable(false);
						txtAlergia.setEnabled(false);
						txtAlergia.setText("");
						
						cbxAlergia.setVisible(true);	
					}
					
				} catch (NullPointerException e2) {
					// TODO: handle exception
				}
				
			}
		});
		
		btnAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedAlergia = cbxAlergia.getSelectedItem().toString();
				
				if ( btnAlergia.getText().equalsIgnoreCase("Borrar")) {
					try {
						if (!txtAlergia.getText().isEmpty())
						{
							AlergiasSinElegir.add(txtAlergia.getText());
							AlergiasElegidas.remove(txtAlergia.getText());
							txtAlergia.setText("");
							
							reloadAlergia();
							System.out.println("Reload cuadro Borrar");
							reloadAlergiaCuadro();
						}
						
						
					} catch (IndexOutOfBoundsException e2) {
						btnAlergia.setText("Añadir");
					}
					
				}
				else if ( txtAlergia.isEditable() ) {
					
					System.out.println("Alergia TXT: "+txtAlergia.getText());
					AlergiasElegidas.add(txtAlergia.getText());
					
					reloadAlergia();
					reloadAlergiaCuadro();
					txtAlergia.setVisible(false);
					cbxAlergia.setVisible(true);
					btnAlergia.setText("Borrar");
				}
				
				
				
				
				
			}
		});
		
		listAler.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (!txtAlergia.isEditable())
					{
						selectedInCuadro = listAler.getSelectedIndex();
						selectedAlergia = listAler.getSelectedValue().toString();
						if(selectedInCuadro>=0)
						{
							btnAlergia.setText("Borrar");
							txtAlergia.setText(listAler.getSelectedValue().toString());
						}
					}
					
				} catch (NullPointerException e2) {
					// TODO: handle exception
				}
				
			}
		});
		panel_doctor = new JPanel();
		panel_doctor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_doctor.setBounds(10, 219, 495, 208);
		contentPanel.add(panel_doctor);
		panel_doctor.setLayout(null);
		panel_doctor.setVisible(false);
		
		JLabel lblAreaMedica = new JLabel("Area M\u00E9dica:");
		lblAreaMedica.setBounds(7, 23, 103, 14);
		panel_doctor.add(lblAreaMedica);
		
		txtArea = new JTextField();
		txtArea.setColumns(10);
		txtArea.setBounds(86, 20, 116, 21);
		panel_doctor.add(txtArea);
		
		
		panel_secretaria = new JPanel();
		panel_secretaria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_secretaria.setBounds(10, 219, 495, 208);
		contentPanel.add(panel_secretaria);
		panel_secretaria.setLayout(null);
		panel_secretaria.setVisible(false);
		
		
		JLabel lblSupervisor = new JLabel("Supervisor:");
		lblSupervisor.setBounds(7, 23, 75, 16);
		panel_secretaria.add(lblSupervisor);
		
		
		cbxSupervisor = new JComboBox();
		cbxSupervisor.setEnabled(false);
		cbxSupervisor.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		cbxSupervisor.setBounds(86, 20, 200, 22);
		panel_secretaria.add(cbxSupervisor);

		
		JPanel panel_tipos = new JPanel();
		panel_tipos.setBorder(new TitledBorder(null, "Tipo de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_tipos.setBounds(10, 160, 495, 48);
		contentPanel.add(panel_tipos);
		panel_tipos.setLayout(null);
		
		
		txtAlergia = new JTextField();
		txtAlergia.setEnabled(false);
		txtAlergia.setText("VA-1");
		txtAlergia.setEditable(false);
		txtAlergia.setVisible(false);
		txtAlergia.setColumns(10);
		txtAlergia.setBounds(146, 20, 225, 22);
		panel_paciente.add(txtAlergia);
		
		reloadAlergia();
		reloadAlergiaCuadro();
		
		rdbtnPaciente = new JRadioButton("Paciente");
		rdbtnPaciente.setSelected(true);
		rdbtnPaciente.setBounds(17, 18, 88, 23);
		panel_tipos.add(rdbtnPaciente);
		
		
		rdbtnDoctor = new JRadioButton("Doctor");
		rdbtnDoctor.setBounds(142, 18, 88, 23);
		panel_tipos.add(rdbtnDoctor);
		
		rdbtnSecretaria = new JRadioButton("Secretaria");
		rdbtnSecretaria.setBounds(257, 18, 88, 23);
		panel_tipos.add(rdbtnSecretaria);
		
		rdbtnAdmin = new JRadioButton("Admin");
		
		rdbtnAdmin.setBounds(399, 18, 88, 23);
		panel_tipos.add(rdbtnAdmin);
		rdbtnAdmin.setVisible(false);
		
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rdbtnPaciente.setSelected(false);
				rdbtnSecretaria.setSelected(false);
				rdbtnDoctor.setSelected(false);
				rdbtnAdmin.setSelected(true);
				
				panel_paciente.setVisible(false);
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(false);
				
				lblSupervisor.setVisible(false);
				cbxSupervisor.setVisible(false);
				cbxSupervisor.setEnabled(false);

			}
		});
	
		rdbtnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Paciente Seleccionado");
				rdbtnPaciente.setSelected(true);
				rdbtnSecretaria.setSelected(false);
				rdbtnDoctor.setSelected(false);
				rdbtnAdmin.setSelected(false);
				
				panel_paciente.setVisible(false);
				panel_paciente.setVisible(true);
				panel_doctor.setVisible(false);
				
				cbxSupervisor.setVisible(false);
				cbxSupervisor.setEnabled(false);


			}
		});
		
		rdbtnSecretaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Secretaria Seleccionado");

				rdbtnPaciente.setSelected(false);
				rdbtnDoctor.setSelected(false);
				rdbtnSecretaria.setSelected(true);
				rdbtnAdmin.setSelected(false);
				
				panel_secretaria.setVisible(true);
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(false);
				cbxSupervisor.setEnabled(true);
				
				lblSupervisor.setVisible(true);
				cbxSupervisor.setVisible(true);
				loadSupervisores();
			}
		});
		rdbtnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnPaciente.setSelected(false);
				rdbtnSecretaria.setSelected(false);
				rdbtnDoctor.setSelected(true);
				rdbtnAdmin.setSelected(false);
				
				panel_paciente.setVisible(false);
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(true);

				cbxSupervisor.setEnabled(false);
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
							
							aux = new Paciente(codigo, nombre, cedula, telefono, contrasenia, dir, false, genero,new RegistroMedico(false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), AlergiasElegidas));
							//aux = new Paciente(codigo, nombre, cedula, telefono, contrasenia, dir, false, genero,new RegistroMedico(false, new ArrayList<Cita>(), new ArrayList<Consulta>(), new ArrayList<Vacuna>(), AlergiasElegidas));
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
						else if (rdbtnAdmin.isSelected())
						{
							aux = new Admin(codigo, nombre, cedula, telefono, contrasenia);
						}
						
						if (!hayEspacioVacio())
						{
							System.out.println(aux);
							if (Hospital.getInstance().buscarUsuarioByCedula(cedula) == null || entrada != null)
							{
								if (entrada == null)
								{	
									
									System.out.println("IngresarGood");
									Hospital.getInstance().insertarUsuario(aux);
									JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);

								}
								else
								{
									if (entrada instanceof Paciente)
									{
										((Paciente) aux).setMiRegistro(((Paciente) entrada).getMiRegistro());
									}
									
									System.out.println("ModificarGood");
									JOptionPane.showMessageDialog(null, "Modificacion satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
									Hospital.getInstance().modificarUsuario(aux);
								}
									
								try {
									Hospital.save();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
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
		aux = entrada;
		System.out.println(aux);
		System.out.println(entrada);

		if (adminCheck)
		{
			rdbtnAdmin.setVisible(true);
		}
		
		if (!adminCheck && entrada == null)
		{
			rdbtnDoctor.setVisible(false);
			rdbtnSecretaria.setVisible(false);
			
			panel_paciente.setVisible(true);
			panel_doctor.setVisible(false);
			panel_secretaria.setVisible(false);
		}
		else if (entrada != null)
		{
			rdbtnPaciente.setVisible(true);
			rdbtnDoctor.setVisible(true);
			rdbtnSecretaria.setVisible(true);
			
			rdbtnPaciente.setEnabled(false);
			rdbtnDoctor.setEnabled(false);
			rdbtnSecretaria.setEnabled(false);
		}
		else if (entrada == null)
		{
			panel_paciente.setVisible(true);
		}
		
		loadUser();
		/*
		panel_doctor.setVisible(false);
		panel_paciente.setVisible(true);
*/

	}
	
	private void loadUser() {
		try {
			txtId.setText(aux.getCodigo());
			txtNombre.setText(aux.getNombre());
			txtCedula.setText(aux.getCedula());
			txtPassword.setText(aux.getContrasenia());
			txtTelefono.setText(aux.getTelefono());
			
			if (aux instanceof Paciente){
				
				rdbtnPaciente.setSelected(true);
				rdbtnDoctor.setSelected(false);
				rdbtnSecretaria.setSelected(false);
				
				panel_paciente.setVisible(true);
				panel_doctor.setVisible(false);
				panel_secretaria.setVisible(false);
				
				setGenero( ((Paciente)aux).getSexo() );
				txtDir.setText( ((Paciente)aux).getDireccion());
				
				AlergiasElegidas = ((Paciente) aux).getMiRegistro().getMisAlergias();
				reloadAlergia();
				reloadAlergiaCuadro();
				
				/*
				cbxAlergia.setModel(new DefaultComboBoxModel(((Paciente) aux).getAlergias().toArray(new String[0])));

				cbxAlergia.addItem("Agregar...");*/
			}
			
			else if (aux instanceof Doctor){

				rdbtnPaciente.setSelected(false);
				rdbtnDoctor.setSelected(true);
				rdbtnSecretaria.setSelected(false);
				
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(true);
				panel_secretaria.setVisible(false);

				
				txtArea.setText( ((Doctor)aux).getAreaMedica());
			}
			
			else if (aux instanceof Secretaria){
				
				rdbtnPaciente.setSelected(false);
				rdbtnDoctor.setSelected(false);
				rdbtnSecretaria.setSelected(true);
				
				
				panel_paciente.setVisible(false);
				panel_doctor.setVisible(false);
				panel_secretaria.setVisible(true);

				txtArea.setText( ((Secretaria)aux).getDependiente().getCodigo() );
			}
			
			

		} catch (NullPointerException e) {

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
		else if (rdbtnSecretaria.isSelected())
		{
			if (cbxSupervisor.getSelectedIndex() <= 0 )
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
	private void loadAlergia()
	{
		try {

			AlergiasElegidas = ((Paciente) aux).getMiRegistro().getMisAlergias();


		} catch (NullPointerException e) {
		}

	}
	private void reloadAlergia()
	{
		cbxAlergia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		
		for (String temp : AlergiasSinElegir )
		{
			cbxAlergia.addItem(temp);
		}
		
		cbxAlergia.addItem("Agregar...");
		
	}
	private void reloadAlergiaCuadro()
	{
		modelAlergia.removeAllElements();
		for (String auxString : AlergiasElegidas)
		{
			modelAlergia.addElement(auxString);
		}
	}
}
