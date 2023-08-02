package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Hospital;
import logico.Cita;
import logico.Consulta;
import logico.Usuario;
import logico.Vacuna;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JComboBox cbxHorasDisponibles = new JComboBox();
	private JDateChooser dateChooser = new JDateChooser();
	private JComboBox cbxEstado = new JComboBox();
	private Doctor doc = null;
	private Paciente pac = null;
	private Cita cita;
	private Date fecha;
	private JCheckBox chbxRetraso;
	
	private  DefaultTableModel tableModelDoctor;
	private  DefaultTableModel tableModelPaciente;
	private JTable tableDoctor;
	private JTable tablePaciente;

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
		
		cita = entrada;
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		doc = null;
		pac = null;
		setTitle("Registrar Cita");
		setBounds(100, 100, 502, 437);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
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
			lblDoctor.setBounds(12, 129, 56, 16);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(250, 126, 56, 16);
			contentPanel.add(lblPaciente);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(12, 82, 56, 16);
			contentPanel.add(lblEstado);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(67, 10, 97, 22);
			try {
				txtCode.setText(cita.getCodigo());
			} catch (NullPointerException e) {
				txtCode.setText("CI-"+String.valueOf(Hospital.getInstance().generadorCita));
			}
			
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			if (entrada == null) {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Pendiente"}));
				cbxEstado.setSelectedIndex(1);
			}
			else {
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Pendiente", "Realizado", "Cancelado"}));
			}
			
			cbxEstado.setBounds(67, 79, 154, 22);
			contentPanel.add(cbxEstado);
		}
		
		chbxRetraso = new JCheckBox("");
		chbxRetraso.setBounds(339, 41, 36, 25);
		contentPanel.add(chbxRetraso);
		{
			JLabel lblNewLabel = new JLabel("Cita Retrasada");
			lblNewLabel.setBounds(250, 45, 125, 16);
			contentPanel.add(lblNewLabel);
		}
		
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
				/*
				System.out.println("Paciente: " + pac);
				System.out.println("Doctor: " + doc);
				System.out.println("Fecha: " + dateChooser.getDate());
				System.out.println("");*/

				checkDatosCompletos();
			}
		});
		
		
		dateChooser.setBounds(67, 46, 154, 20);
		fecha = dateChooser.getDate();
		contentPanel.add(dateChooser);
		cbxHorasDisponibles.setModel(new DefaultComboBoxModel(new String[] {""}));
		cbxHorasDisponibles.setEnabled(false);
				
		cbxHorasDisponibles.setMaximumRowCount(15);
		cbxHorasDisponibles.setBounds(298, 76, 161, 22);
		contentPanel.add(cbxHorasDisponibles);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(252, 79, 36, 16);
		contentPanel.add(lblHora);
		{
			JScrollPane scrollPaneDoctor = new JScrollPane();
			scrollPaneDoctor.setBounds(22, 158, 199, 184);
			contentPanel.add(scrollPaneDoctor);
			{
				tableDoctor = new JTable();
				tableDoctor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(tableDoctor.getSelectedRow()>=0){
							doc = (Doctor)Hospital.getInstance().buscarUsuarioByCode(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
							System.out.println(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
							checkDatosCompletos();

						}
					}
				});
				scrollPaneDoctor.setViewportView(tableDoctor);
			}
			
			tableModelDoctor = new DefaultTableModel();
			tableModelDoctor.setColumnIdentifiers(setColumns());
			loadListDoctor();
			scrollPaneDoctor.setViewportView(tableDoctor);
		}
		{
			JScrollPane scrollPanePaciente = new JScrollPane();
			scrollPanePaciente.setBounds(250, 158, 209, 184);
			contentPanel.add(scrollPanePaciente);
			{
				tablePaciente = new JTable();
				tablePaciente.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(tablePaciente.getSelectedRow()>=0){
							pac = (Paciente)Hospital.getInstance().buscarUsuarioByCode(tablePaciente.getValueAt(tablePaciente.getSelectedRow(), 0).toString());
							System.out.println(tablePaciente.getValueAt(tablePaciente.getSelectedRow(), 0).toString());
							checkDatosCompletos();
						}
					}
				});
				scrollPanePaciente.setRowHeaderView(tablePaciente);
			}
			tableModelPaciente = new DefaultTableModel();
			tableModelPaciente.setColumnIdentifiers(setColumns());
			loadListPaciente();
			scrollPanePaciente.setViewportView(tablePaciente);
			
		}
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
							
							estado = cbxEstado.getSelectedItem().toString();
							Cita aux = new Cita( txtCode.getText(),estado,fecha, hora,((Doctor)doc), ((Paciente)pac) );
							Consulta auxConsulta;
							pac.getMiRegistro().setEsPaciente(true);
							
							if (cita != null)
							{
								auxConsulta = new Consulta("CON-" + cita.getCodigo(), estado, fecha, ((Paciente)pac), ((Doctor)doc), new ArrayList<String>(), new ArrayList<Vacuna>(), null);
							}
							else {
								auxConsulta = new Consulta("CON-" + String.valueOf(Hospital.getInstance().generadorConsulta), estado, fecha, ((Paciente)pac), ((Doctor)doc), new ArrayList<String>(), new ArrayList<Vacuna>(), null);
							}
						
							
							
							if (cita == null)
							{
								System.out.println("Fecha al intentar insertar Cita: " + aux.getFechaReal());
								Hospital.getInstance().insertarCita(aux);
								Hospital.getInstance().insertarConsulta(auxConsulta);
								aux.getPaciente().getMiRegistro().getMisCitas().add(aux);
								aux.getPaciente().getMiRegistro().getMisConsultas().add(auxConsulta);
							}
							else
							{
								System.out.println("Fecha al intentar modificar Cita: " + aux.getFechaReal());
								Hospital.getInstance().modificarCita(aux);
								Hospital.getInstance().modificarConsulta(auxConsulta);
								
								//Ingresar Cita y consulta modificada al registro del paciente
								int index = -1;
								index = Hospital.getInstance().buscarIndexByCita(aux.getCodigo());
								aux.getPaciente().getMiRegistro().getMisCitas().set(index, aux);
								
								index = -1;
								index = Hospital.getInstance().buscarIndexByConsulta(auxConsulta.getCodigo());
								auxConsulta.getPaciente().getMiRegistro().getMisConsultas().set(index, auxConsulta);
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
							JOptionPane.showMessageDialog(null, "Registro satisfactorio\nSe ha generado una Consulta para esta Cita", "Información", JOptionPane.INFORMATION_MESSAGE);
						    clean();
						    
						    if (cita != null)
						    {
						    	dispose();
						    }
						    
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
		loadCita();
	}
	
	void loadCita(){
		
		
		try {
			
			switch (cita.getEstado())
			{
			case "Pendiente":
				cbxEstado.setSelectedIndex(1);
				break;
			}
			
			//cbxHorasDisponibles.setSelectedIndex(cita.getHora() - 8);
			
			
			dateChooser.setDate(cita.getFechaReal());
			
			if (cita.getFechaReal() != cita.getFechaOriginal())
			{
				chbxRetraso.setSelected(true);
			}
			else 
			{
				chbxRetraso.setSelected(false);
			}
			
			
		} catch (NullPointerException e) {
			return;
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
		if (cita != null)
		{
			int horacita = cita.getHora();
			
			if (horacita < 12)
			{
				cbxHorasDisponibles.addItem(horacita + ":00 AM");
			}
			else if (horacita == 12)
			{
				cbxHorasDisponibles.addItem(horacita + ":00 PM");
			}
			else if (horacita > 12)
			{
				cbxHorasDisponibles.addItem((horacita - 12) + ":00 PM");
			}
		}
		
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
	
	private void clean() {
		txtCode.setText("CI-"+String.valueOf(Hospital.getInstance().generadorCita));
		cbxHorasDisponibles.removeAllItems();
		cbxEstado.setSelectedIndex(1);
		cbxHorasDisponibles.setEnabled(false);
		
		dateChooser.setDate(null);
		pac = null;
		doc = null;
	}
	
	private void loadListDoctor()
	{
		tableModelDoctor.setRowCount(0);
		Object[] fila = new Object[tableModelDoctor.getColumnCount()];
		
		for (Usuario aux : Hospital.getInstance().getMisCuentas()) {
			if(aux instanceof Doctor){
				fila[0] = aux.getCodigo();
				fila[1] = aux.getNombre();

	
				tableModelDoctor.addRow(fila);
			}
		}
		
		tableDoctor.setModel(tableModelDoctor);
		tableDoctor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableDoctor.getTableHeader().setReorderingAllowed(false);
		tableDoctor.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnModel = tableDoctor.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(130);
	}
	
	private void loadListPaciente()
	{
		tableModelPaciente.setRowCount(0);
		Object[] fila = new Object[tableModelPaciente.getColumnCount()];
		
		for (Usuario aux : Hospital.getInstance().getMisCuentas()) {
			if(aux instanceof Paciente){
				fila[0] = aux.getCodigo();
				fila[1] = aux.getNombre();

	
				tableModelPaciente.addRow(fila);
			}
		}
		
		tablePaciente.setModel(tableModelPaciente);
		tablePaciente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablePaciente.getTableHeader().setReorderingAllowed(false);
		tablePaciente.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnModel = tablePaciente.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(130);
	}
	
	private String[] setColumns()
	{
		
			String[] columnNames = {"Código", "Nombre"};
			return columnNames;
		
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
		else if (doc == null)
		{System.out.println("Doc Vacio");
			return true;
		}
		else if (pac == null)
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
	
	private void checkDatosCompletos()
	{
		if (dateChooser.getDate() != null && doc != null && pac != null)
		{
			setHorasDisponibles();
			cbxHorasDisponibles.setEnabled(true);
		}
	}
}
