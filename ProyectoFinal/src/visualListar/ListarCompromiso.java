package visualListar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.ietf.jgss.Oid;

import logico.*;
import visualRegistros.*;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import com.toedter.calendar.JCalendar;

public class ListarCompromiso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  JTable table;
	private  Object[] fila;
	private  DefaultTableModel tableModel;
	private  JButton btnEliminar;
	private  JButton btnModificar;
	private int code;
	private String[] columnNames;
	String mode = "<Todos>";
	private JLabel lblBuscar;
	private JPanel panel_1;
	private JLabel lblBuscarCodigo;
	private JTextField txtBuscarCodigo;
	private JLabel lblBuscarDoctor;
	private JScrollPane scrollPane_1;
	private JLabel lblBuscarPaciente;
	private JScrollPane scrollPane_2;
	private JLabel lblBuscarFecha;
	private JPanel panelBuscarCalendar;
	private JCalendar calendar;
	private JPanel panelDetalles;
	private JLabel lblDetalles;
	private JLabel lblDetallesCodigo;
	private JTextField txtDetallesCodigo;
	private JLabel lblDetallesFecha;
	private JLabel lblDetallesPaciente;
	private JLabel lblDetallesDoctor;
	private JLabel lblDetallesEstado;
	private JLabel lblBuscarEstado;
	private JComboBox comboBox;
	private JTextField txtDetallesEstado;
	private JTextField txtDetallesFecha;
	private JTextField txtDetallesPaciente;
	private JTextField txtDetallesDoctor;
	
	private Cita selectedCita = null;
	private Consulta selectedConsulta = null;
	private JLabel lblDetallesFechaOriginal;
	private JTextField txtDetallesFechaOriginal;
	
	private JPanel PanelAlergia;
	private JScrollPane scrollPaneAlergia;
	private DefaultListModel<String> modelAlergia = new DefaultListModel<String>();



	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarCompromiso(final String type) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		columnNames = setColumns(type);
		
		setTitle("Listado de " + type + "s");
		setBounds(100, 100, 1168, 890);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de " + type + "s:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 624, 796);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 604, 757);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRow()>=0){
					
						btnEliminar.setEnabled(true);
					
					btnModificar.setEnabled(true);
					
					updateSelected(type);
					updateDetalles();
					//mode = cbxQuesoType.getSelectedItem().toString();
				}
			}
		});
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		loadSportMans(type);
		scrollPane.setViewportView(table);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setBounds(646, 31, 87, 16);
		contentPanel.add(lblBuscar);
		
		panel_1 = new JPanel();
		panel_1.setBounds(646, 60, 504, 436);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		lblBuscarCodigo = new JLabel("C\u00F3digo");
		lblBuscarCodigo.setBounds(12, 13, 56, 16);
		panel_1.add(lblBuscarCodigo);
		
		txtBuscarCodigo = new JTextField();
		txtBuscarCodigo.setBounds(80, 10, 152, 22);
		panel_1.add(txtBuscarCodigo);
		txtBuscarCodigo.setColumns(10);
		
		lblBuscarDoctor = new JLabel("Doctor");
		lblBuscarDoctor.setBounds(12, 330, 56, 16);
		panel_1.add(lblBuscarDoctor);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(80, 333, 390, 77);
		panel_1.add(scrollPane_1);
		
		lblBuscarPaciente = new JLabel("Paciente");
		lblBuscarPaciente.setBounds(12, 230, 56, 16);
		panel_1.add(lblBuscarPaciente);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(80, 229, 390, 88);
		panel_1.add(scrollPane_2);
		
		lblBuscarFecha = new JLabel("Fecha");
		lblBuscarFecha.setBounds(12, 43, 56, 16);
		panel_1.add(lblBuscarFecha);
		
		panelBuscarCalendar = new JPanel();
		panelBuscarCalendar.setLayout(null);
		panelBuscarCalendar.setBackground(Color.WHITE);
		panelBuscarCalendar.setBounds(80, 42, 390, 174);
		panel_1.add(panelBuscarCalendar);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 11, 368, 150);
		panelBuscarCalendar.add(calendar);
		
		lblBuscarEstado = new JLabel("Estado");
		lblBuscarEstado.setBounds(255, 13, 56, 16);
		panel_1.add(lblBuscarEstado);
		
		comboBox = new JComboBox();
		comboBox.setBounds(323, 10, 147, 22);
		panel_1.add(comboBox);
		
		panelDetalles = new JPanel();
		panelDetalles.setBounds(646, 538, 504, 269);
		contentPanel.add(panelDetalles);
		panelDetalles.setLayout(null);
		
		lblDetallesCodigo = new JLabel("C\u00F3digo");
		lblDetallesCodigo.setBounds(12, 13, 56, 16);
		panelDetalles.add(lblDetallesCodigo);
		
		txtDetallesCodigo = new JTextField();
		txtDetallesCodigo.setEditable(false);
		txtDetallesCodigo.setBounds(107, 10, 218, 22);
		panelDetalles.add(txtDetallesCodigo);
		txtDetallesCodigo.setColumns(10);
		
		lblDetallesFecha = new JLabel("Fecha");
		lblDetallesFecha.setBounds(12, 88, 56, 16);
		panelDetalles.add(lblDetallesFecha);
		
		lblDetallesPaciente = new JLabel("Paciente");
		lblDetallesPaciente.setBounds(12, 123, 56, 16);
		panelDetalles.add(lblDetallesPaciente);
		
		lblDetallesDoctor = new JLabel("Doctor");
		lblDetallesDoctor.setBounds(12, 158, 56, 16);
		panelDetalles.add(lblDetallesDoctor);
		
		lblDetallesEstado = new JLabel("Estado");
		lblDetallesEstado.setBounds(12, 42, 56, 16);
		panelDetalles.add(lblDetallesEstado);
		
		txtDetallesEstado = new JTextField();
		txtDetallesEstado.setEditable(false);
		txtDetallesEstado.setBounds(107, 42, 218, 22);
		panelDetalles.add(txtDetallesEstado);
		txtDetallesEstado.setColumns(10);
		
		txtDetallesFecha = new JTextField();
		txtDetallesFecha.setEditable(false);
		txtDetallesFecha.setColumns(10);
		txtDetallesFecha.setBounds(107, 88, 218, 22);
		panelDetalles.add(txtDetallesFecha);
		
		txtDetallesPaciente = new JTextField();
		txtDetallesPaciente.setEditable(false);
		txtDetallesPaciente.setColumns(10);
		txtDetallesPaciente.setBounds(107, 123, 218, 22);
		panelDetalles.add(txtDetallesPaciente);
		
		txtDetallesDoctor = new JTextField();
		txtDetallesDoctor.setEditable(false);
		txtDetallesDoctor.setColumns(10);
		txtDetallesDoctor.setBounds(107, 158, 218, 22);
		panelDetalles.add(txtDetallesDoctor);
		
		lblDetallesFechaOriginal = new JLabel("Fecha Original");
		lblDetallesFechaOriginal.setBounds(12, 209, 83, 16);
		panelDetalles.add(lblDetallesFechaOriginal);
		
		txtDetallesFechaOriginal = new JTextField();
		txtDetallesFechaOriginal.setEditable(false);
		txtDetallesFechaOriginal.setColumns(10);
		txtDetallesFechaOriginal.setBounds(107, 209, 218, 22);
		panelDetalles.add(txtDetallesFechaOriginal);
		
		PanelAlergia = new JPanel();
		PanelAlergia.setBounds(107, 193, 207, 64);
		panelDetalles.add(PanelAlergia);
		PanelAlergia.setLayout(null);
		PanelAlergia.setVisible(false);
		
		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(0, 0, 207, 64);
		PanelAlergia.add(scrollPaneAlergia);
		
		JList listAlergia = new JList();
		listAlergia.setModel(modelAlergia);
		scrollPaneAlergia.setViewportView(listAlergia);
		
		
		
		lblDetalles = new JLabel("Detalles:");
		lblDetalles.setBounds(646, 509, 56, 16);
		contentPanel.add(lblDetalles);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					

					if (type.equalsIgnoreCase("cita")) {	
						
						RegistrarCita modAdmin = new RegistrarCita(Hospital.getInstance().getMisCitas().get(table.getSelectedRow()));
						modAdmin.setModal(true);
						modAdmin.setLocationRelativeTo(null);
						modAdmin.setVisible(true);
						
					}

					if (type.equalsIgnoreCase("consulta")) {	
						RegistrarConsulta modUser = new RegistrarConsulta(Hospital.getInstance().getMisConsultas().get(table.getSelectedRow()));
						modUser.setModal(true);
						modUser.setLocationRelativeTo(null);
						modUser.setVisible(true);	
					}
					loadSportMans(type);
					
				}
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int delete = JOptionPane.showConfirmDialog(null, "Realmente desea Eliminar esta "+type+"?", null, JOptionPane.YES_NO_OPTION);
					    
						if (delete == JOptionPane.YES_OPTION)
					    {				
					    	switch (type)
					    	{
					    	case "cita":
					    		
					    		Cita auxCita = Hospital.getInstance().buscarCitasByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
					    		System.out.println(auxCita);
					    		auxCita.getPaciente().getMiRegistro().getMisCitas().remove(auxCita);
					    		Hospital.getInstance().getMisCitas().remove(auxCita);
					    		
					    		break;
					    	
					    	case "consulta":
					    		
					    		Consulta auxConsulta = Hospital.getInstance().buscarConsultasByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
					    		System.out.println(auxConsulta);
					    		auxConsulta.getPaciente().getMiRegistro().getMisConsultas().remove(auxConsulta);
					    		Hospital.getInstance().getMisConsultas().remove(auxConsulta);
					    		
					    		break;
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
					    	
							loadSportMans(type);
						
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
							
					    }

					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
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
	}



	private void loadSportMans(String selection) {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		
		switch (selection) {
		case "consulta":
		for (Consulta aux : Hospital.getInstance().getMisConsultas())
		{
			fila[0] = aux.getCodigo();
			fila[1] = aux.getFecha();
			fila[2] = aux.getEstado();
			fila[3] = aux.getPaciente().getNombre();
			fila[4] = aux.getDoctor().getNombre();
			
			tableModel.addRow(fila);
		}
		
		break;
	case "cita":
		
		for (Cita aux : Hospital.getInstance().getMisCitas())
		{
			System.out.println(fila[0] + " :");
			fila[0] = aux.getCodigo();
			fila[1] = aux.getFechaReal();
			if (aux.getHora() > 12)
			{
				fila[2] = Integer.toString(aux.getHora()-12) + ":00PM";
			}
			else
			{
				if (aux.getHora() == 12)
				{
					fila[2] = Integer.toString(aux.getHora()) + ":00PM";
				}
				else 
				{
					fila[2] = Integer.toString(aux.getHora()) + ":00AM";

				}
			}
			
			fila[3] = aux.getEstado();
			fila[4] = aux.getPaciente().getNombre();
			fila[5] = aux.getDoctor().getNombre();
			
			tableModel.addRow(fila);
		}
		
		break;
	}

		
		
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(75);
		columnModel.getColumn(3).setPreferredWidth(85);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		
		/*if(tableModel.getRowCount()==0){
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
		}*/
	}
	
	private String[] setColumns(String type)
	{
			if (type == "cita")
			{
				String[] columnNames = {"Código", "Fecha","Hora", "Estado","Paciente", "Doctor"};
				return columnNames;
			}
			else
			{
				String[] columnNames = {"Código", "Fecha", "Estado","Paciente", "Doctor", "Sintomas"};
				return columnNames;
			}
		
	}
	
	private void modificarCompromiso(String type)
	{

		switch (type)
		{

		case "cita":
			
			RegistrarCita modCita = new RegistrarCita(Hospital.getInstance().getMisCitas().get(table.getSelectedRow()));
			modCita.setModal(true);
			modCita.setLocationRelativeTo(null);
			modCita.setVisible(true);
			
			break;
		case "consulta":
			
			RegistrarConsulta modConsulta = new RegistrarConsulta(Hospital.getInstance().getMisConsultas().get(table.getSelectedRow()));
			modConsulta.setModal(true);
			modConsulta.setLocationRelativeTo(null);
			modConsulta.setVisible(true);
			
			break;
		
		}
		
	}
	
	private void updateSelected(String type)
	{
		if (type.equalsIgnoreCase("cita")) {	
			
			selectedCita = Hospital.getInstance().getMisCitas().get(table.getSelectedRow());
			return;
			
		}

		if (type.equalsIgnoreCase("consulta")) {	
			selectedConsulta = Hospital.getInstance().getMisConsultas().get(table.getSelectedRow());
			return;
		}
	}
	
	private void updateDetalles()
	{
		
		if (selectedCita != null)
		{
			lblDetallesCodigo.setVisible(true);
			lblDetallesEstado.setVisible(true);
			lblDetallesFecha.setVisible(true);
			lblDetallesFechaOriginal.setVisible(true);
			lblDetallesPaciente.setVisible(true);
			lblDetallesPaciente.setVisible(true);
			PanelAlergia.setVisible(false);
			
			
			
			txtDetallesCodigo.setText(selectedCita.getCodigo());
			txtDetallesEstado.setText(selectedCita.getEstado());
			txtDetallesFecha.setText(selectedCita.getFechaReal().toString());
			txtDetallesFechaOriginal.setText(selectedCita.getFechaReal().toString());
			txtDetallesPaciente.setText(selectedCita.getPaciente().getNombre());
			txtDetallesDoctor.setText(selectedCita.getDoctor().getNombre());
			
			
		}
		else if (selectedConsulta != null)
		{
			PanelAlergia.setVisible(true);
			lblDetallesCodigo.setVisible(true);
			lblDetallesEstado.setVisible(true);
			lblDetallesFecha.setVisible(true);
			lblDetallesPaciente.setVisible(true);
			lblDetallesPaciente.setVisible(true);
			lblDetallesFechaOriginal.setVisible(true);
			txtDetallesFechaOriginal.setVisible(false);
			
			reloadAlergiaCuadro();
			
			txtDetallesCodigo.setText(selectedConsulta.getCodigo());
			txtDetallesEstado.setText(selectedConsulta.getEstado());
			txtDetallesFecha.setText(selectedConsulta.getFecha().toString());
			//txtDetallesFechaOriginal.setText(selectedConsulta.getFechaReal().toString());
			txtDetallesPaciente.setText(selectedConsulta.getPaciente().getNombre());
			txtDetallesDoctor.setText(selectedConsulta.getDoctor().getNombre());
			lblDetallesFechaOriginal.setText("Síntomas");
		}
		else
		{
			
			txtDetallesEstado.setText("Seleccione un Elemento");
			txtDetallesFecha.setText("Para ver Detalles.");
			
			lblDetallesCodigo.setVisible(false);
			lblDetallesEstado.setVisible(false);
			lblDetallesFecha.setVisible(false);
			lblDetallesFechaOriginal.setVisible(false);
			lblDetallesPaciente.setVisible(false);
			lblDetallesPaciente.setVisible(false);
			PanelAlergia.setVisible(false);
			
			
			
			
		}
	}
	
	private void reloadAlergiaCuadro()
	{
		modelAlergia.removeAllElements();
		for (String auxString : selectedConsulta.getSintomas())
		{
			System.out.println(auxString);
			modelAlergia.addElement(auxString);
		}
	}
}
