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

import logico.*;
import visualRegistros.*;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JList;

public class ListarCuenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  JTable table;
	private  Object[] fila;
	private  DefaultTableModel tableModel;
	private  JButton btnEliminar;
	private  JButton btnModificar;
	private int code;
	//private String[] columnNames;
	String mode = "<Todos>";
	Usuario selected = null;
	JComboBox cbxQuesoType;
	private JButton btnRegistrar;
	
	
	private JTextField txtCode;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtDirAreaSupervisor;
	
	private JLabel lblCode;
	private JLabel lblNombre;
	private JLabel lblCedula;
	private JLabel lblAlergias;
	private JPanel panelDetalles;
	private JLabel lblDirAreaSupervisor;
	private JPanel PanelAlergia;
	private JScrollPane scrollPaneAlergia;
	private DefaultListModel<String> modelAlergia = new DefaultListModel<String>();
	private JLabel lblBuscar;
	private JPanel panelBuscar;
	private JLabel lblBuscarPorCodigo;
	private JTextField txtBuscarCodigo;
	private JLabel lblBuscarPorAreaSupervisor;
	private JTable table_1;
	

	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarCuenta(final String type, final boolean adminCheck, final String codigoCuentaActual) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		 String[] columnNames = setColumns(type);
		
		setTitle("Listado de " + type + "s");
		setBounds(100, 100, 1001, 629);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de " + type + "s:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 953, 535);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 583, 496);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRow()>=0){
					selected = Hospital.getInstance().buscarUsuarioByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
					updateDetalles();
					System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());

				}
			}
		});
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		loadSportMans(type);
		scrollPane.setViewportView(table);
		
		JLabel lblTipoDePublicacin = new JLabel("Tipo de Cuenta:");
		lblTipoDePublicacin.setBounds(605, 29, 116, 14);
		panel.add(lblTipoDePublicacin);
		
		cbxQuesoType = new JComboBox();
		cbxQuesoType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (cbxQuesoType.getSelectedIndex())
				{
				case 0:
					loadSportMans("cuenta");
					break;
				case 1:
					loadSportMans("admin");
					break;
				case 2:
					loadSportMans("paciente");
					break;
				case 3:
					loadSportMans("doctor");
					break;
				case 4:
					loadSportMans("secretaria");
					break;
					
				}
				/*int selection = cbxQuesoType.getSelectedIndex();
				loadSportMans(selection);
				*/
			}
		});
		
		cbxQuesoType.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Administradores", "Pacientes", "Doctores", "Secretarias"}));
		cbxQuesoType.setBounds(722, 26, 157, 20);
		panel.add(cbxQuesoType);
		
		
		
		lblCode = new JLabel("C\u00F3digo:");
		lblNombre = new JLabel("Nombre");
		lblCedula = new JLabel("C\u00E9dula");
		
		lblAlergias = new JLabel("Alergias");
		panelDetalles = new JPanel();

		
		
		
		panelDetalles.setBounds(602, 294, 335, 228);
		panel.add(panelDetalles);
		panelDetalles.setLayout(null);
		
		lblDirAreaSupervisor = new JLabel("New label");
		lblCode.setBounds(12, 13, 56, 16);
		lblNombre.setBounds(12, 42, 56, 16);
		lblCedula.setBounds(12, 71, 56, 16);
		lblDirAreaSupervisor.setBounds(12, 100, 92, 16);
		panelDetalles.add(lblCode);
		

		panelDetalles.add(lblNombre);
		
		panelDetalles.add(lblCedula);
		
		panelDetalles.add(lblDirAreaSupervisor);
		
		lblAlergias.setBounds(12, 129, 56, 16);
		panelDetalles.add(lblAlergias);
		
		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setBounds(116, 13, 207, 22);
		panelDetalles.add(txtCode);
		txtCode.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(116, 42, 207, 22);
		panelDetalles.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setBounds(116, 74, 207, 22);
		panelDetalles.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtDirAreaSupervisor = new JTextField();
		txtDirAreaSupervisor.setEditable(false);
		txtDirAreaSupervisor.setBounds(116, 103, 207, 22);
		panelDetalles.add(txtDirAreaSupervisor);
		txtDirAreaSupervisor.setColumns(10);
		
		PanelAlergia = new JPanel();
		PanelAlergia.setBounds(116, 138, 207, 64);
		panelDetalles.add(PanelAlergia);
		PanelAlergia.setLayout(null);
		
		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(0, 0, 207, 64);
		PanelAlergia.add(scrollPaneAlergia);
		
		JList listAlergia = new JList();
		listAlergia.setModel(modelAlergia);
		scrollPaneAlergia.setRowHeaderView(listAlergia);
		
		updateDetalles();
		
		JLabel lblDetalles = new JLabel("Detalles:");
		lblDetalles.setBounds(602, 267, 335, 16);
		panel.add(lblDetalles);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setBounds(605, 0, 116, 16);
		panel.add(lblBuscar);
		
		panelBuscar = new JPanel();
		panelBuscar.setBounds(615, 56, 325, 182);
		panel.add(panelBuscar);
		panelBuscar.setLayout(null);
		
		lblBuscarPorCodigo = new JLabel("C\u00F3digo");
		lblBuscarPorCodigo.setBounds(12, 13, 56, 16);
		panelBuscar.add(lblBuscarPorCodigo);
		
		txtBuscarCodigo = new JTextField();
		txtBuscarCodigo.setBounds(80, 10, 233, 22);
		panelBuscar.add(txtBuscarCodigo);
		txtBuscarCodigo.setColumns(10);
		
		lblBuscarPorAreaSupervisor = new JLabel("New label");
		lblBuscarPorAreaSupervisor.setBounds(12, 59, 56, 16);
		panelBuscar.add(lblBuscarPorAreaSupervisor);
		
		JScrollPane scrollPaneBuscarAreaSupervisor = new JScrollPane();
		scrollPaneBuscarAreaSupervisor.setBounds(80, 59, 233, 110);
		panelBuscar.add(scrollPaneBuscarAreaSupervisor);
		
		table_1 = new JTable();
		scrollPaneBuscarAreaSupervisor.setViewportView(table_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						RegistrarUsuario modUser = new RegistrarUsuario(type, Hospital.getInstance().getMisCuentas().get(table.getSelectedRow()), false);
						modUser.setModal(true);
						modUser.setLocationRelativeTo(null);
						modUser.setVisible(true);	
					
				
					loadSportMans(type);
					
				}
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
	
					  int delete = JOptionPane.showConfirmDialog(null, "Realmente desea Eliminar esta cuenta?", null, JOptionPane.YES_NO_OPTION);
						    if (delete == JOptionPane.YES_OPTION)
						    {						    	
								Usuario aux = Hospital.getInstance().buscarUsuarioByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
								if (aux.getCodigo() != codigoCuentaActual)
								{
									Hospital.getInstance().getMisCuentas().remove(aux);

									loadSportMans(type);
									
								}
								else
								{
									JOptionPane.showMessageDialog(null, "No puede borrar su propia cuenta", "Alerta", JOptionPane.INFORMATION_MESSAGE);
								}
								
						    }
						
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnRegistrar = new JButton("Nuevo");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarUsuario regisUser = new RegistrarUsuario("cuenta", null, adminCheck);
						regisUser.setVisible(true);
						regisUser.setModal(true);
						
						loadSportMans(type);
						
					}
				});
				buttonPane.add(btnRegistrar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadSportMans(type);
		

	}



	private void loadSportMans(String selection) {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];

		if (selection.equalsIgnoreCase("admin")) {
			for (Usuario aux : Hospital.getInstance().getMisCuentas()) 
			{
				if (aux instanceof Admin)
				{
					fila[0] = aux.getCodigo();
					fila[1] = aux.getNombre();
					fila[2] = aux.getCedula();
					fila[3] = aux.getTelefono();
					fila[4] = "Admin";
		
					tableModel.addRow(fila);
				}
			}
		}
		else if (selection.equalsIgnoreCase("cuenta")) {
			for (Usuario aux : Hospital.getInstance().getMisCuentas()) 
			{
				fila[0] = aux.getCodigo();
				fila[1] = aux.getNombre();
				fila[2] = aux.getCedula();
				fila[3] = aux.getTelefono();
				
				if (aux instanceof Paciente) 
				{
					fila[4] = "Paciente";
				}
				else if (aux instanceof Doctor) 
				{
					fila[4] = "Doctor";
				}
				else if (aux instanceof Secretaria) 
				{
					fila[4] = "Secretaria";
				}
				else if (aux instanceof Admin)
				{
					fila[4] = "Admin";
				}
				
				tableModel.addRow(fila);
			}
		}
		else if (selection.equalsIgnoreCase("paciente")) {
			for (Usuario aux : Hospital.getInstance().getMisCuentas()) {
				if(aux instanceof Paciente){
					fila[0] = aux.getCodigo();
					fila[1] = aux.getNombre();
					fila[2] = aux.getCedula();
					fila[3] = aux.getTelefono();
					fila[4] = "Paciente";
		
					tableModel.addRow(fila);
				}
			}
		}
			
		if (selection.equalsIgnoreCase("doctor")) {
			for (Usuario aux : Hospital.getInstance().getMisCuentas()) {
				if(aux instanceof Doctor){
					fila[0] = aux.getCodigo();
					fila[1] = aux.getNombre();
					fila[2] = aux.getCedula();
					fila[3] = aux.getTelefono();
					fila[4] = "Doctor";
		
					tableModel.addRow(fila);
				}
			}
		}	
		if (selection.equalsIgnoreCase("secretaria")) {
			for (Usuario aux : Hospital.getInstance().getMisCuentas()) {
				if(aux instanceof Secretaria){
					fila[0] = aux.getCodigo();
					fila[1] = aux.getNombre();
					fila[2] = aux.getCedula();
					fila[3] = aux.getTelefono();
					fila[4] = "Secretaria";
		
					tableModel.addRow(fila);
				}
			}
		}
		
		
		
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(130);
		columnModel.getColumn(2).setPreferredWidth(130);
		columnModel.getColumn(3).setPreferredWidth(130);
		
		
		
	}
	
	private void updateDetalles()
	{
		if (selected != null)
		{
			lblCode.setVisible(true);
			lblNombre.setVisible(true);
			lblCedula.setVisible(true);
			txtCedula.setVisible(true);
			lblDirAreaSupervisor.setVisible(true);
			txtDirAreaSupervisor.setVisible(true);
			lblAlergias.setVisible(true);
			
			txtCode.setText(selected.getCodigo());
			txtNombre.setText(selected.getNombre());
			txtCedula.setText(selected.getCedula());
			if (selected instanceof Paciente)
			{
				lblDirAreaSupervisor.setText("Dirección:");
				txtDirAreaSupervisor.setText(((Paciente) selected).getDireccion());
				reloadAlergiaCuadro();
				lblAlergias.setVisible(true);
				PanelAlergia.setVisible(true);
				
			}
			else 
			{
				lblAlergias.setVisible(false);
				PanelAlergia.setVisible(false);
			}
			if (selected instanceof Doctor)
			{
				lblDirAreaSupervisor.setText("Área Médica:");
				txtDirAreaSupervisor.setText(((Doctor) selected).getAreaMedica());
			}
			else if (selected instanceof Secretaria)
			{
				lblDirAreaSupervisor.setText("Supervisor:");
				txtDirAreaSupervisor.setText(((Secretaria) selected).getDependiente().getNombre());
			}
			else if (selected instanceof Admin)
			{
				txtDirAreaSupervisor.setVisible(false);;
				lblDirAreaSupervisor.setVisible(false);
			}
			
		}
		else
		{
			lblCode.setVisible(false);
			lblNombre.setVisible(false);
			txtCode.setText("Seleccione un usuario");
			txtNombre.setText("Para ver Detalles.");
			
			lblCedula.setVisible(false);
			txtCedula.setVisible(false);
			lblDirAreaSupervisor.setVisible(false);
			txtDirAreaSupervisor.setVisible(false);
			lblAlergias.setVisible(false);
			PanelAlergia.setVisible(false);
			
			
			
			
		}
	}
	
	private void reloadAlergiaCuadro()
	{
		modelAlergia.removeAllElements();
		for (String auxString : ((Paciente)selected).getMiRegistro().getMisAlergias())
		{
			System.out.println(auxString);
			modelAlergia.addElement(auxString);
		}
	}
	
	private String[] setColumns(String type)
	{
		if (type == "admin")
		{
			String[] columnNames = {"Usuario"};
			return columnNames;
		}
		else 
		{
			String[] columnNames = {"Código", "Nombre", "Cédula","Teléfono", "Tipo de Cuenta"};
			return columnNames;
		}
	}
}
