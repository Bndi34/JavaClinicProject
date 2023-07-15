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
import javax.swing.JCheckBox;

public class RegistrarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtCodeInternational;
	private JTextField txtNombre;

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
	 */
	public RegistrarEnfermedad() {
		setTitle("Registar Enfermedad");
		setBounds(100, 100, 381, 251);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(12, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(12, 103, 56, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblCura = new JLabel("Tiene Cura");
			lblCura.setBounds(12, 132, 93, 16);
			contentPanel.add(lblCura);
		}
		{
			JLabel lblCodeInternational = new JLabel("Tipo");
			lblCodeInternational.setBounds(12, 42, 56, 16);
			contentPanel.add(lblCodeInternational);
		}
		{
			JLabel lblSintomas = new JLabel("S\u00EDntomas");
			lblSintomas.setBounds(208, 13, 56, 16);
			contentPanel.add(lblSintomas);
		}
		{
			txtCode = new JTextField();
			txtCode.setBounds(80, 10, 116, 22);
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			txtCodeInternational = new JTextField();
			txtCodeInternational.setBounds(80, 39, 116, 22);
			contentPanel.add(txtCodeInternational);
			txtCodeInternational.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(80, 100, 116, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JComboBox cboxSintomas = new JComboBox();
			cboxSintomas.setBounds(208, 39, 130, 22);
			contentPanel.add(cboxSintomas);
		}
		{
			JButton btnBorrarSintoma = new JButton("Borrar S\u00EDntoma");
			btnBorrarSintoma.setBounds(208, 74, 130, 25);
			contentPanel.add(btnBorrarSintoma);
		}
		{
			JCheckBox chboxCura = new JCheckBox("");
			chboxCura.setBounds(80, 132, 113, 16);
			contentPanel.add(chboxCura);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
	}

}
