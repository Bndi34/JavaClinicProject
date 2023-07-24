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
import java.io.IOException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

import logico.Admin;
import logico.Secretaria;
import logico.Usuario;
import visualRegistros.RegistrarCita;
import visualRegistros.RegistrarConsulta;
import visualRegistros.RegistrarEnfermedad;
import visualRegistros.RegistrarUsuario;
import visualRegistros.RegistrarVacuna;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;

public class Dashboard extends JFrame {

	private final JPanel contentPanel = new JPanel();

	private Dimension dim = null;
	
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
		
		Login login = new Login();
		login.setVisible(true);
		
		
		
		Usuario cuenta = login.getUsuario();
		
		if (cuenta instanceof Secretaria || login.isAdmin())
		{
			
			SecretariaMenu secrMenu;
			if (login.isAdmin())
			{
				Admin admin = login.getAdmin();
				secrMenu = new SecretariaMenu(admin.getUsuario(), null);

			}
			else {
					secrMenu = new SecretariaMenu(cuenta.getCodigo(), ((Secretaria) cuenta).getDependiente());
			}
			secrMenu.setVisible(true);
			dispose();
		}
		
		dim = getToolkit().getScreenSize();
		setBounds(100, 100, 1094, 690);
		setSize(dim.width, dim.height-40);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuarios");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar");
		mntmRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario regUs = new RegistrarUsuario("Registrar Usuario",null);
				regUs.setModal(true);
				regUs.setVisible(true);
			}
		});
		mnUsuario.add(mntmRegistrarUsuario);
		
		JMenuItem mntmListaUsuario = new JMenuItem("Lista");
		mnUsuario.add(mntmListaUsuario);
		
		JMenu mnVacuna = new JMenu("Vacuna");
		menuBar.add(mnVacuna);
		
		JMenuItem mntmRegistrarVacuna = new JMenuItem("Registrar");
		mntmRegistrarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVacuna regVac = new RegistrarVacuna(); //("Registrar Usuario",null);
				regVac.setModal(true);
				regVac.setVisible(true);
			}
		});
		mnVacuna.add(mntmRegistrarVacuna);
		
		JMenuItem mntmListarVacuna = new JMenuItem("Listar");
		mnVacuna.add(mntmListarVacuna);
		
		JMenu mnEnfermedad = new JMenu("Enfermedad");
		menuBar.add(mnEnfermedad);
		
		JMenuItem mntmRegistrarEnfermedad = new JMenuItem("Registrar");
		mntmRegistrarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEnfermedad regEnf;
				try {
					regEnf = new RegistrarEnfermedad();
					regEnf.setModal(true);
					regEnf.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //("Registrar Usuario",null);
			}
		});
		mnEnfermedad.add(mntmRegistrarEnfermedad);
		
		JMenuItem mntmListarEnfermedad = new JMenuItem("Listar");
		mnEnfermedad.add(mntmListarEnfermedad);
		
		JMenu mnDiagnostico = new JMenu("Diagn\u00F3stico");
		menuBar.add(mnDiagnostico);
		
		JMenuItem mntmRegistrarDiagnostico = new JMenuItem("Registrar");
		mntmRegistrarDiagnostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*RegistrarDiagnostico regVac = new RegistrarVacuna(); //("Registrar Usuario",null);
				regVac.setModal(true);
				regVac.setVisible(true);*/
			}
		});
		mnDiagnostico.add(mntmRegistrarDiagnostico);
		
		JMenuItem mntmListarDiagnostico = new JMenuItem("Listar");
		mnDiagnostico.add(mntmListarDiagnostico);
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenuItem mntmRegistrarConsulta = new JMenuItem("Registrar");
		mntmRegistrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarConsulta regCon = new RegistrarConsulta(); //("Registrar Usuario",null);
				regCon.setModal(true);
				regCon.setVisible(true);
			}
		});
		mnConsulta.add(mntmRegistrarConsulta);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Listar");
		mnConsulta.add(mntmNewMenuItem_5);
		
		JMenu mnCita = new JMenu("Cita");
		menuBar.add(mnCita);
		
		JMenuItem mntmRegistrarCita = new JMenuItem("Registrar");
		mntmRegistrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCita regCit = new RegistrarCita("Registrar Cita",null); //("Registrar Usuario",null);
				regCit.setModal(true);
				regCit.setVisible(true);
			}
		});
		mnCita.add(mntmRegistrarCita);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listar");
		mnCita.add(mntmNewMenuItem_7);
		

	}
}
