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

public class ListarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  JTable table;
	private  Object[] fila;
	private  DefaultTableModel tableModel;
	private  JButton btnEliminar;
	private  JButton btnModificar;
	private int code;
	//private String[] columnNames;
	String mode = "<Todos>";
	private JButton btnRegistrar;
	private JPanel panelBuscar;
	private JPanel panelDetalles;
	private JLabel lblBuscar;
	private JLabel lblBuscarCodigo;
	private JLabel lblDetalles;
	private JLabel lblDetallesCodigo;
	private JLabel lblDetallesEnfermedad;
	private JLabel lblDetallesAlergia;
	private JTextField txtBuscarCodigo;
	private JTextField txtDetallesCodigo;
	
	private Vacuna selected;
	private JPanel PanelAlergia;
	private JScrollPane scrollPaneAlergia;
	private DefaultListModel<String> modelAlergia = new DefaultListModel<String>();
	
	private JPanel panelEnfermdedad;
	private JScrollPane scrollpaneEnfermedad;
	private DefaultListModel<String> modelEnfermedad = new DefaultListModel<String>();
	

	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarVacuna(final boolean adminCheck) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		 String[] columnNames = setColumns();
		
		setTitle("Listado de Vacunas");
		setBounds(100, 100, 983, 685);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 27, 624, 575);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 495, 528);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRow()>=0){
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					selected = Hospital.getInstance().buscarVacunasByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
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
		panelBuscar.setBounds(646, 27, 298, 123);
		contentPanel.add(panelBuscar);
		panelBuscar.setLayout(null);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setBounds(12, 13, 175, 16);
		panelBuscar.add(lblBuscar);
		
		lblBuscarCodigo = new JLabel("C\u00F3digo");
		lblBuscarCodigo.setBounds(12, 42, 56, 16);
		panelBuscar.add(lblBuscarCodigo);
		
		txtBuscarCodigo = new JTextField();
		txtBuscarCodigo.setBounds(71, 42, 188, 22);
		panelBuscar.add(txtBuscarCodigo);
		txtBuscarCodigo.setColumns(10);
		
		panelDetalles = new JPanel();
		panelDetalles.setBounds(646, 163, 298, 439);
		contentPanel.add(panelDetalles);
		panelDetalles.setLayout(null);
		
		lblDetalles = new JLabel("Detalles");
		lblDetalles.setBounds(12, 13, 56, 16);
		panelDetalles.add(lblDetalles);
		
		lblDetallesCodigo = new JLabel("C\u00F3digo");
		lblDetallesCodigo.setBounds(12, 42, 56, 16);
		panelDetalles.add(lblDetallesCodigo);
		
		lblDetallesEnfermedad = new JLabel("Enfermedades Tratadas");
		lblDetallesEnfermedad.setBounds(12, 98, 159, 16);
		panelDetalles.add(lblDetallesEnfermedad);
		
		lblDetallesAlergia = new JLabel("Alergias");
		lblDetallesAlergia.setBounds(12, 272, 56, 16);
		panelDetalles.add(lblDetallesAlergia);
		
		txtDetallesCodigo = new JTextField();
		txtDetallesCodigo.setEditable(false);
		txtDetallesCodigo.setColumns(10);
		txtDetallesCodigo.setBounds(80, 39, 179, 22);
		panelDetalles.add(txtDetallesCodigo);
		
		PanelAlergia = new JPanel();
		PanelAlergia.setBounds(12, 301, 247, 125);
		panelDetalles.add(PanelAlergia);
		PanelAlergia.setLayout(null);

		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(0, 0, 247, 125);
		PanelAlergia.add(scrollPaneAlergia);
		
		JList listAlergia = new JList();
		listAlergia.setModel(modelAlergia);
		scrollPaneAlergia.setRowHeaderView(listAlergia);
		

		panelEnfermdedad = new JPanel();
		panelEnfermdedad.setBounds(12, 127, 247, 125);
		panelDetalles.add(panelEnfermdedad);
		panelEnfermdedad.setLayout(null);

		scrollpaneEnfermedad = new JScrollPane();
		scrollpaneEnfermedad.setBounds(0, 0, 247, 125);
		panelEnfermdedad.add(scrollpaneEnfermedad);
		
		JList listEnfermedad = new JList();
		listEnfermedad.setModel(modelEnfermedad);
		scrollpaneEnfermedad.setRowHeaderView(listEnfermedad);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						RegistrarVacuna modEnfermedad;
						System.out.println("Vacunas array " + Hospital.getInstance().getMisVacunas().size());
						modEnfermedad = new RegistrarVacuna(Hospital.getInstance().getMisVacunas().get(table.getSelectedRow()));
						modEnfermedad.setModal(true);
						modEnfermedad.setLocationRelativeTo(null);
						modEnfermedad.setVisible(true);
					
					loadSportMans();
				}
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
	
					  int delete = JOptionPane.showConfirmDialog(null, "Realmente desea Eliminar esta Vacuna?", null, JOptionPane.YES_NO_OPTION);
						    if (delete == JOptionPane.YES_OPTION)
						    {						    	
								Vacuna aux = Hospital.getInstance().buscarVacunasByCode(table.getValueAt(table.getSelectedRow(), 0).toString());
								
									Hospital.getInstance().getMisVacunas().remove(aux);

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
						
						RegistrarVacuna regisUser;
						regisUser = new RegistrarVacuna(null);
						regisUser.setVisible(true);
						regisUser.setModal(true);
						
						
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

		for (Vacuna aux : Hospital.getInstance().getMisVacunas())
		{
			fila[0] = aux.getCodigo();
			
			try {
				
				if (aux.getPosiblesAlergias().size() > 1)
				{
					fila[1] = aux.getPosiblesAlergias().get(0) + "...";
				}
				else 
				{
					fila[1] = aux.getPosiblesAlergias().get(0);
				}
				
				if (aux.getEnfermedadesPrevenidas().size() > 1)
				{
					fila[2] = aux.getEnfermedadesPrevenidas().get(0).getNombre() + "...";
				}
				else 
				{
					fila[2] = aux.getEnfermedadesPrevenidas().get(0).getNombre();
				}
				
			} catch (NullPointerException e) {
				fila[1] = "Error";
			}
			catch (IndexOutOfBoundsException e) {
				fila[1] = "Error";
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
		columnModel.getColumn(2).setPreferredWidth(250);
		
		/*if(tableModel.getRowCount()==0){
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
		}*/
	}
	private void updateDetalles()
	{
		if (selected != null)
		{
			txtDetallesCodigo.setText(selected.getCodigo());
			reloadAlergiaCuadro();
			reloadEnfermedadCuadro();
		}
	}
	
	private void reloadAlergiaCuadro()
	{
		modelAlergia.removeAllElements();
		for (String auxString : selected.getPosiblesAlergias())
		{
			modelAlergia.addElement(auxString);
		}
	}
	
	private void reloadEnfermedadCuadro()
	{
		modelEnfermedad.removeAllElements();
		for (Enfermedad auxString : selected.getEnfermedadesPrevenidas())
		{
			modelEnfermedad.addElement(auxString.getNombre());
		}
	}
	
	private String[] setColumns()
	{
		
			String[] columnNames = {"Código", "Posibles Alergias", "Enfermedades Prevenidas"};
			return columnNames;
		
	}
}
