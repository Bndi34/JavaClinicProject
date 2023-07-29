package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cita;
import logico.Consulta;
import logico.Doctor;
import logico.Hospital;
import logico.Paciente;
import logico.Usuario;
import logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class RegistrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JComboBox cbxDoctor = new JComboBox();
	private JComboBox cbxPaciente = new JComboBox();
	private JComboBox cbxEstado = new JComboBox();
	private JComboBox cbxHorasDisponibles = new JComboBox();
	private JDateChooser dateChooser = new JDateChooser();
	private JComboBox cbxSintoma = new JComboBox();
	
	private ArrayList<String>sintomas;
	private ArrayList<Vacuna>vacunasColocadas;
	private Doctor doctor;
	private Paciente paciente;
	private Cita cita;
	private Date fecha;
	
	

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
		sintomas = new ArrayList<String>();
		setTitle("Registrar Consulta");
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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
			cbxDoctor.setBounds(70, 119, 152, 22);
			contentPanel.add(cbxDoctor);
		}
		{
			cbxPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedcbx = cbxPaciente.getSelectedItem().toString();
					if ( selectedcbx.equalsIgnoreCase("Agregar...") ) {
					}
				}
			});
			cbxPaciente.setBounds(70, 82, 152, 22);
			contentPanel.add(cbxPaciente);
		}
		{
			cbxEstado.setBounds(70, 156, 152, 22);
			contentPanel.add(cbxEstado);
			
			if (entrada == null) {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "Pendiente"}));
			}
			else {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "Pendiente", "Realizado", "Cancelado"}));
			}
		}
		{
			JCheckBox chbxRetraso = new JCheckBox("");
			chbxRetraso.setBounds(129, 188, 34, 25);
			contentPanel.add(chbxRetraso);
		}
		{
			JLabel lblSintomas = new JLabel("Sintomas:");
			lblSintomas.setBounds(293, 81, 56, 16);
			contentPanel.add(lblSintomas);
		}
		{
			
			cbxSintoma.setBounds(359, 78, 152, 22);
			contentPanel.add(cbxSintoma);
			
		}
		{
			JButton btnBorrarSintoma = new JButton("Borrar Sintoma Elegido");
			btnBorrarSintoma.setBounds(320, 158, 176, 25);
			contentPanel.add(btnBorrarSintoma);
		}
		{
			JLabel lblRetraso = new JLabel("Consulta Retrasada");
			lblRetraso.setBounds(12, 193, 130, 16);
			contentPanel.add(lblRetraso);
		}
		
		dateChooser.setBounds(70, 47, 154, 20);
		contentPanel.add(dateChooser);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String estado = cbxEstado.getSelectedItem().toString();
						int indf = cbxDoctor.getSelectedItem().toString().indexOf(":");
						//System.out.println(cbxDoctor.getSelectedItem().toString().substring(0, indf-1));
						doctor = (Doctor) Hospital.getInstance().buscarUsuarioByCode(cbxDoctor.getSelectedItem().toString().substring(0, indf-1));
						
						indf = cbxPaciente.getSelectedItem().toString().indexOf(":");
						paciente = (Paciente) Hospital.getInstance().buscarUsuarioByCode(cbxPaciente.getSelectedItem().toString().substring(0, indf-1));
						fecha = dateChooser.getDate();
						
						Consulta aux = new Consulta(txtCode.getText(),estado,fecha,(Paciente) paciente,(Doctor) doctor ,sintomas,vacunasColocadas);
						Hospital.getInstance().insertarConsulta(aux); 
						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
					    clean();
						
						try {
							Hospital.save();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
		 setDoctoryPaciente();
	}
	
	void setDoctoryPaciente() {
		cbxDoctor.addItem("<Seleccione>");
		cbxPaciente.addItem("<Seleccione>");
		for (Usuario aux : Hospital.getInstance().getMisCuentas() ) {
			String temp = aux.getCodigo()+" : "+aux.getNombre();
			if (aux instanceof Doctor) {
				cbxDoctor.addItem(temp);
			}
			
			if (aux instanceof Paciente) {
				cbxPaciente.addItem(temp);
			}
		}
		
		cbxDoctor.addItem("Agregar...");
		cbxPaciente.addItem("Agregar...");
	}
	void setSintomas() {
		for (String aux : Hospital.getInstance().getSintomasRegistrados() ) {
			cbxSintoma.addItem(aux);
		}
	}
	void clean() {
		txtCode.setText("CON-"+String.valueOf(Hospital.getInstance().generadorConsulta));
		cbxDoctor.setSelectedIndex(-1);
		cbxPaciente.setSelectedIndex(-1);
		cbxHorasDisponibles.setSelectedIndex(-1);
		cbxEstado.setSelectedIndex(-1);
		dateChooser.getDefaultLocale();
		sintomas = new ArrayList<String>();
	}
	
}
