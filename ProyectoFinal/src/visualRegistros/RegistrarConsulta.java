package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cita;
import logico.Consulta;
import logico.Doctor;
import logico.Enfermedad;
import logico.Hospital;
import logico.Paciente;
import logico.Usuario;
import logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class RegistrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JComboBox cbxHorasDisponibles = new JComboBox();
	
	private ArrayList<String>sintomas;
	private ArrayList<Vacuna>vacunasColocadas = new ArrayList<Vacuna>();
	private Doctor doctor;
	private Paciente paciente;
	private Consulta consulta;
	private Date fecha;
	private JTextField txtDoctor;
	private JTextField txtPaciente;
	private JTextField txtEstado;
	private JTextField txtFecha;
	
	private JComboBox cbxEnfermedad = new JComboBox();
	private JButton btnEnfermedad = new JButton("A\u00F1adir");
	private DefaultListModel<String> modelEnfermedad;
	private JList listEnf = new JList();
	private String selectedEnfermedad;
	private JTextField txtEnfermedad;
	private ArrayList<String>EnfermedadesElegidas = new ArrayList<>();
	private ArrayList<String>EnfermedadeSinElegir = new ArrayList<>();
	private int selected = -1;
	
	private JPanel panel_sintomas;
	private String selectedSintoma;
	private JButton btnAgregarSintoma;
	private JTextField txtSintoma;
	private JList listSeleccionada = new JList();
	private JComboBox cbxSintoma = new JComboBox();
	private DefaultListModel<String> modelSintomaElegido;
	private ArrayList<String>SintomasElegido = new ArrayList<>();
	private ArrayList<String>SintomasSinElegir = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarConsulta dialog = new RegistrarConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarConsulta(Consulta entrada) {
		
		consulta = entrada;
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		sintomas = new ArrayList<String>();
		setTitle("Registrar Consulta");
		setBounds(100, 100, 674, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		loadEnfermedadesSinElegir();
		modelEnfermedad = new DefaultListModel<String>();
		
		SintomasSinElegir = new ArrayList<String>();
		SintomasElegido = consulta.getSintomas();
		modelSintomaElegido = new DefaultListModel<String>();
		reloadSintomasCuadro();
		
		txtSintoma = new JTextField();
		txtSintoma.setEnabled(false);
		txtSintoma.setEditable(false);
		txtSintoma.setVisible(false);
		txtSintoma.setColumns(10);
		txtSintoma.setBounds(175, 265, 382, 22);
		contentPanel.add(txtSintoma);
		
		{
			{
				cbxSintoma.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						selectedSintoma = cbxSintoma.getSelectedItem().toString();
							if ( selectedSintoma.equalsIgnoreCase("Agregar...") ) {
								btnAgregarSintoma.setText("Añadir");
								
								txtSintoma.setVisible(true);
								txtSintoma.setEditable(true);
								txtSintoma.setEnabled(true);
								txtSintoma.setText("");
								
								cbxSintoma.setVisible(false);
								//cbxAlergia.setEditable(true);
								//cbxAlergia.setSelectedItem("");
								
							}
							else if (!selectedSintoma.equalsIgnoreCase("<Seleccione>"))
							{
								SintomasElegido.add(selectedSintoma);
								SintomasSinElegir.remove(selectedSintoma);
								
								System.out.println("Reload Cuadro Combobox");
								reloadSintomas();
								reloadSintomasCuadro();
							}
							else
							{
								btnAgregarSintoma.setText("Borrar");
								
								txtSintoma.setVisible(false);
								txtSintoma.setEditable(false);
								txtSintoma.setEnabled(false);
								txtSintoma.setText("");
								
								cbxSintoma.setVisible(true);	
							}
						
					}
				});
				cbxSintoma.setBounds(175, 265, 382, 22);
				contentPanel.add(cbxSintoma);
			}
			{
				
				
				panel_sintomas = new JPanel();
				panel_sintomas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_sintomas.setBounds(22, 301, 622, 157);
				contentPanel.add(panel_sintomas);
				panel_sintomas.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					panel_sintomas.add(scrollPane, BorderLayout.CENTER);
					{
						listSeleccionada.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								
								try 
								{
									if (!txtSintoma.isEditable())
									{
										int selected = listSeleccionada.getSelectedIndex();
										selectedSintoma = listSeleccionada.getSelectedValue().toString();
										if(selected>=0)
										{
											btnAgregarSintoma.setText("Borrar");
											txtSintoma.setText(listSeleccionada.getSelectedValue().toString());
										}
									}
									
									
								} catch (Exception e2) {
									// TODO: handle exception
								}
								
								
							}
						});
						scrollPane.setViewportView(listSeleccionada);
						System.out.println("Reload cuadro 1");
						reloadSintomasCuadro();
						listSeleccionada.setModel(modelSintomaElegido);
						
					}
				}
			}
			{
			btnAgregarSintoma = new JButton("A\u00F1adir");
			btnAgregarSintoma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					selectedSintoma = cbxSintoma.getSelectedItem().toString();
					
					if ( btnAgregarSintoma.getText().equalsIgnoreCase("Borrar")) {
						try {
							if (!txtSintoma.getText().isEmpty())
							{
								SintomasSinElegir.add(txtSintoma.getText());
								SintomasElegido.remove(txtSintoma.getText());
								txtSintoma.setText("");
								
								reloadSintomas();
								System.out.println("Reload cuadro Borrar");
								reloadSintomasCuadro();
							}
							
							
						} catch (IndexOutOfBoundsException e2) {
							btnAgregarSintoma.setText("Añadir");
						}
						
					}
					else if ( txtSintoma.isEditable() ) {
						
						SintomasElegido.add(txtSintoma.getText());
						
						reloadSintomas();
						reloadSintomasCuadro();
						txtSintoma.setVisible(false);
						cbxSintoma.setVisible(true);
						btnAgregarSintoma.setText("Borrar");
					}
					
					
				}
			});
			btnAgregarSintoma.setBounds(569, 265, 75, 25);
			contentPanel.add(btnAgregarSintoma);
			
			}
		}
		{
			cbxEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					{
						try {
							selectedEnfermedad = cbxEnfermedad.getSelectedItem().toString();
							System.out.println(selectedEnfermedad);
							if ( selectedEnfermedad.equalsIgnoreCase("Agregar...") ) {
								btnEnfermedad.setText("Añadir");
								
								txtEnfermedad.setVisible(true);
								txtEnfermedad.setEditable(true);
								txtEnfermedad.setEnabled(true);
								txtEnfermedad.setText("");
								
								cbxEnfermedad.setVisible(false);
								//cbxAlergia.setEditable(true);
								//cbxAlergia.setSelectedItem("");
								
							}
							else if (!selectedEnfermedad.equalsIgnoreCase("<Seleccione>"))
							{
								//Añadir del combobox al recuadro
								EnfermedadesElegidas.add(selectedEnfermedad);
								System.out.println(EnfermedadeSinElegir.get(0));
								EnfermedadeSinElegir.remove(selectedEnfermedad);
								
								cbxEnfermedad.setEnabled(false);
								
								System.out.println("Reload Cuadro Combobox");
								reloadEnfermedad();
								reloadEnfermedadCuadro();
								
							}
							else
							{
								btnEnfermedad.setText("Borrar");
								
								txtEnfermedad.setVisible(false);
								txtEnfermedad.setEditable(false);
								txtEnfermedad.setEnabled(false);
								txtEnfermedad.setText("");
								
								cbxEnfermedad.setVisible(true);	
							}
						} catch (NullPointerException e2) {
							// TODO: handle exception
						}
						
				}
			}});
			cbxEnfermedad.setBounds(272, 49, 196, 22);
			contentPanel.add(cbxEnfermedad);
			if (cbxEnfermedad.getItemCount() == 0) {
				cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				reloadEnfermedad();
				//cbxEnfermedad.addItem("Agregar...");
			}
		}
		btnEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEnfermedad = cbxEnfermedad.getSelectedItem().toString();
				
				if ( btnEnfermedad.getText().equalsIgnoreCase("Borrar")) {
					try {
						if (!txtEnfermedad.getText().isEmpty())
						{
							EnfermedadeSinElegir.add(txtEnfermedad.getText());
							EnfermedadesElegidas.remove(txtEnfermedad.getText());
							txtEnfermedad.setText("");
							cbxEnfermedad.setEnabled(true);
							
							reloadEnfermedad();
							reloadEnfermedadCuadro();
						}
						
						
					} catch (IndexOutOfBoundsException e2) {
						btnEnfermedad.setText("Añadir");
					}
					
				}
				else if ( txtEnfermedad.isEditable() ) {
					
					System.out.println("txtEnfermedad TXT: "+txtEnfermedad.getText());
					EnfermedadesElegidas.add(txtEnfermedad.getText());
					
					txtEnfermedad.setVisible(false);
					cbxEnfermedad.setVisible(true);
					btnEnfermedad.setText("Borrar");
				}
			}
		});
		
		btnEnfermedad.setBounds(480, 49, 106, 23);
		contentPanel.add(btnEnfermedad);
		
		JPanel panel_enfermedad = new JPanel();
		panel_enfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_enfermedad.setBounds(272, 82, 314, 51);
		contentPanel.add(panel_enfermedad);
		panel_enfermedad.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel_enfermedad.add(scrollPane, BorderLayout.CENTER);
			{
				listEnf.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							selected = listEnf.getSelectedIndex();
							selectedEnfermedad = listEnf.getSelectedValue().toString();
							if(selected>=0){
								btnEnfermedad.setText("Borrar");
								txtEnfermedad.setText(listEnf.getSelectedValue().toString());
							}
						} catch (NullPointerException e2) {
							// TODO: handle exception
						}
						
					}
				});
				scrollPane.setViewportView(listEnf);
				listEnf.setModel(modelEnfermedad);
			}
		}
		
		{
			
			txtEnfermedad = new JTextField();
			txtEnfermedad.setEnabled(false);
			txtEnfermedad.setText("VA-1");
			txtEnfermedad.setEditable(false);
			txtEnfermedad.setVisible(false);
			txtEnfermedad.setColumns(10);
			txtEnfermedad.setBounds(272, 49, 196, 22);
			contentPanel.add(txtEnfermedad);
		}
		
		
		
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(12, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblDate = new JLabel("Fecha");
			lblDate.setBounds(12, 42, 56, 16);
			contentPanel.add(lblDate);
		}
		{
			JLabel lblDoctor = new JLabel("Doctor");
			lblDoctor.setBounds(12, 81, 56, 16);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(12, 121, 56, 16);
			contentPanel.add(lblPaciente);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(12, 162, 56, 16);
			contentPanel.add(lblEstado);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(70, 10, 152, 22);
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
			txtCode.setText("CON-"+String.valueOf(Hospital.getInstance().generadorConsulta));
		}
		{
			
		}
		{
			JCheckBox chbxRetraso = new JCheckBox("");
			chbxRetraso.setEnabled(false);
			chbxRetraso.setBounds(139, 214, 34, 25);
			contentPanel.add(chbxRetraso);
		}
		{
			JLabel lblSintomas = new JLabel("Sintomas / Diagn\u00F3stico:");
			lblSintomas.setBounds(12, 268, 151, 16);
			contentPanel.add(lblSintomas);
		}
		{
			JLabel lblRetraso = new JLabel("Consulta Retrasada");
			lblRetraso.setBounds(22, 219, 130, 16);
			contentPanel.add(lblRetraso);
		}
		
		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setBounds(262, 20, 79, 16);
		contentPanel.add(lblEnfermedad);
		
		JLabel lblVacunas = new JLabel("Vacunas");
		lblVacunas.setBounds(262, 146, 56, 16);
		contentPanel.add(lblVacunas);
		
		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setBounds(70, 78, 152, 22);
		contentPanel.add(txtDoctor);
		txtDoctor.setColumns(10);
		
		txtPaciente = new JTextField();
		txtPaciente.setEditable(false);
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(70, 118, 152, 22);
		contentPanel.add(txtPaciente);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(70, 159, 152, 22);
		contentPanel.add(txtEstado);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(70, 42, 152, 22);
		contentPanel.add(txtFecha);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
							int index = EnfermedadesElegidas.get(0).indexOf(":");
							System.out.println((EnfermedadesElegidas.get(0).substring(0, index-1)));
							System.out.println(Hospital.getInstance().buscarEnfermedadesByCode(EnfermedadesElegidas.get(0).substring(0, index-1)));
							
							Enfermedad enfElegida = Hospital.getInstance().buscarEnfermedadesByCode((Hospital.getInstance().buscarEnfermedadesByCode(EnfermedadesElegidas.get(0).substring(0, index-1)).toString()));
									
							Consulta aux = new Consulta(txtCode.getText(),consulta.getEstado(),consulta.getFecha(), consulta.getPaciente(),consulta.getDoctor(), SintomasElegido, vacunasColocadas, enfElegida);
							Hospital.getInstance().modificarConsulta(aux);
						   
							index = -1;
							index = Hospital.getInstance().buscarIndexByConsulta(aux.getCodigo());
							aux.getPaciente().getMiRegistro().getMisConsultas().set(index, aux);
							
							JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);

							
							for (Vacuna vac : vacunasColocadas ) {
								paciente.getMiRegistro().getTotalDeVacunasColocadas().add(vac);
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar esta consulta", "Información", JOptionPane.INFORMATION_MESSAGE);

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
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadConsulta();
		
		//loadEnfermedades();
		//loadEnfermedadesSinElegir();
		
		reloadSintomas();
		reloadSintomasCuadro();
		
		reloadEnfermedad();
		reloadEnfermedadCuadro();
	}
	

	void loadConsulta(){
		try {
			
			txtFecha.setText(consulta.getFecha().toString());
			
			txtCode.setText(consulta.getCodigo());
			txtDoctor.setText(consulta.getDoctor().getCodigo() + " : " + consulta.getDoctor().getNombre());
			txtPaciente.setText(consulta.getPaciente().getCodigo() + " : " + consulta.getPaciente().getNombre());
			
			txtEstado.setText(consulta.getEstado());
			
			
			
		} catch (NullPointerException e) {
			return;
		}
	}
	
	void loadEnfermedades()
	{
		for (Enfermedad auxEnfermedad : Hospital.getInstance().getEnfermedadesReg())
		{
			cbxEnfermedad.addItem(auxEnfermedad.getCodigo() + " : " + auxEnfermedad.getNombre());

			if (consulta.getEnfermedad() == auxEnfermedad)
			{
				cbxEnfermedad.setSelectedItem(auxEnfermedad);
			}
		}
	}

	private void loadEnfermedadesSinElegir()
	{

		EnfermedadeSinElegir = new ArrayList<>();
		
		for (Enfermedad aux : Hospital.getInstance().getEnfermedadesReg())
		{
			String temp = aux.getCodigo()+" : "+aux.getNombre();
			EnfermedadeSinElegir.add(temp);
		}
		
	}
	
		private void reloadEnfermedad() {
		
		cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		
		for (String auxString : EnfermedadeSinElegir)
		{
			cbxEnfermedad.addItem(auxString);
		}
		
	}
	private void reloadEnfermedadCuadro()
	{
		modelEnfermedad.removeAllElements();
		for (String auxString : EnfermedadesElegidas)
		{
			modelEnfermedad.addElement(auxString);
		}
	}
	
	private void reloadSintomas()
	{
		
		cbxSintoma.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
	
		for (String temp : SintomasSinElegir )
		{
			cbxSintoma.addItem(temp);
		}
	
		cbxSintoma.addItem("Agregar...");
	
	}
	private void reloadSintomasCuadro()
	{
		modelSintomaElegido.removeAllElements();
		for (String auxString : SintomasElegido)
		{
			modelSintomaElegido.addElement(auxString);
		}
	}
	
}
