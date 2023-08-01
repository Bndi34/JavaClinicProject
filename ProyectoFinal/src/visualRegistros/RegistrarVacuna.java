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
	private boolean antiDoubleAddFilter = false;
	private String selectedAlergia;
	private String selectedEnfermedad;
	
	private int selected = -1;
	
	private String selectedItem;
	private ArrayList<String>EnfermedadesElegidas = new ArrayList<>();
	private ArrayList<String>EnfermedadeSinElegir = new ArrayList<>();
	private ArrayList<String>AlergiasElegidas = new ArrayList<>();
	private ArrayList<String>AlergiasSinElegir = new ArrayList<>();
	private JTextField txtAlergia;
	private JTextField txtEnfermedad;
	
	private Vacuna auxVacuna;
	
	//private ArrayList<Enfermedad>EnfermedadesPrevenidas;
	//private ArrayList<String>Alergias;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarVacuna dialog = new RegistrarVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarVacuna(Vacuna entrada) {
		
		auxVacuna = entrada;
		
		loadEntrada();
		
		AlergiasSinElegir = Hospital.getInstance().getAlergiasRegistradas();
		loadEnfermedadesSinElegir();
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modelEnfermedad = new DefaultListModel<String>();
		modelAlergia = new DefaultListModel<String>();
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 384, 474);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(22, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblEnfermedad = new JLabel("Enfermedad Prevenida:");
			lblEnfermedad.setBounds(22, 49, 165, 16);
			contentPanel.add(lblEnfermedad);
		}
		{
			JLabel lblAlergia = new JLabel("Posibles Alergias:");
			lblAlergia.setBounds(28, 173, 129, 16);
			contentPanel.add(lblAlergia);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(71, 10, 265, 22);
			txtCode.setText("VA-"+String.valueOf(Hospital.getInstance().generadorVacuna));
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			cbxEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					{
						try {
							selectedEnfermedad = cbxEnfermedad.getSelectedItem().toString();
							System.out.println(selectedEnfermedad);
							if ( selectedEnfermedad.equalsIgnoreCase("Agregar...") ) {
								btnEnfermedad.setText("Añadir");
								
								txtEnfermedad.setVisible(true);
								txtEnfermedad.setEditable(true);
								txtEnfermedad.setEnabled(true);
								txtEnfermedad.setText("");
								
								cbxEnfermedad.setVisible(false);
								//cbxAlergia.setEditable(true);
								//cbxAlergia.setSelectedItem("");
								
							}
							else if (!selectedEnfermedad.equalsIgnoreCase("<Seleccione>"))
							{
								EnfermedadesElegidas.add(selectedEnfermedad);
								System.out.println(EnfermedadeSinElegir.get(0));
								EnfermedadeSinElegir.remove(selectedEnfermedad);
								
								System.out.println("Reload Cuadro Combobox");
								reloadEnfermedad();
								reloadEnfermedadCuadro();
							}
							else
							{
								btnEnfermedad.setText("Borrar");
								
								txtEnfermedad.setVisible(false);
								txtEnfermedad.setEditable(false);
								txtEnfermedad.setEnabled(false);
								txtEnfermedad.setText("");
								
								cbxEnfermedad.setVisible(true);	
							}
						} catch (NullPointerException e2) {
							// TODO: handle exception
						}
						
				}
			}});
			cbxEnfermedad.setBounds(22, 76, 196, 22);
			contentPanel.add(cbxEnfermedad);
			if (cbxEnfermedad.getItemCount() == 0) {
				cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				reloadEnfermedad();
				//cbxEnfermedad.addItem("Agregar...");
			}
		}
		
		
		
		{
			
			cbxAlergia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						 selectedAlergia = cbxAlergia.getSelectedItem().toString();
						if ( selectedAlergia.equalsIgnoreCase("Agregar...") ) {
							btnAlergia.setText("Añadir");
							
							txtAlergia.setVisible(true);
							txtAlergia.setEditable(true);
							txtAlergia.setEnabled(true);
							txtAlergia.setText("");
							
							cbxAlergia.setVisible(false);
							//cbxAlergia.setEditable(true);
							//cbxAlergia.setSelectedItem("");
							
						}
						else if (!selectedAlergia.equalsIgnoreCase("<Seleccione>"))
						{
							AlergiasElegidas.add(selectedAlergia);
							AlergiasSinElegir.remove(selectedAlergia);
							
							System.out.println("Reload Cuadro Combobox");
							reloadEnfermedad();
							reloadEnfermedadCuadro();
						}
						else
						{
							btnAlergia.setText("Borrar");
							
							txtAlergia.setVisible(false);
							txtAlergia.setEditable(false);
							txtAlergia.setEnabled(false);
							txtAlergia.setText("");
							
							cbxAlergia.setVisible(true);	
						}
						
					} catch (NullPointerException e2) {
						// TODO: handle exception
					}
					
				}
			});
			cbxAlergia.setBounds(22, 200, 196, 22);
			contentPanel.add(cbxAlergia);
			reloadAlergia();
			
		}
		btnEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEnfermedad = cbxEnfermedad.getSelectedItem().toString();
				
				if ( btnEnfermedad.getText().equalsIgnoreCase("Borrar")) {
					try {
						if (!txtEnfermedad.getText().isEmpty())
						{
							EnfermedadeSinElegir.add(txtEnfermedad.getText());
							EnfermedadesElegidas.remove(txtEnfermedad.getText());
							txtEnfermedad.setText("");
							
							reloadEnfermedad();
							reloadEnfermedadCuadro();
						}
						
						
					} catch (IndexOutOfBoundsException e2) {
						btnEnfermedad.setText("Añadir");
					}
					
				}
				else if ( txtEnfermedad.isEditable() ) {
					
					System.out.println("txtEnfermedad TXT: "+txtEnfermedad.getText());
					EnfermedadesElegidas.add(txtEnfermedad.getText());
					
					txtEnfermedad.setVisible(false);
					cbxEnfermedad.setVisible(true);
					btnEnfermedad.setText("Borrar");
				}
			}
		});
		
		btnEnfermedad.setBounds(230, 76, 106, 23);
		contentPanel.add(btnEnfermedad);
		
		btnAlergia.setBounds(230, 200, 106, 23);
		contentPanel.add(btnAlergia);
		
		btnAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedAlergia = cbxAlergia.getSelectedItem().toString();
				
				if ( btnAlergia.getText().equalsIgnoreCase("Borrar")) {
					try {
						if (!txtAlergia.getText().isEmpty())
						{
							AlergiasSinElegir.add(txtAlergia.getText());
							AlergiasElegidas.remove(txtAlergia.getText());
							txtAlergia.setText("");
							
							reloadAlergia();
							System.out.println("Reload cuadro Borrar");
							reloadAlergiaCuadro();
						}
						
						
					} catch (IndexOutOfBoundsException e2) {
						btnAlergia.setText("Añadir");
					}
					
				}
				else if ( txtAlergia.isEditable() ) {
					
					System.out.println("Alergia TXT: "+txtAlergia.getText());
					AlergiasElegidas.add(txtAlergia.getText());
					
					reloadAlergia();
					reloadAlergiaCuadro();
					txtAlergia.setVisible(false);
					cbxAlergia.setVisible(true);
					btnAlergia.setText("Borrar");
				}
				
				
				
				
				
			}
		});
		
		
		JPanel panel_enfermedad = new JPanel();
		panel_enfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_enfermedad.setBounds(22, 109, 314, 51);
		contentPanel.add(panel_enfermedad);
		panel_enfermedad.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel_enfermedad.add(scrollPane, BorderLayout.CENTER);
			{
				listEnf.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							selected = listEnf.getSelectedIndex();
							selectedEnfermedad = listEnf.getSelectedValue().toString();
							if(selected>=0){
								btnEnfermedad.setText("Borrar");
								txtEnfermedad.setText(listEnf.getSelectedValue().toString());
							}
						} catch (NullPointerException e2) {
							// TODO: handle exception
						}
						
					}
				});
				scrollPane.setViewportView(listEnf);
				listEnf.setModel(modelEnfermedad);
			}
		}
		
		JPanel panel_alergia = new JPanel();
		panel_alergia.setBounds(22, 233, 314, 146);
		contentPanel.add(panel_alergia);
		panel_alergia.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_alergia.add(scrollPane_1);
			{
				listAler.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							selected = listAler.getSelectedIndex();
							selectedAlergia = listAler.getSelectedValue().toString();
							if(selected>=0){
								btnAlergia.setText("Borrar");
								txtAlergia.setText(listAler.getSelectedValue().toString());
							}
						} catch (NullPointerException e2) {
							// TODO: handle exception
						}
						
					}
				});
				scrollPane_1.setViewportView(listAler);
				System.out.println("Reload cuadro 1");
				reloadAlergiaCuadro();
				listAler.setModel(modelAlergia);
			}
		}
		{
			txtAlergia = new JTextField();
			txtAlergia.setEnabled(false);
			txtAlergia.setText("VA-1");
			txtAlergia.setEditable(false);
			txtAlergia.setVisible(false);
			txtAlergia.setColumns(10);
			txtAlergia.setBounds(22, 202, 196, 22);
			contentPanel.add(txtAlergia);
			
			
			txtEnfermedad = new JTextField();
			txtEnfermedad.setEnabled(false);
			txtEnfermedad.setText("VA-1");
			txtEnfermedad.setEditable(false);
			txtEnfermedad.setVisible(false);
			txtEnfermedad.setColumns(10);
			txtEnfermedad.setBounds(22, 76, 196, 22);
			contentPanel.add(txtEnfermedad);
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
						
						Vacuna aux = new Vacuna(codigo,getArrayEnfermedadesElegidas(),AlergiasElegidas);
						Hospital.getInstance().insertarVacuna(aux);
						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						
						try {
							Hospital.save();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
		reloadAlergia();
	}
	
	private void clean() {
		txtCode.setText("VA-"+String.valueOf(Hospital.getInstance().generadorVacuna));
		modelAlergia.removeAllElements();
		modelEnfermedad.removeAllElements();
		cbxEnfermedad.setSelectedIndex(-1);
		cbxAlergia.setSelectedIndex(-1);
		
	}
	

	
	private void reloadAlergia()
	{
		cbxAlergia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		
		for (String temp : AlergiasSinElegir )
		{
			cbxAlergia.addItem(temp);
		}
		
		cbxAlergia.addItem("Agregar...");
		
	}
	private void reloadAlergiaCuadro()
	{
		modelAlergia.removeAllElements();
		for (String auxString : AlergiasElegidas)
		{
			modelAlergia.addElement(auxString);
		}
	}
	
	private void loadEnfermedadesSinElegir()
	{

		EnfermedadeSinElegir = new ArrayList<>();
		
		for (Enfermedad aux : Hospital.getInstance().getEnfermedadesReg())
		{
			String temp = aux.getCodigo()+" : "+aux.getNombre();
			EnfermedadeSinElegir.add(temp);
		}
		
	}
	
		private void reloadEnfermedad() {
		
		cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		
		for (String auxString : EnfermedadeSinElegir)
		{
			cbxEnfermedad.addItem(auxString);
		}
		
	}
	private void reloadEnfermedadCuadro()
	{
		modelEnfermedad.removeAllElements();
		for (String auxString : EnfermedadesElegidas)
		{
			modelEnfermedad.addElement(auxString);
		}
	}
	
	private ArrayList<Enfermedad> getArrayEnfermedadesElegidas()
	{
		ArrayList<Enfermedad> temp = new ArrayList<>();
		
		for (String tempString : EnfermedadesElegidas)
		{
			int i = tempString.indexOf(":");
			System.out.println("Buscando enfermedad codigo: " + tempString.substring(0, i-1));
			temp.add(Hospital.getInstance().buscarEnfermedadesByCode(tempString.substring(0, i-1)));
		}
		
		return temp;
	}
	
	private void loadEntrada()
	{
		try {
			txtCode.setText(auxVacuna.getCodigo());
			AlergiasElegidas = auxVacuna.getPosiblesAlergias();
			
			for (Enfermedad aux : auxVacuna.getEnfermedadesPrevenidas())
			{
				String temp = aux.getCodigo()+" : "+aux.getNombre();
				EnfermedadesElegidas.add(temp);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
		
	}
}
