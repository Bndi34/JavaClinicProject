package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import javax.swing.JScrollPane;

public class Dashboard extends JFrame {

	private final JPanel contentPanel = new JPanel();

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
		
		JMenu mnUser = new JMenu("<User>");
		mnUser.setBounds(787, 13, 125, 24);
		contentPanel.add(mnUser);
		
		JMenuItem mntmUserConfig = new JMenuItem("Configuraci\u00F3n");
		mnUser.add(mntmUserConfig);
		
		JMenuItem mntmUserLogOut = new JMenuItem("Cerrar Sesi\u00F3n");
		mnUser.add(mntmUserLogOut);
		
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
		
		JPanel panel_calendar = new JPanel();
		panel_calendar.setBackground(Color.LIGHT_GRAY);
		panel_calendar.setBounds(29, 116, 484, 300);
		contentPanel.add(panel_calendar);
		panel_calendar.setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 11, 464, 278);
		panel_calendar.add(calendar);
		
		JPanel panel = new JPanel();
		panel.setBounds(523, 116, 445, 300);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 159, 246);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrllDetallesDia = new JScrollPane();
		scrllDetallesDia.setBounds(0, 0, 159, 246);
		panel_1.add(scrllDetallesDia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(240, 40, 158, 221);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrllHistorialMedico = new JScrollPane();
		scrllHistorialMedico.setBounds(240, 11, 158, 257);
		panel_2.add(scrllHistorialMedico);
		
		JComboBox cboxHistorialMedico = new JComboBox();
		cboxHistorialMedico.setBounds(240, 7, 138, 22);
		panel.add(cboxHistorialMedico);
		cboxHistorialMedico.setModel(new DefaultComboBoxModel(new String[] {"<Paciente>"}));
		
		JButton btnDetallesDia = new JButton("Detalles...");
		btnDetallesDia.setBounds(36, 266, 89, 23);
		panel.add(btnDetallesDia);
		
		JButton btnDetallesHistorialMedico = new JButton("Detalles...");
		btnDetallesHistorialMedico.setBounds(277, 266, 89, 23);
		panel.add(btnDetallesHistorialMedico);
		

	}
}
