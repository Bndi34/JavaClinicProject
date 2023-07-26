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
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RegistrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarConsulta dialog = new RegistrarConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarConsulta() {
		setTitle("Registrar Consulta");
		setBounds(100, 100, 450, 300);
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
			JLabel lblDate = new JLabel("Fecha");
			lblDate.setBounds(12, 42, 56, 16);
			contentPanel.add(lblDate);
		}
		{
			JLabel lblDoctor = new JLabel("Doctor");
			lblDoctor.setBounds(12, 81, 56, 16);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(12, 110, 56, 16);
			contentPanel.add(lblPaciente);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(12, 162, 56, 16);
			contentPanel.add(lblEstado);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(80, 10, 152, 22);
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
			txtCode.setText("CON-"+String.valueOf(Hospital.getInstance().generadorConsulta));
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(80, 39, 152, 22);
			contentPanel.add(textField_1);
		}
		{
			JComboBox cbDoctor = new JComboBox();
			cbDoctor.setBounds(80, 78, 152, 22);
			contentPanel.add(cbDoctor);
		}
		{
			JComboBox cbPaciente = new JComboBox();
			cbPaciente.setBounds(80, 107, 152, 22);
			contentPanel.add(cbPaciente);
		}
		{
			JComboBox cbEstado = new JComboBox();
			cbEstado.setBounds(80, 159, 152, 22);
			contentPanel.add(cbEstado);
		}
		{
			JCheckBox chbxRetraso = new JCheckBox("");
			chbxRetraso.setBounds(130, 184, 34, 25);
			contentPanel.add(chbxRetraso);
		}
		{
			JLabel lblSintomas = new JLabel("Sintomas");
			lblSintomas.setBounds(244, 13, 56, 16);
			contentPanel.add(lblSintomas);
		}
		{
			JComboBox cboxSintoma = new JComboBox();
			cboxSintoma.setBounds(244, 39, 176, 22);
			contentPanel.add(cboxSintoma);
		}
		{
			JButton btnBorrarSintoma = new JButton("Borrar Sintoma Elegido");
			btnBorrarSintoma.setBounds(244, 77, 176, 25);
			contentPanel.add(btnBorrarSintoma);
		}
		{
			JLabel lblRetraso = new JLabel("Consulta Retrasada");
			lblRetraso.setBounds(12, 189, 130, 16);
			contentPanel.add(lblRetraso);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//registrar????
						
						try {
							Hospital.save();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
