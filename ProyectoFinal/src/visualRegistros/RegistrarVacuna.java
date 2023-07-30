package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Enfermedad;
import logico.Hospital;
import logico.Usuario;
import logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class RegistrarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode; 
	private JComboBox cbxEnfermedad = new JComboBox();
	private JComboBox cbxAlergia = new JComboBox();
	private JButton btnEnfermedad = new JButton("A\u00F1adir");
	private JButton btnAlergia = new JButton("A\u00F1adir");
	private DefaultListModel<String> modelEnfermedad;
	private DefaultListModel<String> modelAlergia;
	private JList listAler = new JList();
	private JList listEnf = new JList();
	
	private int selected = -1;
	private ArrayList<Enfermedad>EnfermedadesElegidas;
	private ArrayList<Enfermedad>EnfermedadeSinElegir;
	private ArrayList<String>AlergiasElegidas;
	private ArrayList<String>AlergiasSinElegir;
	
	
	//private ArrayList<Enfermedad>EnfermedadesPrevenidas;
	//private ArrayList<String>Alergias;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarVacuna dialog = new RegistrarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarVacuna() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modelEnfermedad = new DefaultListModel<String>();
		modelAlergia = new DefaultListModel<String>();
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 525, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(22, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblEnfermedad = new JLabel("Enfermedad Prevenida:");
			lblEnfermedad.setBounds(22, 49, 129, 16);
			contentPanel.add(lblEnfermedad);
		}
		{
			JLabel lblAlergia = new JLabel("Posibles Alergias:");
			lblAlergia.setBounds(273, 49, 129, 16);
			contentPanel.add(lblAlergia);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(71, 10, 116, 22);
			txtCode.setText("VA-"+String.valueOf(Hospital.getInstance().generadorVacuna));
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			cbxEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedEnfermedad = cbxEnfermedad.getSelectedItem().toString();
					if ( cbxEnfermedad.isEditable() ) {
						cbxEnfermedad.addItem(cbxEnfermedad.getSelectedItem().toString());
						cbxEnfermedad.removeItemAt(cbxEnfermedad.getItemCount() -2 );
						cbxEnfermedad.addItem("Agregar...");
						cbxEnfermedad.setEditable(false);
				
					}
					if ( selectedEnfermedad.equalsIgnoreCase("Agregar...") ) {
						btnEnfermedad.setText("Añadir");
						
					}
					else {
						if (!selectedEnfermedad.equalsIgnoreCase("<Seleccione>")){
							String cod = cbxEnfermedad.getSelectedItem().toString();
							modelEnfermedad.addElement(cod);
							//AlergiasSinElegir.remove( cbxEnfermedad.getSelectedIndex()  );
							EnfermedadesElegidas.add(EnfermedadeSinElegir.get(selected - 1));
							System.out.println(EnfermedadeSinElegir.get(selected-1).getCodigo() );
							cbxEnfermedad.setSelectedIndex(0);
						}
					}
				}
			});
			cbxEnfermedad.setBounds(22, 76, 116, 22);
			contentPanel.add(cbxEnfermedad);
			if (cbxEnfermedad.getItemCount() == 0) {
				cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				loadEnfermedadinCbx();
				cbxEnfermedad.addItem("Agregar...");
			}
		}
		
		
		
		{
			cbxAlergia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedAlergia = cbxAlergia.getSelectedItem().toString();
					if ( cbxAlergia.isEditable() ) {
						cbxAlergia.addItem(cbxAlergia.getSelectedItem().toString());
						cbxAlergia.removeItemAt(cbxAlergia.getItemCount() -2 );
						cbxAlergia.addItem("Agregar...");
						cbxAlergia.setEditable(false);
				
					}
					if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
						btnAlergia.setText("Añadir");
						cbxAlergia.setEditable(true);
						cbxAlergia.setSelectedItem("");
						
					}
					else {
						if (!selectedAlergia.equalsIgnoreCase("<Seleccione>")){
							String cod = cbxAlergia.getSelectedItem().toString();
							modelAlergia.addElement(cod);
							AlergiasSinElegir.remove( cbxAlergia.getSelectedIndex()  );
							AlergiasElegidas.add(AlergiasSinElegir.get(selected - 1));
							cbxAlergia.setSelectedIndex(0);
						} 
					}
				}
			});
			cbxAlergia.setBounds(267, 76, 116, 22);
			contentPanel.add(cbxAlergia);
			if (cbxAlergia.getItemCount() == 0) {
				cbxAlergia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				loadAlergiainCbx();
				cbxAlergia.addItem("Agregar...");
			}
			
		}
		btnEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedAlergia = cbxEnfermedad.getSelectedItem().toString();
				
				if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
					RegistrarEnfermedad regEnf;
					try {
						regEnf = new RegistrarEnfermedad(null);
						regEnf.setModal(true);
						regEnf.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					cbxEnfermedad.addItem(Hospital.getInstance().getEnfermedadesReg().get(Hospital.generadorEnfermedad - 2 ).getCodigo() );
					cbxEnfermedad.setEditable(true);

					//cbxEnfermedad.removeItemAt(cbxEnfermedad.getItemCount() - 2);
					cbxEnfermedad.addItem("Agregar...");
					btnEnfermedad.setText("Borrar");
					
				}
				if ( btnEnfermedad.getText().equalsIgnoreCase("Borrar")) {
					System.out.print(selected);
					EnfermedadesElegidas.remove(selected);
					modelEnfermedad.remove(selected);
				}
				
				
			}
		});
		
		btnEnfermedad.setBounds(143, 76, 89, 23);
		contentPanel.add(btnEnfermedad);
		
		btnAlergia.setBounds(393, 76, 89, 23);
		contentPanel.add(btnAlergia);
		
		btnAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedAlergia = cbxAlergia.getSelectedItem().toString();
				if ( cbxAlergia.isEditable() ) {
					
					cbxAlergia.addItem(cbxAlergia.getSelectedItem().toString());
					cbxAlergia.removeItemAt(cbxAlergia.getItemCount() - 2);
					cbxAlergia.addItem("Agregar...");
					btnAlergia.setText("Borrar");
					cbxAlergia.setEditable(false);
			
				}
				
				if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
					btnAlergia.setText("Agregar");
					cbxAlergia.setEditable(true);
					cbxAlergia.setSelectedItem("");
					
				}
				if ( btnAlergia.getText().equalsIgnoreCase("Borrar")) {
					AlergiasElegidas.remove(selected-1);
					modelAlergia.remove(selected);
				}
				
				
			}
		});
		
		
		JPanel panel_enfermedad = new JPanel();
		panel_enfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_enfermedad.setBounds(22, 109, 116, 108);
		contentPanel.add(panel_enfermedad);
		panel_enfermedad.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel_enfermedad.add(scrollPane, BorderLayout.CENTER);
			{
				listEnf.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						selected = listEnf.getSelectedIndex();
						if(selected>=0){
							btnEnfermedad.setText("Borrar");
						}
					}
				});
				scrollPane.setViewportView(listEnf);
				listEnf.setModel(modelEnfermedad);
			}
		}
		
		JPanel panel_alergia = new JPanel();
		panel_alergia.setBounds(267, 109, 116, 108);
		contentPanel.add(panel_alergia);
		panel_alergia.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_alergia.add(scrollPane_1);
			{
				listAler.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						selected = listAler.getSelectedIndex();
						if(selected>=0){
							btnAlergia.setText("Borrar");
						}
					}
				});
				scrollPane_1.setViewportView(listAler);
				listAler.setModel(modelAlergia);
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String codigo = txtCode.getText();
						
						Vacuna aux = new Vacuna(codigo,EnfermedadesElegidas,AlergiasElegidas);
						Hospital.getInstance().insertarVacuna(aux);
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
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		//loadAlergiainCbx();
	}
	
	private void clean() {
		txtCode.setText("VA-"+String.valueOf(Hospital.getInstance().generadorVacuna));
		modelAlergia.removeAllElements();
		modelEnfermedad.removeAllElements();
		cbxEnfermedad.setSelectedIndex(-1);
		cbxAlergia.setSelectedIndex(-1);
		
	}
	
	private void loadEnfermedadinCbx() {
		for (Enfermedad aux : Hospital.getInstance().getEnfermedadesReg())
		{
			String temp = aux.getCodigo()+" : "+aux.getNombre();
			cbxEnfermedad.addItem(temp );
		}
		EnfermedadeSinElegir = Hospital.getInstance().getEnfermedadesReg();
	}
	
	private void loadAlergiainCbx() {
		for (String temp : Hospital.getInstance().getAlergiasRegistradas() )
		{
			cbxEnfermedad.addItem(temp);
		}
		AlergiasSinElegir = Hospital.getInstance().getAlergiasRegistradas();
	}
}
