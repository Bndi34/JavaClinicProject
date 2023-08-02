package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Enfermedad;
import logico.Hospital;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class RegistrarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtNombre;
	private JList listSeleccionada = new JList();
	private JComboBox cbxSintomas = new JComboBox();
	private DefaultListModel<String> modelElegido;
	private ArrayList<String>SintomasElegido = new ArrayList<>();
	private ArrayList<String>SintomasSinElegir = new ArrayList<>();
	private boolean cargado = false;
	private JButton okButton;
	private JCheckBox chbxCura;
	private JPanel panel_sintomas;
	
	private String selectedSintoma;
	private JButton btnAgregarSintoma;
	private JTextField txtAlergia;
	
	private Enfermedad auxEnfermedad;
	
	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public RegistrarEnfermedad(Enfermedad entrada) throws ClassNotFoundException, IOException {
		
		auxEnfermedad = entrada;
		
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		SintomasSinElegir = new ArrayList<String>();
		SintomasElegido = new ArrayList<String>();
		modelElegido = new DefaultListModel<String>();
		
		
		txtAlergia = new JTextField();
		txtAlergia.setEnabled(false);
		txtAlergia.setText("VA-1");
		txtAlergia.setEditable(false);
		txtAlergia.setVisible(false);
		txtAlergia.setColumns(10);
		txtAlergia.setBounds(83, 75, 247, 22);
		contentPanel.add(txtAlergia);
		
		setTitle("Registar Enfermedad");
		setBounds(100, 100, 447, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(10, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(10, 45, 56, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblCura = new JLabel("Tiene Cura");
			lblCura.setBounds(10, 280, 75, 16);
			contentPanel.add(lblCura);
		}
		{
			JLabel lblSintomas = new JLabel("S\u00EDntomas");
			lblSintomas.setBounds(15, 78, 56, 16);
			contentPanel.add(lblSintomas);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(78, 10, 339, 22);
			contentPanel.add(txtCode);
			txtCode.setText("ENF-"+String.valueOf(Hospital.getInstance().generadorEnfermedad));
			txtCode.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(78, 42, 339, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			cbxSintomas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					selectedSintoma = cbxSintomas.getSelectedItem().toString();
						if ( selectedSintoma.equalsIgnoreCase("Agregar...") ) {
							btnAgregarSintoma.setText("Añadir");
							
							txtAlergia.setVisible(true);
							txtAlergia.setEditable(true);
							txtAlergia.setEnabled(true);
							txtAlergia.setText("");
							
							cbxSintomas.setVisible(false);
							//cbxAlergia.setEditable(true);
							//cbxAlergia.setSelectedItem("");
							
						}
						else if (!selectedSintoma.equalsIgnoreCase("<Seleccione>"))
						{
							SintomasElegido.add(selectedSintoma);
							SintomasSinElegir.remove(selectedSintoma);
							
							System.out.println("Reload Cuadro Combobox");
							reloadAlergia();
							reloadAlergiaCuadro();
						}
						else
						{
							btnAgregarSintoma.setText("Borrar");
							
							txtAlergia.setVisible(false);
							txtAlergia.setEditable(false);
							txtAlergia.setEnabled(false);
							txtAlergia.setText("");
							
							cbxSintomas.setVisible(true);	
						}
					
				}
			});
			cbxSintomas.setBounds(83, 75, 247, 22);
			contentPanel.add(cbxSintomas);
		}
		{
			chbxCura = new JCheckBox("");
			chbxCura.setBounds(78, 283, 43, 16);
			contentPanel.add(chbxCura);
		}
		{
			panel_sintomas = new JPanel();
			panel_sintomas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_sintomas.setBounds(10, 110, 407, 157);
			contentPanel.add(panel_sintomas);
			panel_sintomas.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel_sintomas.add(scrollPane, BorderLayout.CENTER);
				{
					listSeleccionada.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							
							try 
							{
								if (!txtAlergia.isEditable())
								{
									int selected = listSeleccionada.getSelectedIndex();
									selectedSintoma = listSeleccionada.getSelectedValue().toString();
									if(selected>=0)
									{
										btnAgregarSintoma.setText("Borrar");
										txtAlergia.setText(listSeleccionada.getSelectedValue().toString());
									}
								}
								
								
							} catch (Exception e2) {
								// TODO: handle exception
							}
							
							
						}
					});
					scrollPane.setViewportView(listSeleccionada);
					System.out.println("Reload cuadro 1");
					reloadAlergiaCuadro();
					listSeleccionada.setModel(modelElegido);
					
				}
			}
		}
		{
		btnAgregarSintoma = new JButton("A\u00F1adir");
		btnAgregarSintoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectedSintoma = cbxSintomas.getSelectedItem().toString();
				
				if ( btnAgregarSintoma.getText().equalsIgnoreCase("Borrar")) {
					try {
						if (!txtAlergia.getText().isEmpty())
						{
							SintomasSinElegir.add(txtAlergia.getText());
							SintomasElegido.remove(txtAlergia.getText());
							txtAlergia.setText("");
							
							reloadAlergia();
							System.out.println("Reload cuadro Borrar");
							reloadAlergiaCuadro();
						}
						
						
					} catch (IndexOutOfBoundsException e2) {
						btnAgregarSintoma.setText("Añadir");
					}
					
				}
				else if ( txtAlergia.isEditable() ) {
					
					SintomasElegido.add(txtAlergia.getText());
					
					reloadAlergia();
					reloadAlergiaCuadro();
					txtAlergia.setVisible(false);
					cbxSintomas.setVisible(true);
					btnAgregarSintoma.setText("Borrar");
				}
				
				
			}
		});
		btnAgregarSintoma.setBounds(342, 74, 75, 25);
		contentPanel.add(btnAgregarSintoma);
		
		}
		
		
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Enfermedad aux = new Enfermedad(txtCode.getText(), txtNombre.getText(), SintomasElegido,chbxCura.isSelected());
						Hospital.getInstance().insertarEnfermedad(aux);
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
		//Debería existir un sistema de si estas cargando una enfermedad no funcione loadSintomas, 
		//o que solo cargue los sintomas que no esten seleccionados
		loadEntrada();
		LoadSintomas();
		loadEnfermedad();
		reloadElegido();
		reloadAlergia();
		reloadAlergiaCuadro();

	
	}
			
	void loadEnfermedad() {
		
	}
	
	
	void LoadSintomas() throws IOException, ClassNotFoundException{
		if ( cargado ) {
			
			File file = new File("misSintomas.dat" );

			if( file.exists()) {		
				FileInputStream f = new FileInputStream(file);
				ObjectInputStream oos = new ObjectInputStream(f);
				
				int size = oos.readInt();
				
				for (int i = 0; i < size; i++) {
					String a = (String)oos.readObject();
					SintomasSinElegir.add(a);
				}
				oos.close();
				reloadCbxSintomas();
			}  
		}
		else {
			reloadCbxSintomas();
		}
    	
	}
	
	void reloadCbxSintomas() {
		
		cbxSintomas.removeAll();
		
		cbxSintomas.addItem("<Seleccione>");
		for (String aux : SintomasSinElegir ) 
		{
			cbxSintomas.addItem(aux);
		}
		cbxSintomas.addItem("Agregar...");
		
	}
	
	
	private void reloadElegido() {
		modelElegido.removeAllElements();
		String aux = "";
		for (int i = 0; i < SintomasElegido.size(); i++) {
			aux = SintomasElegido.get(i);
			modelElegido.addElement(aux);
		}
		
	}
	
	void clean() {
		txtCode.setText("ENF-"+ String.valueOf(Hospital.getInstance().generadorEnfermedad));
		txtNombre.setText("");
		modelElegido.removeAllElements();
	}





	private void reloadAlergia()
	{
		cbxSintomas.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
	
		for (String temp : SintomasSinElegir )
		{
			cbxSintomas.addItem(temp);
		}
	
		cbxSintomas.addItem("Agregar...");
	
	}
	private void reloadAlergiaCuadro()
	{
		modelElegido.removeAllElements();
		for (String auxString : SintomasElegido)
		{
			modelElegido.addElement(auxString);
		}
	}
	
	private void loadEntrada()
	{
		try {
			txtCode.setText(auxEnfermedad.getCodigo());
			SintomasElegido = auxEnfermedad.getSintomas();
			txtNombre.setText(auxEnfermedad.getNombre());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
		
	}
}