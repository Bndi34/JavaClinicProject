package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesDia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetallesDia dialog = new DetallesDia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetallesDia() {
		setTitle("Detalles");
		setBounds(100, 100, 450, 419);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCode = new JLabel("Codigo");
			lblCode.setBounds(12, 13, 56, 16);
			contentPanel.add(lblCode);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(12, 42, 56, 16);
			contentPanel.add(lblEstado);
		}
		{
			JLabel lblDate = new JLabel("Fecha");
			lblDate.setBounds(12, 71, 56, 16);
			contentPanel.add(lblDate);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(12, 100, 56, 16);
			contentPanel.add(lblPaciente);
		}
		{
			JLabel lblDoctor = new JLabel("Doctor");
			lblDoctor.setBounds(12, 129, 56, 16);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblSintomas = new JLabel("Sintomas");
			lblSintomas.setBounds(12, 158, 56, 16);
			contentPanel.add(lblSintomas);
		}
		
		textField = new JTextField();
		textField.setBounds(80, 10, 116, 22);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(80, 68, 116, 22);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(80, 97, 116, 22);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(79, 126, 116, 22);
		contentPanel.add(textField_4);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(80, 158, 116, 142);
		contentPanel.add(scrollPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Pendiente", "Realizado", "Cancelado"}));
		comboBox.setBounds(80, 39, 116, 22);
		contentPanel.add(comboBox);
		{
			JButton btnDateCambio = new JButton("Cambiar");
			btnDateCambio.setBounds(208, 67, 97, 25);
			contentPanel.add(btnDateCambio);
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
				{
					JButton btnEliminar = new JButton("Eliminar");
					buttonPane.add(btnEliminar);
				}
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
