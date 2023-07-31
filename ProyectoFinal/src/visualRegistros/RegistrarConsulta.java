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
	private JComboBox cbxHorasDisponibles = new JComboBox();
	private JDateChooser dateChooser = new JDateChooser();
	private JComboBox cbxSintoma = new JComboBox();
	
	private ArrayList<String>sintomas;
	private ArrayList<Vacuna>vacunasColocadas;
	private Doctor doctor;
	private Paciente paciente;
	private Consulta consulta;
	private Date fecha;
	private JTextField txtDoctor;
	private JTextField txtPaciente;
	private JTextField txtEstado;
	
	

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
			
		}
		{
			JCheckBox chbxRetraso = new JCheckBox("");
			chbxRetraso.setEnabled(false);
			chbxRetraso.setBounds(129, 188, 34, 25);
			contentPanel.add(chbxRetraso);
		}
		{
			JLabel lblSintomas = new JLabel("Sintomas:");
			lblSintomas.setBounds(275, 13, 79, 16);
			contentPanel.add(lblSintomas);
		}
		{
			
			cbxSintoma.setBounds(344, 10, 152, 22);
			contentPanel.add(cbxSintoma);
			
		}
		{
			JButton btnBorrarSintoma = new JButton("Borrar Sintoma Elegido");
			btnBorrarSintoma.setBounds(320, 42, 176, 25);
			contentPanel.add(btnBorrarSintoma);
		}
		{
			JLabel lblRetraso = new JLabel("Consulta Retrasada");
			lblRetraso.setBounds(12, 193, 130, 16);
			contentPanel.add(lblRetraso);
		}
		dateChooser.getCalendarButton().setEnabled(false);
		
		dateChooser.setBounds(70, 47, 154, 20);
		contentPanel.add(dateChooser);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setBounds(275, 85, 79, 16);
		contentPanel.add(lblEnfermedad);
		
		JComboBox cbxEnfermedad = new JComboBox();
		cbxEnfermedad.setBounds(354, 78, 142, 22);
		contentPanel.add(cbxEnfermedad);
		
		JLabel lblVacunas = new JLabel("Vacunas");
		lblVacunas.setBounds(275, 122, 56, 16);
		contentPanel.add(lblVacunas);
		
		JComboBox cbxVacunas = new JComboBox();
		cbxVacunas.setBounds(344, 118, 152, 22);
		contentPanel.add(cbxVacunas);
		
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Consulta aux = new Consulta(txtCode.getText(),consulta.getEstado(),fecha, consulta.getPaciente(),consulta.getDoctor(), sintomas, vacunasColocadas);
						Hospital.getInstance().insertarConsulta(aux); 
					   
						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						
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
		loadConsulta();
	}
	

	void loadConsulta(){
		try {
			
			txtDoctor.setText(consulta.getDoctor().getCodigo() + " : " + consulta.getDoctor().getNombre());
			txtPaciente.setText(consulta.getPaciente().getCodigo() + " : " + consulta.getPaciente().getNombre());
			
			txtEstado.setText(consulta.getEstado());
		
			dateChooser.setDate(consulta.getFecha());
			
			
		} catch (NullPointerException e) {
			return;
		}
	}
	
	
	void setSintomas() {
		for (String aux : Hospital.getInstance().getSintomasRegistrados() ) {
			cbxSintoma.addItem(aux);
		}
	}
}
