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
import java.awt.event.ActionEvent;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtDate;
	//private Date

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita() {
		setTitle("Registrar Cita");
		setBounds(100, 100, 353, 300);
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
			lblDoctor.setBounds(12, 90, 56, 16);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(12, 119, 56, 16);
			contentPanel.add(lblPaciente);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(12, 160, 56, 16);
			contentPanel.add(lblEstado);
		}
		{
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setBounds(80, 10, 243, 22);
			txtCode.setText("CI-"+String.valueOf(Hospital.getInstance().generadorCita));
			contentPanel.add(txtCode);
			txtCode.setColumns(10);
		}
		{
			txtDate = new JTextField();
			txtDate.setBounds(80, 39, 243, 22);
			contentPanel.add(txtDate);
			txtDate.setColumns(10);
		}
		{
			JComboBox cbxDoctor = new JComboBox();
			cbxDoctor.setBounds(80, 90, 243, 22);
			contentPanel.add(cbxDoctor);
		}
		{
			JComboBox cbxPaciente = new JComboBox();
			cbxPaciente.setBounds(80, 116, 243, 22);
			contentPanel.add(cbxPaciente);
		}
		{
			JComboBox cbxEstado = new JComboBox();
			cbxEstado.setBounds(80, 157, 243, 22);
			contentPanel.add(cbxEstado);
		}
		
		JCheckBox chbxRetraso = new JCheckBox("");
		chbxRetraso.setBounds(108, 184, 36, 25);
		contentPanel.add(chbxRetraso);
		{
			JLabel lblNewLabel = new JLabel("Cita Retrasada");
			lblNewLabel.setBounds(12, 189, 102, 16);
			contentPanel.add(lblNewLabel);
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
