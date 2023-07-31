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

public class ListarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  JTable table;
	private  Object[] fila;
	private  DefaultTableModel tableModel;
	private  JButton btnEliminar;
	private  JButton btnModificar;
	private JButton btnDetalles;
	private int code;
	//private String[] columnNames;
	String mode = "<Todos>";
	Usuario selected = null;
	private JButton btnRegistrar;
	


	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarVacuna(final boolean adminCheck) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		 String[] columnNames = setColumns();
		
		setTitle("Listado de Vacunas");
		setBounds(100, 100, 650, 376);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 27, 624, 277);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 604, 248);
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
					System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());

				}
			}
		});
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		loadSportMans();
		scrollPane.setViewportView(table);
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
			
			btnDetalles = new JButton("Detalles");
			btnDetalles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnDetalles.setEnabled(false);
			buttonPane.add(btnDetalles);
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
	
	private String[] setColumns()
	{
		
			String[] columnNames = {"Código", "Posibles Alergias", "Enfermedades Prevenidas"};
			return columnNames;
		
	}
}
