package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.WindowConstants;

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
import logico.Doctor;
import logico.Hospital;
import logico.Paciente;
import logico.Secretaria;
import logico.Usuario;
import visualListar.ListarCompromiso;
import visualListar.ListarCuenta;
import visualRegistros.RegistrarCita;
import visualRegistros.RegistrarConsulta;
import visualRegistros.RegistrarEnfermedad;
import visualRegistros.RegistrarUsuario;
import visualRegistros.RegistrarVacuna;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {

	private final JPanel contentPanel = new JPanel();
	JPanel btnDetallesDia = new JPanel();
	JPanel btnDetallesHistorialMedico = new JPanel();
	private JMenu mnUser;
	
	private Dimension dim = null;
	private JTextField txtSupervisor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dashboard dialog = new Dashboard(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dashboard(final Usuario Cuenta) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Centro M\u00E9dico");
		
		setBounds(100, 100, 1270, 690);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		try {
		} catch (NullPointerException e) {
			mnUser.setText("Error");
		}
		
		JLabel lblCalendario = new JLabel("Calendario");
		lblCalendario.setBounds(22, 119, 82, 16);
		contentPanel.add(lblCalendario);
		
		JLabel lblDetallesDelDia = new JLabel("Horario del D\u00EDa");
		lblDetallesDelDia.setBounds(762, 129, 102, 16);
		contentPanel.add(lblDetallesDelDia);
		
		JLabel lblHistorialMedico = new JLabel("Historial Medico");
		lblHistorialMedico.setBounds(992, 129, 112, 16);
		contentPanel.add(lblHistorialMedico);
		
		JLabel Dashboard = new JLabel("DASHBOARD");
		Dashboard.setFont(new Font("Impact", Font.PLAIN, 33));
		Dashboard.setBounds(22, 13, 217, 54);
		contentPanel.add(Dashboard);
		
		JPanel panel_calendar = new JPanel();
		panel_calendar.setBackground(Color.LIGHT_GRAY);
		panel_calendar.setBounds(22, 159, 707, 467);
		contentPanel.add(panel_calendar);
		panel_calendar.setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 11, 685, 443);
		panel_calendar.add(calendar);
		
		JPanel panel = new JPanel();
		panel.setBounds(741, 158, 445, 458);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 159, 246);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrllDetallesDia = new JScrollPane();
		scrllDetallesDia.setBounds(0, 0, 159, 400);
		panel_1.add(scrllDetallesDia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(240, 40, 158, 360);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrllHistorialMedico = new JScrollPane();
		scrllHistorialMedico.setBounds(240, 11, 158, 257);
		panel_2.add(scrllHistorialMedico);
		
		JComboBox cboxHistorialMedico = new JComboBox();
		cboxHistorialMedico.setBackground(new Color(51, 153, 102));
		cboxHistorialMedico.setBounds(240, 7, 138, 22);
		cboxHistorialMedico.setBorder(null); // new Color(51, 153, 102)
		panel.add(cboxHistorialMedico);
		cboxHistorialMedico.setModel(new DefaultComboBoxModel(new String[] {"<Paciente>"}));
		
		btnDetallesDia.setBackground(new Color(51, 153, 102));
		btnDetallesDia.setBounds(38, 411, 110, 34);
		panel.add(btnDetallesDia);
		btnDetallesDia.setLayout(null);
		
		JLabel lblbtnDetallesDia = new JLabel("    Detalles...");
		lblbtnDetallesDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(btnDetallesDia);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				removeSetColor(btnDetallesDia);
			}
		});
		lblbtnDetallesDia.setBounds(0, 0, 110, 34);
		lblbtnDetallesDia.setForeground(new Color(255, 255, 255));
		lblbtnDetallesDia.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDetallesDia.add(lblbtnDetallesDia);
		
		btnDetallesHistorialMedico.setLayout(null);
		btnDetallesHistorialMedico.setBackground(new Color(51, 153, 102));
		btnDetallesHistorialMedico.setBounds(260, 411, 110, 34);
		panel.add(btnDetallesHistorialMedico);
		
		JLabel lblbtnDetallesHistorialMedico = new JLabel("    Detalles...");
		lblbtnDetallesHistorialMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(btnDetallesHistorialMedico);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				removeSetColor(btnDetallesHistorialMedico);
			}
		});
		lblbtnDetallesHistorialMedico.setForeground(Color.WHITE);
		lblbtnDetallesHistorialMedico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblbtnDetallesHistorialMedico.setBounds(0, 0, 110, 34);
		btnDetallesHistorialMedico.add(lblbtnDetallesHistorialMedico);
		
		try {
			if (Cuenta instanceof Secretaria)
		{
			txtSupervisor = new JTextField();
			txtSupervisor.setText(((Secretaria) Cuenta).getDependiente().getNombre());
			txtSupervisor.setEditable(false);
			txtSupervisor.setBounds(1127, 50, 116, 22);
			contentPanel.add(txtSupervisor);
			txtSupervisor.setColumns(10);
			
			JLabel lblSupervisor = new JLabel("Supervisor");
			lblSupervisor.setBounds(1041, 51, 74, 16);
			contentPanel.add(lblSupervisor);
		}
		} catch (NullPointerException e) {
			txtSupervisor.setVisible(false);
		}
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuarios");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar");
		mntmRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario regUs;
				
				if (Cuenta instanceof Admin)
				{
					regUs = new RegistrarUsuario("Registrar Usuario",null, true);
				}
				else 
				{
					regUs = new RegistrarUsuario("Registrar Usuario",null, false);
				}
				
				regUs.setModal(true);
				regUs.setVisible(true);
			}
		});
		mnUsuario.add(mntmRegistrarUsuario);
		
		JMenuItem mntmListaUsuario = new JMenuItem("Lista");
		mntmListaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCuenta listaCuentas = new ListarCuenta("cuenta", true, Cuenta.getCodigo());
				
				listaCuentas.setVisible(true);
				listaCuentas.setModal(true);
			}
		});
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
					regEnf = new RegistrarEnfermedad(null);
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
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenuItem mntmRegistrarConsulta = new JMenuItem("Registrar");
		mntmRegistrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarConsulta regCon = new RegistrarConsulta(null);
				regCon.setModal(true);
				regCon.setVisible(true);
			}
		});
		mnConsulta.add(mntmRegistrarConsulta);
		
		JMenuItem mntmListarConsulta = new JMenuItem("Listar");
		mntmListarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCompromiso listCons = new ListarCompromiso("consulta");
				listCons.setVisible(true);
				
			}
		});
		mnConsulta.add(mntmListarConsulta);
		
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
		
		JMenuItem mntmListarCita = new JMenuItem("Listar");
		mnCita.add(mntmListarCita);
		
		JMenu mnEstadisticas = new JMenu("Estad\u00EDsticas");
		menuBar.add(mnEstadisticas);
		
		JMenuItem mntmAbrirEstadisticas = new JMenuItem("Abrir men\u00FA de Estad\u00EDsticas");
		mnEstadisticas.add(mntmAbrirEstadisticas);
		
		mnUser = new JMenu("<User>");
		menuBar.add(mnUser);
		mnUser.setText(Cuenta.getNombre());
		
		JMenuItem mntmUserConfig = new JMenuItem("Configuraci\u00F3n");
		mnUser.add(mntmUserConfig);
		
		JMenuItem mntmUserLogOut = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmUserLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				Login aLogin = new Login();
				aLogin.setVisible(true);
				
				
				dispose();
			}
		});
		mnUser.add(mntmUserLogOut);
		
		if (Cuenta instanceof Doctor || Cuenta instanceof Paciente)
		{
			mnUsuario.setEnabled(false);
			mnVacuna.setEnabled(false);
			mnConsulta.setEnabled(false);
			mnEnfermedad.setEnabled(false);
			mnCita.setEnabled(false);
		}
		//openLogIn();

	}
	
	private void openLogIn()
	{
		Login login = new Login();
		login.setVisible(true);
		
		Usuario cuenta = null;
		
		cuenta = login.getUsuario();
		System.out.println(cuenta);

		try {
			
			if (cuenta instanceof Secretaria || login.isAdmin())
			{
				
				SecretariaMenu secrMenu;
				if (login.isAdmin())
				{
					Admin admin = login.getAdmin();
					//secrMenu = new SecretariaMenu(admin.getUsuario(), null);
					//what is this?

				}
				else {
						secrMenu = new SecretariaMenu(cuenta.getCodigo(), ((Secretaria) cuenta).getDependiente());
				}
				//secrMenu.setVisible(true);
			}
			
			
		} catch (NullPointerException e) {
			setVisible(false);
		}
		
	}
	
	private void setColor(JPanel p) {
		p.setBackground(new Color(73, 173, 128));
	}
	private void removeSetColor(JPanel p) {
		p.setBackground(new Color(51, 153, 102));
	}
}
