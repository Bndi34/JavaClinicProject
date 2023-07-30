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
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JComboBox cbxDoctor = new JComboBox();
	private JComboBox cbxPaciente = new JComboBox();
	private JComboBox cbxHorasDisponibles = new JComboBox();
	private JDateChooser dateChooser = new JDateChooser();
	private JComboBox cbxEstado = new JComboBox();
	private Doctor doc;
	private Paciente pac;
	private Cita cita;
	private Date fecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita(Cita entrada) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		doc = null;
		pac = null;
		setTitle("Registrar Cita");
		setBounds(100, 100, 432, 300);
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
			txtCode.setBounds(67, 10, 97, 22);
			txtCode.setText("CI-"+String.valueOf(Hospital.getInstance().generadorCita));
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			cbxDoctor.setModel(new DefaultComboBoxModel(new String[] {""}));
			cbxDoctor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						int indf = cbxDoctor.getSelectedItem().toString().indexOf(":");
						String docCode = cbxDoctor.getSelectedItem().toString().substring(0, indf-1);
						System.out.println(docCode);
						doc = (Doctor)Hospital.getInstance().buscarUsuarioByCode(docCode);
						
					} catch (IndexOutOfBoundsException e2) {
					}
									
					
					System.out.println("Paciente: " + pac);
					System.out.println("Doctor: " + doc);
					System.out.println("Fecha: " + dateChooser.getDate());
					System.out.println("");
					
					if (dateChooser.getDate() != null && doc != null && pac != null)
					{
						setHorasDisponibles();
						cbxHorasDisponibles.setEnabled(true);
					}
				}
			});
			cbxDoctor.setBounds(67, 84, 154, 22);
			contentPanel.add(cbxDoctor);
		}
		{
			cbxPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						int indf = cbxPaciente.getSelectedItem().toString().indexOf(":");
						String docCode = cbxPaciente.getSelectedItem().toString().substring(0, indf-1);
						System.out.println(docCode);
						pac = (Paciente)Hospital.getInstance().buscarUsuarioByCode(docCode);
						
					} catch (IndexOutOfBoundsException e2) {
						// TODO: handle exception
					}
					
					System.out.println("Paciente: " + pac);
					System.out.println("Doctor: " + doc);
					System.out.println("Fecha: " + dateChooser.getDate());
					System.out.println("");
					
					if (dateChooser.getDate() != null && doc != null && pac != null)
					{
						setHorasDisponibles();
						cbxHorasDisponibles.setEnabled(true);
					}
				}
			});
			cbxPaciente.setModel(new DefaultComboBoxModel(new String[] {""}));
			cbxPaciente.setBounds(67, 121, 154, 22);
			contentPanel.add(cbxPaciente);
		}
		{
			if (entrada == null) {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Pendiente"}));
				cbxEstado.setSelectedIndex(1);
			}
			else {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Pendiente", "Realizado", "Cancelado"}));
			}
			
			cbxEstado.setBounds(67, 158, 154, 22);
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
		
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
				System.out.println("Paciente: " + pac);
				System.out.println("Doctor: " + doc);
				System.out.println("Fecha: " + dateChooser.getDate());
				System.out.println("");

				if (dateChooser.getDate() != null && doc != null && pac != null)
				{
					setHorasDisponibles();
					cbxHorasDisponibles.setEnabled(true);
				}
			}
		});
		
		
		dateChooser.setBounds(67, 46, 154, 20);
		fecha = dateChooser.getDate();
		contentPanel.add(dateChooser);
		cbxHorasDisponibles.setModel(new DefaultComboBoxModel(new String[] {""}));
		cbxHorasDisponibles.setEnabled(false);
				
		cbxHorasDisponibles.setMaximumRowCount(15);
		cbxHorasDisponibles.setBounds(286, 47, 120, 22);
		contentPanel.add(cbxHorasDisponibles);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(240, 50, 36, 16);
		contentPanel.add(lblHora);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!hayEspacioVacio())
						{
							String estado;
							fecha = dateChooser.getDate();
						
							int hora = getHoraInBox();
							//System.out.println(hora);
							
							//Acá se debería convertir el string de cbxHorasDisponibles a un int como debería hacerlo cbxHorasDisponibles.getSelectedItem().toString())
							estado = cbxEstado.getSelectedItem().toString();
							Cita aux = new Cita( txtCode.getText(),estado,fecha, hora,((Doctor)doc), ((Paciente)pac) );
							Hospital.getInstance().insertarCita(aux);
							
							Consulta auxConsulta = new Consulta(txtCode.getText(), estado, fecha, ((Paciente)pac), ((Doctor)doc), new ArrayList<>(), new ArrayList<>());
							Hospital.getInstance().insertarConsulta(auxConsulta);
							
							for(Cita auxCita : Hospital.getInstance().getMisCitas())
							{
								System.out.println(auxCita);
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
							JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						    clean();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
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
		setDoctoryPaciente();
	}
	
	void loadCita(){
		setDoctoryPaciente();
	}
	
	void setDoctoryPaciente() {
		
		cbxDoctor.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		cbxPaciente.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		//cbxDoctor.addItem("<Seleccione>");
		//cbxPaciente.addItem("<Seleccione>");
		for (Usuario aux : Hospital.getInstance().getMisCuentas() ) {

			if (aux instanceof Paciente) {
				cbxPaciente.addItem(aux.getCodigo() + " : " + aux.getNombre());
			}
			
			else if (aux instanceof Doctor) {
				cbxDoctor.addItem(aux.getCodigo() + " : " + aux.getNombre());
			}
		}
	}
	
	void setHorasDisponibles() {
		
		fecha = dateChooser.getDate();
		
		ArrayList<Consulta>ConsultaDia = null;
		ArrayList<Cita>CitaDia = null;
		
		
		//ConsultaDia = Hospital.getInstance().buscarHorasDisponiblesConsultas(fecha, doc);
		CitaDia = Hospital.getInstance().buscarHorasDisponiblesCitas(fecha, doc);
		System.out.println("Cita Dia size: "+CitaDia.size());
		
		//eliminar horas ocupadas
		
		cbxHorasDisponibles.removeAllItems();
		cbxHorasDisponibles.setSelectedItem("<Seleccione>");
		
		for (int horas = 8; horas <= 20; horas++)
		{
			colocarHora(horas);
		}
		System.out.println("Eliminar Horas");
		for (int horas = 8; horas <= 20; horas++) {
			if (CitaDia != null) {
				if (CitaDia.size() > 0)
				{
					for (Cita aux : CitaDia ) {
						
						if (aux.getHora() == horas && aux.getFechaReal().getDay() == dateChooser.getDate().getDay() && aux.getFechaReal().getMonth() == dateChooser.getDate().getMonth() && aux.getFechaReal().getYear() == dateChooser.getDate().getYear()) 
						{
							System.out.println("Deberia entrar");
							eliminarHoras(horas);
						}
						System.out.println("");
					}
				}	
			}
		}
	}
	
	void colocarHora(int horas) 
	{
		if (horas <= 12)
		{
			if (horas == 12 )
			{
				cbxHorasDisponibles.addItem(String.valueOf(horas)+":00 PM");
			}
			else 
			{
				cbxHorasDisponibles.addItem(String.valueOf(horas)+":00 AM");

			}

		}
		else 
		{
			
			cbxHorasDisponibles.addItem(String.valueOf(horas - 12)+":00 PM");
			
		}
		
		System.out.println("Hora Colocada: " + horas);
	}
	
	void eliminarHoras(int horas) 
	{
		System.out.println("Eliminar Horas IN");
		System.out.println("Hora: "+ horas);
		if (horas <= 12)
		{
			if (horas == 12 )
			{
				System.out.println("Item a borrar: "+String.valueOf(horas)+":00 AM");
				System.out.println("Item a las 8: "+cbxHorasDisponibles.getItemAt(0));
				cbxHorasDisponibles.removeItem(String.valueOf(horas)+":00 PM");
				
			}
			else 
			{
				System.out.println("Item a borrar: "+String.valueOf(horas)+":00 AM");
				System.out.println("Item a las 8: "+cbxHorasDisponibles.getItemAt(0));
				cbxHorasDisponibles.removeItem(String.valueOf(horas)+":00 AM");

			}

		}
		else 
		{
			System.out.println("Item a borrar: "+String.valueOf(horas)+":00 AM");
			System.out.println("Item a las 8: "+cbxHorasDisponibles.getItemAt(0));
			cbxHorasDisponibles.removeItem(String.valueOf(horas - 12)+":00 PM");
			
		}
		
		System.out.println("Hora Removida: " + horas);
	}
	
	void clean() {
		txtCode.setText("CI-"+String.valueOf(Hospital.getInstance().generadorCita));
		cbxDoctor.setSelectedIndex(0);
		cbxPaciente.setSelectedIndex(0);
		cbxHorasDisponibles.removeAllItems();
		cbxEstado.setSelectedIndex(1);
		cbxHorasDisponibles.setEnabled(false);
		
		dateChooser.setDate(null);
		pac = null;
		doc = null;
	}
	
	private boolean hayEspacioVacio()
	{
		if (cbxEstado.getSelectedIndex() < 1)
		{
			System.out.println("Estado Vacio");
			return true;
		}
		else if (dateChooser.getDate() == null)
		{System.out.println("Date Vacio");
			return true;
		}
		else if (cbxDoctor.getSelectedIndex() < 1)
		{System.out.println("Doc Vacio");
			return true;
		}
		else if (cbxPaciente.getSelectedIndex() < 1)
		{System.out.println("Paciente Vacio");
			return true;
		}
		
		return false;
	}
	
	private int getHoraInBox() 
	{
		int indf = cbxHorasDisponibles.getSelectedItem().toString().indexOf("M");
		String selected = cbxHorasDisponibles.getSelectedItem().toString().substring(indf-1);
		int hora = -1;
		

		
		if (selected.equalsIgnoreCase("AM"))
		{
			System.out.println("AM In");
			indf = cbxHorasDisponibles.getSelectedItem().toString().indexOf(":");
			hora = Integer.valueOf(cbxHorasDisponibles.getSelectedItem().toString().substring(0, indf));
			
		}
		else if (selected.equalsIgnoreCase("PM"))
		{
			System.out.println("PM In");
			indf = cbxHorasDisponibles.getSelectedItem().toString().indexOf(":");
			hora = Integer.valueOf(cbxHorasDisponibles.getSelectedItem().toString().substring(0, indf));
			
			if (hora != 12)
			{
				hora += 12;
			}
		}
		
		System.out.println("Hora Got: " + hora);
		return hora;
	}
}
