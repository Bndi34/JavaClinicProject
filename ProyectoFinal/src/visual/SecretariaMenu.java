package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import logico.Doctor;
import visualListar.ListarCompromiso;
import visualListar.ListarCuenta;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecretariaMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUserCode;
	private JTextField txtDoctorName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SecretariaMenu dialog = new SecretariaMenu("N/A", null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SecretariaMenu(String codigo, Doctor supervisor) {
		setTitle("Listados");
		setBounds(100, 100, 570, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("Codigo de Usuario: ");
			lblUser.setBounds(12, 13, 113, 16);
			contentPanel.add(lblUser);
		}
		{
			JLabel lblDoctor = new JLabel("Supervisor:");
			lblDoctor.setBounds(12, 42, 113, 16);
			contentPanel.add(lblDoctor);

		}
		{
			txtUserCode = new JTextField();
			txtUserCode.setEditable(false);
			txtUserCode.setBounds(137, 10, 388, 22);
			contentPanel.add(txtUserCode);
			txtUserCode.setColumns(10);
			
			txtUserCode.setText(codigo);
		}
		{
			txtDoctorName = new JTextField();
			txtDoctorName.setEditable(false);
			txtDoctorName.setBounds(137, 39, 388, 22);
			contentPanel.add(txtDoctorName);
			txtDoctorName.setColumns(10);
			
			
			try {
				txtDoctorName.setText(supervisor.getNombre());
				
			} catch (NullPointerException e) {
				txtDoctorName.setText("N/A");
			}
		}
		
		JButton btnAccounts = new JButton("Lista de Cuentas");
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCuenta listaCuentas = new ListarCuenta("cuenta", true, codigo);
				listaCuentas.setVisible(true);
				listaCuentas.setModal(true);
			}
		});
		btnAccounts.setBounds(22, 74, 218, 67);
		contentPanel.add(btnAccounts);
		
		JButton btnConsultas = new JButton("Lista de Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				ListarCompromiso ListConsult = new ListarCompromiso("consulta");
				ListConsult.setVisible(true);
				ListConsult.setModal(true);
				*/
			}
		});
		btnConsultas.setBounds(22, 154, 218, 60);
		contentPanel.add(btnConsultas);
		
		JButton btnCitas = new JButton("Lista de Citas");
		btnCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCitas.setBounds(22, 227, 218, 67);
		contentPanel.add(btnCitas);
		
		JButton btnEnfermedades = new JButton("Enfermedades");
		btnEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnfermedades.setBounds(305, 74, 218, 67);
		contentPanel.add(btnEnfermedades);
		
		JButton btnVacunas = new JButton("Lista de Vacunas");
		btnVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVacunas.setBounds(305, 154, 221, 60);
		contentPanel.add(btnVacunas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
}
