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
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ListarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  JTable table;
	private  Object[] fila;
	private  DefaultTableModel tableModel;
	private  JButton btnEliminar;
	private  JButton btnModificar;
	private int code;
	//private String[] columnNames;
	String mode = "<Todos>";
	private Enfermedad selected = null;
	private JButton btnRegistrar;
	private JPanel panelBuscar;
	private JLabel lblBuscar;
	private JLabel lblBuscarCodigo;
	private JPanel panelDetalles;
	private JLabel lblDetalles;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblCura;
	private JLabel lblSntomas_1;
	private JTextField txtBuscarCodigo;
	private JTextField txtDetallesCodigo;
	private JTextField txtDetallesNombre;
	private JCheckBox chckbxDetallesCura;
	
	private JPanel PanelAlergia;
	private JScrollPane scrollPaneAlergia;
	private DefaultListModel<String> modelAlergia = new DefaultListModel<String>();


	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarEnfermedad(final boolean adminCheck) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		 String[] columnNames = setColumns();
		
		setTitle("Listado de enfermedades");
		setBounds(100, 100, 895, 584);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 27, 564, 474);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 531, 427);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRow()>=0){
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					selected = Hospital.getInstance().buscarEnfermedadesByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
					updateDetalles();
					//mode = cbxQuesoType.getSelectedItem().toString();
					System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());

				}
			}
		});
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		loadSportMans();
		scrollPane.setViewportView(table);
		
		panelBuscar = new JPanel();
		panelBuscar.setBounds(586, 27, 279, 107);
		contentPanel.add(panelBuscar);
		panelBuscar.setLayout(null);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setBounds(12, 13, 84, 16);
		panelBuscar.add(lblBuscar);
		
		lblBuscarCodigo = new JLabel("C\u00F3digo");
		lblBuscarCodigo.setBounds(12, 55, 56, 16);
		panelBuscar.add(lblBuscarCodigo);
		
		txtBuscarCodigo = new JTextField();
		txtBuscarCodigo.setBounds(64, 52, 203, 22);
		panelBuscar.add(txtBuscarCodigo);
		txtBuscarCodigo.setColumns(10);
		
		panelDetalles = new JPanel();
		panelDetalles.setBounds(586, 163, 279, 338);
		contentPanel.add(panelDetalles);
		panelDetalles.setLayout(null);
		
		lblDetalles = new JLabel("Detalles");
		lblDetalles.setBounds(20, 13, 56, 16);
		panelDetalles.add(lblDetalles);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(20, 41, 56, 16);
		panelDetalles.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 72, 56, 16);
		panelDetalles.add(lblNombre);
		
		lblCura = new JLabel("Tiene Cura");
		lblCura.setBounds(20, 101, 87, 16);
		panelDetalles.add(lblCura);
		
		lblSntomas_1 = new JLabel("S\u00EDntomas");
		lblSntomas_1.setBounds(20, 152, 56, 16);
		panelDetalles.add(lblSntomas_1);
		
		txtDetallesCodigo = new JTextField();
		txtDetallesCodigo.setEditable(false);
		txtDetallesCodigo.setColumns(10);
		txtDetallesCodigo.setBounds(82, 39, 185, 22);
		panelDetalles.add(txtDetallesCodigo);
		
		txtDetallesNombre = new JTextField();
		txtDetallesNombre.setEditable(false);
		txtDetallesNombre.setColumns(10);
		txtDetallesNombre.setBounds(82, 67, 185, 22);
		panelDetalles.add(txtDetallesNombre);
		
		PanelAlergia = new JPanel();
		PanelAlergia.setBounds(20, 200, 247, 125);
		panelDetalles.add(PanelAlergia);
		PanelAlergia.setLayout(null);

		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(0, 0, 247, 125);
		PanelAlergia.add(scrollPaneAlergia);
		
		JList listAlergia = new JList();
		listAlergia.setModel(modelAlergia);
		scrollPaneAlergia.setRowHeaderView(listAlergia);
				
		chckbxDetallesCura = new JCheckBox("");
		chckbxDetallesCura.setEnabled(false);
		chckbxDetallesCura.setBounds(92, 97, 113, 25);
		panelDetalles.add(chckbxDetallesCura);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						RegistrarEnfermedad modEnfermedad;
						try {
							modEnfermedad = new RegistrarEnfermedad(Hospital.getInstance().getEnfermedadesReg().get(table.getSelectedRow()));
							modEnfermedad.setModal(true);
							modEnfermedad.setLocationRelativeTo(null);
							modEnfermedad.setVisible(true);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					selected = null;
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
					loadSportMans();
				}
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
	
					  int delete = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar esta Enfermedad?", null, JOptionPane.YES_NO_OPTION);
						    if (delete == JOptionPane.YES_OPTION)
						    {						    	
								Enfermedad aux = Hospital.getInstance().buscarEnfermedadesByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
								
									Hospital.getInstance().getEnfermedadesReg().remove(aux);

									loadSportMans();
								
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
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnRegistrar = new JButton("Nuevo");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						RegistrarEnfermedad regisUser;
						try {
							regisUser = new RegistrarEnfermedad(null);
							regisUser.setVisible(true);
							regisUser.setModal(true);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						loadSportMans();
					}
				});
				buttonPane.add(btnRegistrar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadSportMans();

	}



	private void loadSportMans() {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];

		for (Enfermedad aux : Hospital.getInstance().getEnfermedadesReg())
		{
			fila[0] = aux.getCodigo();
			fila[1] = aux.getNombre();
			
			if (aux.isCuraEncontrada())
			{
				fila[2] = "Sí";
			}
			else
			{
				fila[2] = "No";
			}
			try {
				
				if (aux.getSintomas().size() > 1)
				{
					fila[3] = aux.getSintomas().get(0) + "...";
				}
				else 
				{
					fila[3] = aux.getSintomas().get(0);
				}
				
			} catch (NullPointerException e) {
				fila[3] = "Error";
			}
			catch (IndexOutOfBoundsException e) {
				fila[3] = "Error";
			}
			
			tableModel.addRow(fila);
			
		}
		
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(130);
		/*if(tableModel.getRowCount()==0){
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
		}*/
	}
	private void reloadAlergiaCuadro()
	{
		modelAlergia.removeAllElements();
		for (String auxString : selected.getSintomas())
		{
			System.out.println(auxString);
			modelAlergia.addElement(auxString);
		}
	}
	
	private String[] setColumns()
	{
		
			String[] columnNames = {"Código", "Nombre", "Tiene Cura ","Síntoma/s"};
			return columnNames;
		
	}
	
	private void updateDetalles()
	{
		if (selected != null)
		{
			txtDetallesCodigo.setText(selected.getCodigo());
			txtDetallesNombre.setText(selected.getNombre());
			chckbxDetallesCura.setSelected(selected.isCuraEncontrada());
			reloadAlergiaCuadro();
			
		}
	}
}
