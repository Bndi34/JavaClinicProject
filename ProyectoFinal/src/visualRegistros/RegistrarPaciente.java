package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Hospital;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPassword;
	private JTextField txtNumber;
	private JTextField txtDir;
	private JTextField txtCedula;
	private JTextField txtName;
	private JTextField txtCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPaciente dialog = new RegistrarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPaciente() {
		setTitle("Registrar Paciente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(12, 46, 56, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblCedula = new JLabel("C\u00E9dula");
			lblCedula.setBounds(12, 82, 56, 16);
			contentPanel.add(lblCedula);
		}
		{
			JLabel lblSex = new JLabel("Sexo");
			lblSex.setBounds(285, 82, 56, 16);
			contentPanel.add(lblSex);
		}
		{
			JLabel lblDir = new JLabel("Direcci\u00F3n");
			lblDir.setBounds(12, 100, 56, 16);
			contentPanel.add(lblDir);
		}
		{
			JLabel lblNumber = new JLabel("N\u00FAmero de Tel\u00E9fono");
			lblNumber.setBounds(12, 129, 140, 16);
			contentPanel.add(lblNumber);
		}
		{
			JLabel lblPassword = new JLabel("Contrase\u00F1a");
			lblPassword.setBounds(12, 189, 75, 16);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblAlergia = new JLabel("Alergias Conocidas");
			lblAlergia.setBounds(285, 13, 135, 16);
			contentPanel.add(lblAlergia);
		}
		{
			txtPassword = new JTextField();
			txtPassword.setBounds(142, 187, 116, 22);
			contentPanel.add(txtPassword);
			txtPassword.setColumns(10);
		}
		{
			txtNumber = new JTextField();
			txtNumber.setColumns(10);
			txtNumber.setBounds(142, 151, 116, 22);
			contentPanel.add(txtNumber);
		}
		{
			txtDir = new JTextField();
			txtDir.setColumns(10);
			txtDir.setBounds(142, 115, 116, 22);
			contentPanel.add(txtDir);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(142, 79, 116, 22);
			contentPanel.add(txtCedula);
		}
		{
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(142, 43, 116, 22);
			contentPanel.add(txtName);
		}
		
		JComboBox cboxAlergia = new JComboBox();
		cboxAlergia.setBounds(285, 39, 116, 22);
		contentPanel.add(cboxAlergia);
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setColumns(10);
			txtCode.setBounds(142, 7, 116, 22);
			txtCode.setText("PA-"+String.valueOf(Hospital.getInstance().generadorUsuario));
			contentPanel.add(txtCode);
		}
		
		JComboBox cbxSexo = new JComboBox();
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Hombre", "Mujer"}));
		cbxSexo.setBounds(285, 116, 116, 22);
		contentPanel.add(cbxSexo);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 13, 56, 16);
		contentPanel.add(lblCdigo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	
	//Función para transformar lo seleccionado en cbxSex a un char
	private char GetSex(String opc) {
		if (opc == "Hombre") {
			return 'H';
		}
		
		if (opc == "Mujer") {
			return 'M';
		}
		return ' ';
	}
	
	//Función para cargar al Paciente
	private int ColocarSexo(char opc) {
		if (opc == 'H') {
			return 0;
		}
		
		if (opc == 'M') {
			return 1;
		}
		return -1;
	}
	
}
