package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Hospital;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumber;
	private JTextField txtArea;
	private JTextField txtCedula;
	private JTextField txtName;
	private JTextField txtPassword;
	private JTextField txtCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarMedico dialog = new RegistrarMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarMedico() {
		setTitle("Registrar M\u00E9dico");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(12, 42, 56, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblCedula = new JLabel("C\u00E9dula");
			lblCedula.setBounds(12, 81, 56, 16);
			contentPanel.add(lblCedula);
		}
		{
			JLabel lblNumber = new JLabel("N\u00FAmero de Tel\u00E9fono");
			lblNumber.setBounds(12, 121, 99, 16);
			contentPanel.add(lblNumber);
		}
		{
			JLabel lblArea = new JLabel("\u00C1rea m\u00E9dica");
			lblArea.setBounds(12, 162, 129, 16);
			contentPanel.add(lblArea);
		}
		{
			JLabel lblPassword = new JLabel("Contrase\u00F1a");
			lblPassword.setBounds(12, 189, 99, 16);
			contentPanel.add(lblPassword);
		}
		{
			txtNumber = new JTextField();
			txtNumber.setBounds(121, 118, 267, 22);
			contentPanel.add(txtNumber);
			txtNumber.setColumns(10);
		}
		{
			txtArea = new JTextField();
			txtArea.setColumns(10);
			txtArea.setBounds(121, 153, 267, 22);
			contentPanel.add(txtArea);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(121, 39, 267, 22);
			contentPanel.add(txtCedula);
		}
		{
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(121, 78, 267, 22);
			contentPanel.add(txtName);
		}
		{
			txtPassword = new JTextField();
			txtPassword.setColumns(10);
			txtPassword.setBounds(121, 188, 267, 22);
			contentPanel.add(txtPassword);
		}
		
		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBounds(121, 12, 267, 22);
		txtCode.setText("DOC-"+String.valueOf(Hospital.getInstance().generadorUsuario));
		contentPanel.add(txtCode);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 15, 56, 16);
		contentPanel.add(lblCdigo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("Aceptar");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
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
}
