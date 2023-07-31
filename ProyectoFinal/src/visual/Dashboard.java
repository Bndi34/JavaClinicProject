package visual;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

import logico.Admin;
import logico.Cita;
import logico.Consulta;
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
import org.jfree.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.UIManager;
import javax.swing.JList;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

public class Dashboard extends JFrame {

	private final JPanel contentPanel = new JPanel();
	JPanel btnDetallesDia = new JPanel();
	JPanel dashboardCalender;
	JPanel dashboardStads;
	JPanel btnDetallesHistorialMedico = new JPanel();
	JComboBox cboxHistorialMedico;
	private DefaultListModel<String> modalHistorialMedico;
	JList listHistorial;
	JRadioButton radioCalendar;
	JRadioButton radioDashStads;
	private JMenu mnUser;
	private JTextField txtSupervisor;
	private DefaultPieDataset pieDataSet;
	private JFreeChart Chart;
	private PiePlot piePlot;
	//private ChartPanel ChartPanel;
	
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
	private Color MyGreen = new Color(51, 153, 102);
	private Color MyBlue = 	new Color(72, 61, 139);
	private Paciente paciente;
	private Dimension dim = null;
	
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
		modalHistorialMedico = new DefaultListModel<String>();
		
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
		
		JLabel Dashboard = new JLabel("DASHBOARD");
		Dashboard.setForeground(new Color(255, 255, 255));
		Dashboard.setFont(new Font("Impact", Font.PLAIN, 33));
		Dashboard.setBounds(22, 13, 217, 54);
		contentPanel.add(Dashboard);
		
