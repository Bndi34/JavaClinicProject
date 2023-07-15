package visualRegistros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JTextField txtSex;
	private JTextField txtCedula;
	private JTextField txtName;

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
			lblName.setBounds(12, 13, 56, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblCedula = new JLabel("C\u00E9dula");
			lblCedula.setBounds(12, 42, 56, 16);
			contentPanel.add(lblCedula);
		}
		{
			JLabel lblSex = new JLabel("Sexo");
			lblSex.setBounds(12, 71, 56, 16);
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
			txtPassword.setBounds(142, 189, 116, 22);
			contentPanel.add(txtPassword);
			txtPassword.setColumns(10);
		}
		{
			txtNumber = new JTextField();
			txtNumber.setColumns(10);
			txtNumber.setBounds(142, 129, 116, 22);
			contentPanel.add(txtNumber);
		}
		{
			txtDir = new JTextField();
			txtDir.setColumns(10);
			txtDir.setBounds(142, 100, 116, 22);
			contentPanel.add(txtDir);
		}
		{
			txtSex = new JTextField();
			txtSex.setColumns(10);
			txtSex.setBounds(142, 71, 116, 22);
			contentPanel.add(txtSex);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(142, 42, 116, 22);
			contentPanel.add(txtCedula);
		}
		{
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(142, 13, 116, 22);
			contentPanel.add(txtName);
		}
		
		JComboBox cboxAlergia = new JComboBox();
		cboxAlergia.setBounds(285, 39, 116, 22);
		contentPanel.add(cboxAlergia);
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
}
