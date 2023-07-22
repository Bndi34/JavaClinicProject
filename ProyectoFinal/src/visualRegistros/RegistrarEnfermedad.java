package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
	JList listSeleccionada = new JList();
	JComboBox cbxSintomas = new JComboBox();
	private DefaultListModel<String> modelElegido;
	private ArrayList<String>SintomasElegido;
	private ArrayList<String>SintomasSinElegir;
	private boolean cargado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarEnfermedad dialog = new RegistrarEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public RegistrarEnfermedad() throws ClassNotFoundException, IOException {
		SintomasSinElegir = new ArrayList<String>();
		SintomasElegido = new ArrayList<String>();
		modelElegido = new DefaultListModel<String>();
		
		SintomasElegido.add("Sida");
		SintomasElegido.add("VIH");
		SintomasElegido.add("Gripe");

		setTitle("Registar Enfermedad");
		setBounds(100, 100, 381, 251);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(10, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(10, 53, 56, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblCura = new JLabel("Tiene Cura");
			lblCura.setBounds(12, 132, 75, 16);
			contentPanel.add(lblCura);
		}
		{
			JLabel lblSintomas = new JLabel("S\u00EDntomas");
			lblSintomas.setBounds(175, 13, 56, 16);
			contentPanel.add(lblSintomas);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(51, 10, 116, 22);
			contentPanel.add(txtCode);
			txtCode.setText("ENF-"+String.valueOf(Hospital.getInstance().generadorEnfermedad));
			txtCode.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(51, 50, 116, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			cbxSintomas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedAlergia = cbxSintomas.getSelectedItem().toString();
					if ( !selectedAlergia.equalsIgnoreCase("Agregar...") && cbxSintomas.getSelectedIndex() != 0 ) {
						String selected = cbxSintomas.getSelectedItem().toString();
						SintomasElegido.add(selected);
						modelElegido.addElement(selected);
						SintomasElegido.remove( cbxSintomas.getSelectedIndex() );
						//cbxSintomas.getSelectedIndex()
						
					}
					if ( cbxSintomas.isEditable() ) {
						String nuevo = cbxSintomas.getSelectedItem().toString();
						SintomasElegido.add(nuevo);
						modelElegido.addElement(nuevo);
						AgregarAlergiaCbxSintomas();
						cbxSintomas.setEditable(false);
					}
					if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
						cbxSintomas.setEditable(true);
					}
					
				}
			});
			cbxSintomas.setBounds(232, 10, 123, 22);
			contentPanel.add(cbxSintomas);
		}
		{
			JCheckBox chboxCura = new JCheckBox("");
			chboxCura.setBounds(80, 132, 43, 16);
			contentPanel.add(chboxCura);
		}
		{
			JPanel panel_sintomas = new JPanel();
			panel_sintomas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_sintomas.setBounds(175, 53, 180, 95);
			contentPanel.add(panel_sintomas);
			panel_sintomas.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel_sintomas.add(scrollPane, BorderLayout.CENTER);
				{
					listSeleccionada.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int selected = listSeleccionada.getSelectedIndex();
							if(selected>=0){
								cbxSintomas.addItem(SintomasElegido.get(selected));
								SintomasElegido.remove(selected);
								modelElegido.remove(selected);
							}
						}
					});
					scrollPane.setViewportView(listSeleccionada);
					listSeleccionada.setModel(modelElegido);
				}
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
						Enfermedad aux = new Enfermedad(txtCode.getText(), txtNombre.getText(), SintomasElegido,false );
						Hospital.getInstance().insertarEnfermedad(aux);
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
		LoadSintomas();
		loadEnfermedad();
		reloadElegido();
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
				AgregarAlergiaCbxSintomas();
			}
		}
		else {
			AgregarAlergiaCbxSintomas();
		}
    	
	}
	
	void AgregarAlergiaCbxSintomas() {
		cbxSintomas.removeAll();
		cbxSintomas.addItem("<Seleccione>");
		for (int i = 0; i < SintomasSinElegir.size(); i++ ) {
			cbxSintomas.addItem( SintomasSinElegir.get(i) );
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
	
}