		radioCalendar = new JRadioButton("");
		radioCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioCalendar.isSelected()) {
					dashboardCalender.setVisible(true);
					dashboardStads.setVisible(false);
					radioDashStads.setSelected(false);
				}
			}
		});
		radioCalendar.setBackground(Color.ORANGE);
		radioCalendar.setBounds(300, 28, 26, 23);
		contentPanel.add(radioCalendar);
		
		radioDashStads = new JRadioButton("");
		radioDashStads.setSelected(true);
		radioDashStads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioDashStads.isSelected()) {
					dashboardCalender.setVisible(false);
					dashboardStads.setVisible(true);
					radioCalendar.setSelected(false);
				}
				
			}
		});
		radioDashStads.setBackground(Color.ORANGE);
		radioDashStads.setBounds(352, 28, 26, 23);
		contentPanel.add(radioDashStads);
		
		dashboardStads = new JPanel();
		dashboardStads.setBounds(0, 0, 1264, 640);
		dashboardStads.setBackground(MyGreen);
		contentPanel.add(dashboardStads);
		dashboardStads.setLayout(null);
		
		JPanel EnfermedadesComunes = new JPanel();
		EnfermedadesComunes.setBounds(26, 122, 428, 252);
		dashboardStads.add(EnfermedadesComunes);
		
		dashboardCalender = new JPanel();
		dashboardCalender.setBackground(MyBlue);
		dashboardCalender.setBounds(0, 0, 1264, 640);
		contentPanel.add(dashboardCalender);
		dashboardCalender.setLayout(null);
		dashboardCalender.setVisible(false);
		
		JLabel lblCalendario = new JLabel("Calendario");
		lblCalendario.setForeground(new Color(255, 255, 255));
		lblCalendario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCalendario.setBounds(300, 138, 112, 16);
		dashboardCalender.add(lblCalendario);
		
		JLabel lblDetallesDelDia = new JLabel("Horario del D\u00EDa");
		lblDetallesDelDia.setForeground(new Color(255, 255, 255));
		lblDetallesDelDia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDetallesDelDia.setBounds(792, 129, 124, 16);
		dashboardCalender.add(lblDetallesDelDia);
		
		JLabel lblHistorialMedico = new JLabel("Historial Medico");
		lblHistorialMedico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHistorialMedico.setForeground(new Color(255, 255, 255));
		lblHistorialMedico.setBounds(983, 129, 133, 16);
		dashboardCalender.add(lblHistorialMedico);
		
		
		JPanel panel_calendar = new JPanel();
		panel_calendar.setBackground(new Color(51, 153, 102));
		panel_calendar.setBounds(22, 159, 707, 467);
		dashboardCalender.add(panel_calendar);
		panel_calendar.setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 11, 685, 443);
		panel_calendar.add(calendar);
		
		JPanel panel_calenderSecundario = new JPanel();
		panel_calenderSecundario.setBackground(new Color(72, 61, 139));
		panel_calenderSecundario.setBounds(741, 158, 445, 468);
		
		LineBorder border = new LineBorder(MyGreen,13);
		panel_calenderSecundario.setBorder(border);
		dashboardCalender.add(panel_calenderSecundario);
		panel_calenderSecundario.setLayout(null);
		
		JPanel panel_horarioDIa = new JPanel();
		panel_horarioDIa.setBounds(43, 52, 158, 348);
		
		panel_calenderSecundario.add(panel_horarioDIa);
		panel_horarioDIa.setLayout(null);
		
		JScrollPane scrllDetallesDia = new JScrollPane();
		scrllDetallesDia.setBounds(0, 0, 158, 348);
		panel_horarioDIa.add(scrllDetallesDia);
		
		JList listHorasDelDia = new JList();
		listHorasDelDia.setBackground(new Color(0, 51, 153));
		listHorasDelDia.setFont(new Font("Tahoma", Font.BOLD, 11));
		listHorasDelDia.setForeground(new Color(51, 153, 102));
		scrllDetallesDia.setColumnHeaderView(listHorasDelDia);
		
		JPanel panel_HistorialMedico = new JPanel();
		panel_HistorialMedico.setBounds(244, 52, 158, 348);
		panel_calenderSecundario.add(panel_HistorialMedico);
		panel_HistorialMedico.setLayout(null);
		
		JScrollPane scrllHistorialMedico = new JScrollPane();
		scrllHistorialMedico.setBounds(0, 0, 158, 348);
		panel_HistorialMedico.add(scrllHistorialMedico);
		
		listHistorial = new JList();
		scrllHistorialMedico.setViewportView(listHistorial);
		
		cboxHistorialMedico = new JComboBox();
		cboxHistorialMedico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboxHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if () {
					int indf = cboxHistorialMedico.getSelectedItem().toString().indexOf(":");
					paciente = (Paciente) Hospital.getInstance().buscarUsuarioByCode(cboxHistorialMedico.getSelectedItem().toString().substring(0, indf-1));
					
					setPacienteHistorialMedico();
					//}
			}
		});
		cboxHistorialMedico.setBackground(new Color(51, 153, 102));
		cboxHistorialMedico.setBounds(244, 17, 158, 26);
		cboxHistorialMedico.setBorder(null); // new Color(51, 153, 102)
		panel_calenderSecundario.add(cboxHistorialMedico);
		cboxHistorialMedico.setModel(new DefaultComboBoxModel(new String[] {"<Paciente>"}));
		
		btnDetallesDia.setBackground(new Color(51, 153, 102));
		btnDetallesDia.setBounds(65, 411, 110, 34);
		panel_calenderSecundario.add(btnDetallesDia);
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
		panel_calenderSecundario.add(btnDetallesHistorialMedico);
		
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
		
		JMenu mnCita = new JMenu("Chequeos");
		menuBar.add(mnCita);
		
		JMenuItem mntmRegistrarCita = new JMenuItem("Hacer una Cita");
		mntmRegistrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCita regCit = new RegistrarCita(null); //("Registrar Usuario",null);
				regCit.setModal(true);
				regCit.setVisible(true);
			}
		});
		mnCita.add(mntmRegistrarCita);
		
		JMenuItem mntmListarCita = new JMenuItem("Listar Citas");
		mntmListarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCompromiso listCita = new ListarCompromiso("cita");
				listCita.setVisible(true);
			}
		});
		mnCita.add(mntmListarCita);
		
		JMenuItem mntmListarConsultas = new JMenuItem("Listar Consultas");
		mntmListarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCompromiso listarConsulta = new ListarCompromiso("consulta");
				listarConsulta.setVisible(true);
			}
		});
		mnCita.add(mntmListarConsultas);
		
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
		
		JMenu mnEstadisticas = new JMenu("Estad\u00EDsticas");
		menuBar.add(mnEstadisticas);
		
		JMenuItem mntmAbrirEstadisticas = new JMenuItem("Abrir men\u00FA de Estad\u00EDsticas");
		mnEstadisticas.add(mntmAbrirEstadisticas);
		
		JMenu mnRespaldo = new JMenu("Respaldo");
		menuBar.add(mnRespaldo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Respaldo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					  File file = new File("main.dat");	
					  
					  DataOutputStream dataOutputStream = null;
					  DataInputStream dataInputStream = null;
					  
					  Socket socket = new Socket("localhost", 9001);
					  
				      dataInputStream = new DataInputStream(socket.getInputStream());
				      dataOutputStream = new DataOutputStream(socket.getOutputStream());
					  
					  
				      int bytes = 0;
				      FileInputStream fileInputStream = new FileInputStream(file);
			        
				      dataOutputStream.writeLong(file.length());  
				      byte[] buffer = new byte[4*1024];
				      while ((bytes = fileInputStream.read(buffer))!= -1){
				    	  dataOutputStream.write(buffer,0,bytes);
				    	  dataOutputStream.flush();
				      }
				      fileInputStream.close();
				  } catch (Exception e2) {
					  System.out.println(e2);
				  }
				
			}
		});
		mnRespaldo.add(mntmNewMenuItem);
		
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
			//mnConsulta.setEnabled(false);
			mnEnfermedad.setEnabled(false);
			mnCita.setEnabled(false);
		}
		//openLogIn();
		setPaciente();

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
		p.setBackground(MyGreen);
	}
	private void removeSetColor(JPanel p) {
		p.setBackground(MyGreen);
	}
	
	private void setPaciente() {
		String temp = ""; 
		for (Usuario aux : Hospital.getInstance().getMisCuentas() ) {
			if (aux instanceof Paciente) {
				temp = aux.getCodigo() +" : "+aux.getNombre();
				cboxHistorialMedico.addItem(temp);
			}
		}
	}
	
	private void setPacienteHistorialMedico(){
		String temp = "";
		if ( paciente.getMiRegistro().getEsPaciente() ) {
			for (Consulta aux : paciente.getMiRegistro().getMisConsultas() ) {
				//temp = aux.getCodigo() + aux.getFecha().getDate().toString(); //aux.getFecha().getYear().toString();
				modalHistorialMedico.addElement(temp);
			}
			for (Cita aux : paciente.getMiRegistro().getMisCitas() ) {
				//temp = aux.getCodigo() + aux.getFechaDeConsulta().getHours()//toString() + " :00";
				modalHistorialMedico.addElement(temp);
			}
		}
		
		paciente.getMiRegistro().getMisConsultas();
		paciente.getMiRegistro().getMisCitas();
	}
}
