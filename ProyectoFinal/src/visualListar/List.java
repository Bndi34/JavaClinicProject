package visualListar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import logica.Fabrica;
import logica.Queso;
import logica.QueEsferico;
import logica.QueCilindrico;
import logica.QueCilHueco;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class List extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel tableModel;
	private static JButton btnEliminar;
	private static JButton btnModificar;
	private int code;
	String mode = "<Todos>";
	Queso selected = null;
	JComboBox cbxQuesoType;



	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public List() {
		setTitle("Listado de Quesos");
		setBounds(100, 100, 650, 376);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de Quesos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 624, 293);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 604, 228);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowSelected = -1;
				rowSelected = table.getSelectedRow();
				selected = Fabrica.getInstance().buscarQuesoByCode(table.getValueAt(rowSelected, 0).toString());
				
				if(table.getSelectedRow()>=0 && selected.getEstado().equalsIgnoreCase("Habilitado")){
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					mode = cbxQuesoType.getSelectedItem().toString();
				}
			}
		});
		tableModel = new DefaultTableModel();
		String[] columnNames = {"Código","Radio", "Precio Base", "Estado","     Tipo    "};
		tableModel.setColumnIdentifiers(columnNames);
		loadSportMans(0);
		scrollPane.setViewportView(table);
		
		JLabel lblTipoDePublicacin = new JLabel("Tipo de Publicaci\u00F3n:");
		lblTipoDePublicacin.setBounds(10, 29, 116, 14);
		panel.add(lblTipoDePublicacin);
		
		cbxQuesoType = new JComboBox();
		cbxQuesoType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = cbxQuesoType.getSelectedIndex();
				loadSportMans(selection);
			}
		});
		cbxQuesoType.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Esf\u00E9rico", "Cil\u00EDndrico", "Ci\u00EDndrico con hueco"}));
		cbxQuesoType.setBounds(127, 26, 157, 20);
		panel.add(cbxQuesoType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selected.getEstado().equalsIgnoreCase("Habilitado")) {						
						RegQueso modQue = new RegQueso("Modificar Queso",0,selected);
						modQue.setModal(true);
						modQue.setLocationRelativeTo(null);
						modQue.setVisible(true);
					}
				}
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*SportMan aux = fed.getSportManByCode(code);
					  int delete = JOptionPane.showConfirmDialog(null, "Realmente desea Eliminar al Deportista: " + aux.getName(), null, JOptionPane.YES_NO_OPTION);
						    if (delete == JOptionPane.YES_OPTION)
						    {
						       
						    	fed.deleteSportMan(code);
								loadSportMans();
						    }
						
						
						*/
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



	public static void loadSportMans(int selection) {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		switch (selection) {
		case 0:
			for (Queso aux : Fabrica.getInstance().getMisQuesos()) {
				fila[0] = aux.getCodigo();
				fila[1] = aux.getRadio();
				fila[2] = aux.getPrecioBase();
				fila[3] = aux.getEstado();
				if(aux instanceof QueEsferico)
					fila[4] = "     Esferico   ";
				if(aux instanceof QueCilindrico)
					fila[4] = "   Cilindrico   ";
				if(aux instanceof QueCilHueco)
					fila[4] = "Cilindrico hueco";
				
				tableModel.addRow(fila);
			}
			break;
		case 1:
			for (Queso aux : Fabrica.getInstance().getMisQuesos()) {
				if(aux instanceof QueEsferico){
				fila[0] = aux.getCodigo();
				fila[1] = aux.getRadio();
				fila[2] = aux.getPrecioBase();
				fila[3] = aux.getEstado();
				fila[4] = "Esferico";
				
				tableModel.addRow(fila);
				}
			}
			break;	
		case 2:
			for (Queso aux : Fabrica.getInstance().getMisQuesos()) {
				if(aux instanceof QueCilindrico){
				fila[0] = aux.getCodigo();
				fila[1] = aux.getRadio();
				fila[2] = aux.getPrecioBase();
				fila[3] = aux.getEstado();
				fila[4] = "Cilindrico";
				
				tableModel.addRow(fila);
				}
			}
			break;	
		case 3:
			for (Queso aux : Fabrica.getInstance().getMisQuesos()) {
				if(aux instanceof QueCilindrico){
				fila[0] = aux.getCodigo();
				fila[1] = aux.getRadio();
				fila[2] = aux.getPrecioBase();
				fila[3] = aux.getEstado();
				fila[4] = "Cilindrico hueco";
				
				tableModel.addRow(fila);
				}
			}
			break;	
		}
		
		
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
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
}
