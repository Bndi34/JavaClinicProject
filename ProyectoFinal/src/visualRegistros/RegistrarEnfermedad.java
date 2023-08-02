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
	
	
	private boolean cargado = false;
	private JButton okButton;
	private JCheckBox chbxCura;
	private JPanel panel_sintomas;
	
	private String selectedSintoma;
	private JButton btnAgregarSintoma;
	private JTextField txtSintoma;
	private JList listSeleccionada = new JList();
	private JComboBox cbxSintomas = new JComboBox();
	private DefaultListModel<String> modelSintomaElegido;
	private ArrayList<String>SintomasElegido = new ArrayList<>();
	private ArrayList<String>SintomasSinElegir = new ArrayList<>();
	
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
		modelSintomaElegido = new DefaultListModel<String>();
		
		
		txtSintoma = new JTextField();
		txtSintoma.setEnabled(false);
		txtSintoma.setEditable(false);
		txtSintoma.setVisible(false);
		txtSintoma.setColumns(10);
		txtSintoma.setBounds(83, 75, 247, 22);
		contentPanel.add(txtSintoma);
		
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
							
							txtSintoma.setVisible(true);
							txtSintoma.setEditable(true);
							txtSintoma.setEnabled(true);
							txtSintoma.setText("");
							
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
							
							txtSintoma.setVisible(false);
							txtSintoma.setEditable(false);
							txtSintoma.setEnabled(false);
							txtSintoma.setText("");
							
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
								if (!txtSintoma.isEditable())
								{
									int selected = listSeleccionada.getSelectedIndex();
									selectedSintoma = listSeleccionada.getSelectedValue().toString();
									if(selected>=0)
									{
										btnAgregarSintoma.setText("Borrar");
										txtSintoma.setText(listSeleccionada.getSelectedValue().toString());
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
					listSeleccionada.setModel(modelSintomaElegido);
					
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
						if (!txtSintoma.getText().isEmpty())
						{
							SintomasSinElegir.add(txtSintoma.getText());
							SintomasElegido.remove(txtSintoma.getText());
							txtSintoma.setText("");
							
							reloadAlergia();
							System.out.println("Reload cuadro Borrar");
							reloadAlergiaCuadro();
						}
						
						
					} catch (IndexOutOfBoundsException e2) {
						btnAgregarSintoma.setText("Añadir");
					}
					
				}
				else if ( txtSintoma.isEditable() ) {
					
					SintomasElegido.add(txtSintoma.getText());
					
					reloadAlergia();
					reloadAlergiaCuadro();
					txtSintoma.setVisible(false);
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

		loadEntrada();
		LoadSintomas();
		reloadElegido();
		reloadAlergia();
		reloadAlergiaCuadro();

	
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
		modelSintomaElegido.removeAllElements();
		String aux = "";
		for (int i = 0; i < SintomasElegido.size(); i++) {
			aux = SintomasElegido.get(i);
			modelSintomaElegido.addElement(aux);
		}
		
	}
	
	void clean() {
		txtCode.setText("ENF-"+ String.valueOf(Hospital.getInstance().generadorEnfermedad));
		txtNombre.setText("");
		modelSintomaElegido.removeAllElements();
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
		modelSintomaElegido.removeAllElements();
		for (String auxString : SintomasElegido)
		{
			modelSintomaElegido.addElement(auxString);
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