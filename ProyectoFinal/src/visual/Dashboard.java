package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable EspacioParaCalendario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dashboard dialog = new Dashboard();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dashboard() {
		setBounds(100, 100, 994, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			EspacioParaCalendario = new JTable();
			EspacioParaCalendario.setBounds(29, 116, 484, 300);
			contentPanel.add(EspacioParaCalendario);
		}
		
		JButton btnDetallesDia = new JButton("Detalles");
		btnDetallesDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetallesDia.setBounds(558, 391, 138, 25);
		contentPanel.add(btnDetallesDia);
		
		ScrollPane scrollDetallesDia = new ScrollPane();
		scrollDetallesDia.setBounds(558, 116, 138, 269);
		contentPanel.add(scrollDetallesDia);
		
		ScrollPane scrollHistorialMedico = new ScrollPane();
		scrollHistorialMedico.setBounds(774, 148, 138, 237);
		contentPanel.add(scrollHistorialMedico);
		
		JComboBox cboxHistorialMedico = new JComboBox();
		cboxHistorialMedico.setModel(new DefaultComboBoxModel(new String[] {"<Paciente>"}));
		cboxHistorialMedico.setBounds(774, 116, 138, 22);
		contentPanel.add(cboxHistorialMedico);
		
		JMenu mnUser = new JMenu("<User>");
		mnUser.setBounds(787, 13, 125, 24);
		contentPanel.add(mnUser);
		
		JMenuItem mntmUserConfig = new JMenuItem("Configuraci\u00F3n");
		mnUser.add(mntmUserConfig);
		
		JMenuItem mntmUserLogOut = new JMenuItem("Cerrar Sesi\u00F3n");
		mnUser.add(mntmUserLogOut);
		
		JButton btnDetallesHistorialMedico = new JButton("Detalles");
		btnDetallesHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetallesHistorialMedico.setBounds(774, 391, 138, 25);
		contentPanel.add(btnDetallesHistorialMedico);
		
		JLabel lblCalendario = new JLabel("Calendario");
		lblCalendario.setBounds(30, 87, 82, 16);
		contentPanel.add(lblCalendario);
		
		JLabel lblDetallesDelDia = new JLabel("Detalles");
		lblDetallesDelDia.setBounds(558, 87, 56, 16);
		contentPanel.add(lblDetallesDelDia);
		
		JLabel lblHistorialMedico = new JLabel("Historial Medico");
		lblHistorialMedico.setBounds(778, 87, 56, 16);
		contentPanel.add(lblHistorialMedico);
		
		JLabel Dashboard = new JLabel("DASHBOARD");
		Dashboard.setBounds(30, 21, 171, 16);
		contentPanel.add(Dashboard);
	}
	
	//asd
}
