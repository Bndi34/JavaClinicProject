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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListarCompromiso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  JTable table;
	private  Object[] fila;
	private  DefaultTableModel tableModel;
	private  JButton btnEliminar;
	private  JButton btnModificar;
	private JButton btnDetalles;
	private int code;
	private String[] columnNames;
	String mode = "<Todos>";



	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarCompromiso(final String type) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		columnNames = setColumns(type);
		
		setTitle("Listado de " + type + "s");
		setBounds(100, 100, 650, 376);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de " + type + "s:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 624, 293);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 604, 256);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRow()>=0){
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					btnDetalles.setEnabled(true);
					//mode = cbxQuesoType.getSelectedItem().toString();
				}
			}
		});
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		loadSportMans(type);
		scrollPane.setViewportView(table);
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
					
				}
			});
			
			btnDetalles = new JButton("Detalles");
			btnDetalles.setEnabled(false);
			buttonPane.add(btnDetalles);
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int delete = JOptionPane.showConfirmDialog(null, "Realmente desea Eliminar esta cuenta?", null, JOptionPane.YES_NO_OPTION);
					    if (delete == JOptionPane.YES_OPTION)
					    {				
					    	//sistema para revisar que tipo se quiere eliminar
					    	/*
							Usuario aux = Hospital.getInstance().buscarUsuarioByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
							if (aux.getCodigo() != codigoCuentaActual)
							
								Hospital.getInstance().getMisCuentas().remove(aux);
*/
								loadSportMans(type);
						
							
							
							
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
		TableColumnModel columnModel = table.getColumnModel();
		/*
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(130);
		columnModel.getColumn(4).setPreferredWidth(81);
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
}
