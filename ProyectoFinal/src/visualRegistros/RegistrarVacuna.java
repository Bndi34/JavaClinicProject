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

public class RegistrarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;

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
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 456, 206);
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
			JLabel lblEnfermedad = new JLabel("Enfermedad Prevenida");
			lblEnfermedad.setBounds(22, 66, 129, 16);
			contentPanel.add(lblEnfermedad);
		}
		{
			JLabel lblAlergia = new JLabel("Posibles Alergias");
			lblAlergia.setBounds(22, 95, 129, 16);
			contentPanel.add(lblAlergia);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(71, 10, 116, 22);
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			JComboBox cboxEnfermedad = new JComboBox();
			cboxEnfermedad.setBounds(163, 63, 116, 22);
			contentPanel.add(cboxEnfermedad);
		}
		{
			JComboBox cboxAlergias = new JComboBox();
			cboxAlergias.setBounds(163, 92, 116, 22);
			contentPanel.add(cboxAlergias);
		}
		{
			JButton btnBorrarAlergia = new JButton("Borrar Alergia");
			btnBorrarAlergia.setBounds(291, 91, 123, 25);
			contentPanel.add(btnBorrarAlergia);
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
