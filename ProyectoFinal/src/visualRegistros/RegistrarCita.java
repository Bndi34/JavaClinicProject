package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Hospital;
import logico.Cita;
import logico.Consulta;
import logico.Usuario;
import logico.Doctor;
import logico.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.Date;


public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	JComboBox cbxDoctor = new JComboBox();
	JComboBox cbxPaciente = new JComboBox();
	JComboBox cbxHorasDisponibles = new JComboBox();
	JDateChooser dateChooser = new JDateChooser();
	JComboBox cbxEstado = new JComboBox();
	private Doctor doc;
	private Cita cita;
	private Date fecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita("",null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita(String tittle, Cita entrada) {
		doc = null;
		setTitle("Registrar Cita");
		setBounds(100, 100, 350, 300);
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
			lblDate.setBounds(12, 50, 56, 16);
			contentPanel.add(lblDate);
		}
		{
			JLabel lblDoctor = new JLabel("Doctor");
			lblDoctor.setBounds(12, 87, 56, 16);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(12, 124, 56, 16);
			contentPanel.add(lblPaciente);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(12, 161, 56, 16);
			contentPanel.add(lblEstado);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(57, 10, 97, 22);
			txtCode.setText("CI-"+String.valueOf(Hospital.getInstance().generadorCita));
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			cbxDoctor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (doc != null) {
						setHorasDisponibles();
						cbxHorasDisponibles.setEnabled(true);
					}
				}
			});
			cbxDoctor.setBounds(57, 84, 203, 22);
			contentPanel.add(cbxDoctor);
		}
		{
			cbxPaciente.setBounds(57, 121, 203, 22);
			contentPanel.add(cbxPaciente);
		}
		{
			if (entrada == null) {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "Pendiente"}));
			}
			else {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "Pendiente", "Realizado", "Cancelado"}));
			}
			cbxEstado.setBounds(57, 158, 120, 22);
			contentPanel.add(cbxEstado);
		}
		
		JCheckBox chbxRetraso = new JCheckBox("");
		chbxRetraso.setBounds(80, 194, 36, 25);
		contentPanel.add(chbxRetraso);
		{
			JLabel lblNewLabel = new JLabel("Cita Retrasada");
			lblNewLabel.setBounds(6, 198, 80, 16);
			contentPanel.add(lblNewLabel);
		}
		
		dateChooser.setBounds(57, 46, 97, 20);
		fecha = dateChooser.getDate();
		contentPanel.add(dateChooser);
		cbxHorasDisponibles.setEnabled(false);
		
		cbxHorasDisponibles.setMaximumRowCount(15);
		cbxHorasDisponibles.setBounds(204, 46, 120, 22);
		contentPanel.add(cbxHorasDisponibles);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(164, 50, 36, 16);
		contentPanel.add(lblHora);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario doc, paciente;
						String estado;
						doc = Hospital.getInstance().buscarUsuarioByCode(cbxDoctor.getSelectedItem().toString());
						paciente = Hospital.getInstance().buscarUsuarioByCode(cbxPaciente.getSelectedItem().toString());
						fecha = dateChooser.getDate();
						fecha.setHours(cbxHorasDisponibles.getSelectedIndex() + 8);
						//Acá se debería convertir el string de cbxHorasDisponibles a un int como debería hacerlo cbxHorasDisponibles.getSelectedItem().toString())
						estado = cbxEstado.getSelectedItem().toString();
						Cita aux = new Cita( txtCode.getText(),estado,fecha,null,((Doctor)doc), ((Paciente)paciente) );
						Hospital.getInstance().insertarCita(aux);
						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
					    clean();
					}
				});
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
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
		loadCita();
	}
	
	void loadCita(){
		
	}
	
	void setDoctoryPaciente() {
		for (Usuario aux : Hospital.getInstance().getMisCuentas() ) {
			if (aux instanceof Doctor) {
				cbxDoctor.addItem(aux.getCodigo());
			}
			
			if (aux instanceof Paciente) {
				cbxPaciente.addItem(aux.getCodigo());
			}
		}
	}
	
	void setHorasDisponibles() {
		ArrayList<Consulta>ConsultaDia = null;
		ArrayList<Cita>CitaDia = null;
		ConsultaDia = Hospital.getInstance().buscarHorasDisponiblesConsultas(fecha, doc);
		CitaDia = Hospital.getInstance().buscarHorasDisponiblesCitas(fecha, doc);
		//eliminar horas ocupadas
		
		boolean validador = false;
		for (int horas = 8; horas <= 20; horas++) {
			validador = false;
			if (ConsultaDia != null) {
				for (Consulta aux : ConsultaDia ) {
					if (aux.getFecha().getHours() == horas ) {
						validador = true;
					}
				}
			}
			
			if (!validador) {
				cbxHorasDisponibles.addItem(String.valueOf(horas)+":00");
			}
		}
	}
	void clean() {
		cbxDoctor.setSelectedIndex(-1);
		cbxPaciente.setSelectedIndex(-1);
		cbxHorasDisponibles.setSelectedIndex(-1);
		cbxEstado.setSelectedIndex(-1);
	}
}
