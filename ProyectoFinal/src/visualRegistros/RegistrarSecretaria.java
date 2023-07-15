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

public class RegistrarSecretaria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumber;
	private JTextField txtCedula;
	private JTextField txtName;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarSecretaria dialog = new RegistrarSecretaria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarSecretaria() {
		setTitle("Registrar Secretaria");
		setBounds(100, 100, 351, 274);
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
			JLabel lblNumber = new JLabel("Tel\u00E9fono");
			lblNumber.setBounds(12, 71, 56, 16);
			contentPanel.add(lblNumber);
		}
		{
			JLabel lblDoctor = new JLabel("Supervisor");
			lblDoctor.setBounds(12, 100, 75, 16);
			contentPanel.add(lblDoctor);
		}
		{
			txtNumber = new JTextField();
			txtNumber.setBounds(97, 71, 224, 22);
			contentPanel.add(txtNumber);
			txtNumber.setColumns(10);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(97, 42, 224, 22);
			contentPanel.add(txtCedula);
		}
		{
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(97, 13, 224, 22);
			contentPanel.add(txtName);
		}
		{
			JComboBox cboxDoctor = new JComboBox();
			cboxDoctor.setBounds(97, 97, 224, 22);
			contentPanel.add(cboxDoctor);
		}
		{
			JLabel lblPassword = new JLabel("Contrase\u00F1a");
			lblPassword.setBounds(12, 163, 109, 16);
			contentPanel.add(lblPassword);
		}
		{
			txtPassword = new JTextField();
			txtPassword.setBounds(97, 160, 224, 22);
			contentPanel.add(txtPassword);
			txtPassword.setColumns(10);
		}
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
