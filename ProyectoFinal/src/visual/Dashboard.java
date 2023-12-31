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
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import com.sun.xml.internal.ws.api.Component;
import com.toedter.calendar.JCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import logico.Admin;
import logico.Cita;
import logico.Consulta;
import logico.Doctor;
import logico.Enfermedad;
import logico.Hospital;
import logico.Paciente;
import logico.Secretaria;
import logico.Usuario;
import logico.Vacuna;
import visualListar.ListarCompromiso;
import visualListar.ListarCuenta;
import visualListar.ListarEnfermedad;
import visualListar.ListarVacuna;
import visualRegistros.RegistrarCita;
import visualRegistros.RegistrarConsulta;
import visualRegistros.RegistrarEnfermedad;
import visualRegistros.RegistrarUsuario;
import visualRegistros.RegistrarVacuna;

import java.awt.Color;
import java.awt.Dimension;

import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.UIManager;
import javax.swing.JList;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Dashboard extends JFrame {

	private final JPanel contentPanel = new JPanel();
	JPanel dashboardCalender;
	JPanel dashboardStads;
	static JPanel panelAlergiasComunes;
	JComboBox cboxHistorialMedico;
	private DefaultListModel<String> modalHistorialMedico;
	JRadioButton radioCalendar;
	JRadioButton radioDashStads;
	private JMenu mnUser;
	private JTextField txtSupervisor;
	private DefaultPieDataset pieDataSet;
	private JFreeChart Chart;
	private PiePlot piePlot;
	private Date fechaActual;
	//private ChartPanel ChartPanel;
	
    private static DataOutputStream outStream = null;
    private static DataInputStream inputSteam = null;
	private Color MyBlue = 	new Color(205, 133, 63);
	private Color myCalendarColorLight = new Color(255, 228, 196);
	private Color myCalendarBGColor = new Color(245, 245, 220);
	private Color myCalendarColorDark = new Color(205, 133, 63);
	private Paciente paciente;
	private Dimension dim = null;
	private Usuario cuentaUsuario;
	private JCalendar calendar;
	private JList<String> listHorasDelDia;
	private JScrollPane scrllDetallesDia;

	private JPanel PanelAlergia;
	private JScrollPane scrollPaneAlergia;
	private DefaultListModel<String> modelAlergia = new DefaultListModel<String>();
	
	/**
	 * Create the dialog.
	 */
	public Dashboard(final Usuario Cuenta) {
		setResizable(false);
		
		cuentaUsuario = Cuenta;
		
		modalHistorialMedico = new DefaultListModel<String>();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Centro M\u00E9dico");
		
		setBounds(100, 100, 1304, 690);
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
		Dashboard.setBackground(new Color(255, 255, 255));
		Dashboard.setForeground(new Color(0, 0, 0));
		Dashboard.setFont(new Font("Impact", Font.PLAIN, 33));
		Dashboard.setBounds(12, 6, 217, 54);
		contentPanel.add(Dashboard);

		radioCalendar = new JRadioButton("Calendario");
		radioCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioCalendar.isSelected()) {
					dashboardCalender.setVisible(true);
					dashboardStads.setVisible(false);
					radioDashStads.setSelected(false);
				}
			}
		});
		radioCalendar.setBackground(myCalendarBGColor);
		radioCalendar.setBounds(234, 25, 166, 35);
		contentPanel.add(radioCalendar);

		radioDashStads = new JRadioButton("Estad\u00EDsticas");
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
		radioDashStads.setBackground(new Color(216, 191, 216));
		radioDashStads.setBounds(420, 25, 189, 35);
		contentPanel.add(radioDashStads);
		
		dashboardStads = new JPanel();
		dashboardStads.setBounds(0, 60, 1264, 580);
		dashboardStads.setBackground(new Color(216, 191, 216));
		contentPanel.add(dashboardStads);
		dashboardStads.setLayout(null);
		
		dashboardCalender = new JPanel();
		dashboardCalender.setBackground(myCalendarBGColor);
		dashboardCalender.setBounds(0, 60, 1413, 580);
		contentPanel.add(dashboardCalender);
		dashboardCalender.setLayout(null);
		dashboardCalender.setVisible(false);
		
		panelAlergiasComunes = new JPanel();
		panelAlergiasComunes.setBounds(23, 81, 416, 417);
		panelAlergiasComunes.setLayout(null);
		dashboardStads.add(panelAlergiasComunes);

		DefaultPieDataset dataset = new  DefaultPieDataset();
		
		for (String aux : Hospital.getInstance().getAlergiasRegistradas()){ //Hospital.getInstance().getMisCuentas()) {
			dataset.setValue(aux, Hospital.getInstance().ContarPacientesConAlergia(aux) );
			System.out.print("Alergia:"+aux);
		}
		JFreeChart chart = ChartFactory.createPieChart("Alergias m\u00E1s comunes", dataset);
		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.setBackground(myCalendarColorLight);
		chartpanel.setPreferredSize(new Dimension(430,400));
		chartpanel.setBounds(0,0,416,417);
		
		panelAlergiasComunes.add(chartpanel);
		
		
		JPanel panelVacunasMasUtilizadas = new JPanel();
		panelVacunasMasUtilizadas.setBounds(491, 81, 724, 417);
		dashboardStads.add(panelVacunasMasUtilizadas);
		
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		
		//Set keys = HashMap.KeySet();
		for (Vacuna aux : Hospital.getInstance().getMisVacunas() ) {
			dcd.setValue( Hospital.getInstance().contarVacunaEnRegistro(aux.getCodigo()), aux.getCodigo(),"");
		}
		
		JFreeChart jchart = ChartFactory.createBarChart("Vacunas m\u00E1s utilizadas", "C\u00F3digo de la vacuna", "Total de veces", dcd);
		CategoryPlot plot = jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		panelVacunasMasUtilizadas.setLayout(null);
		
		ChartPanel chartpnl = new ChartPanel(jchart);
		chartpnl.setBounds(0, 0, 724, 417);
		panelVacunasMasUtilizadas.add(chartpnl);
        
		JLabel lblCalendario = new JLabel("Calendario");
		lblCalendario.setForeground(myCalendarColorDark);
		lblCalendario.setFont(new Font("Poor Richard", Font.BOLD, 43));
		lblCalendario.setBounds(124, 23, 288, 43);
		dashboardCalender.add(lblCalendario);

		JLabel lblHistorialMedico = new JLabel("Historial Medico");
		
		lblHistorialMedico.setFont(new Font("Poor Richard", Font.BOLD, 26));
		lblHistorialMedico.setForeground(myCalendarColorDark);
		lblHistorialMedico.setBounds(983, 36, 203, 43);
		dashboardCalender.add(lblHistorialMedico);
		
		JLabel label = new JLabel("Horario del D\u00EDa");
		label.setForeground(myCalendarColorDark);
		label.setFont(new Font("Poor Richard", Font.BOLD, 26));
		label.setBounds(749, 36, 198, 43);
		dashboardCalender.add(label);


		JPanel panel_calendar = new JPanel();
		panel_calendar.setBackground(myCalendarColorLight);
		//panel_calendar.setBackground(MyBlue);
		panel_calendar.setBounds(22, 80, 707, 420);
		dashboardCalender.add(panel_calendar);
		panel_calendar.setLayout(null);

		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				setHorarioPendiente();
			}
		});
		
		calendar.setBounds(10, 11, 685, 390);
		panel_calendar.add(calendar);

		JPanel panel_calenderSecundario = new JPanel();
		panel_calenderSecundario.setForeground(new Color(255, 228, 196));
		panel_calenderSecundario.setBackground(new Color(245, 245, 220));
		panel_calenderSecundario.setBounds(741, 80, 512, 420);

		LineBorder border = new LineBorder(new Color(255, 228, 196),13);
		panel_calenderSecundario.setBorder(border);
		dashboardCalender.add(panel_calenderSecundario);
		panel_calenderSecundario.setLayout(null);

		JPanel panel_horarioDIa = new JPanel();
		panel_horarioDIa.setBounds(12, 17, 189, 383);

		panel_calenderSecundario.add(panel_horarioDIa);
		panel_horarioDIa.setLayout(null);

		scrllDetallesDia = new JScrollPane();
		scrllDetallesDia.setBounds(0, 0, 189, 383);
		panel_horarioDIa.add(scrllDetallesDia);

		DefaultListModel<String> model = new DefaultListModel<>();
		listHorasDelDia = new JList<>( model );
		setHorarioPendiente();
		
		
		PanelAlergia = new JPanel();
		PanelAlergia.setBounds(213, 56, 260, 344);
		panel_calenderSecundario.add(PanelAlergia);
		PanelAlergia.setLayout(null);
		
		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(0, 0, 258, 344);
		PanelAlergia.add(scrollPaneAlergia);
		
		JList listAlergia = new JList();
		listAlergia.setModel(modelAlergia);
		scrollPaneAlergia.setRowHeaderView(listAlergia);
		
		setPacienteHistorialMedico();
		
		cboxHistorialMedico = new JComboBox();
		cboxHistorialMedico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboxHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if () {
				try {
					int indf = cboxHistorialMedico.getSelectedItem().toString().indexOf(":");
					paciente = (Paciente) Hospital.getInstance().buscarUsuarioByCode(cboxHistorialMedico.getSelectedItem().toString().substring(0, indf-1));

					setPacienteHistorialMedico();
				} catch (NullPointerException e2) {
					// TODO: handle exception
				}
					
					//}
			}
		});
		cboxHistorialMedico.setBackground(myCalendarColorDark);
		cboxHistorialMedico.setBounds(238, 17, 196, 26);
		cboxHistorialMedico.setBorder(null); // new Color(51, 153, 102)
		panel_calenderSecundario.add(cboxHistorialMedico);
		cboxHistorialMedico.setModel(new DefaultComboBoxModel(new String[] {"<Paciente>"}));

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
				
				setHorarioPendiente();
			}
		});
		mnCita.add(mntmRegistrarCita);

		JMenuItem mntmListarCita = new JMenuItem("Listar Citas");
		mntmListarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCompromiso listCita = new ListarCompromiso("cita");
				listCita.setVisible(true);
			
				setHorarioPendiente();
			}
		});
		mnCita.add(mntmListarCita);

		JMenuItem mntmListarConsultas = new JMenuItem("Listar Consultas");
		mntmListarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCompromiso listarConsulta = new ListarCompromiso("consulta");
				listarConsulta.setVisible(true);
				
				setHorarioPendiente();
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
		mntmListarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedad listEnf =  new ListarEnfermedad(true);
				listEnf.setVisible(true);
			}
		});
		mnEnfermedad.add(mntmListarEnfermedad);

		JMenu mnVacuna = new JMenu("Vacuna");
		menuBar.add(mnVacuna);

		JMenuItem mntmRegistrarVacuna = new JMenuItem("Registrar");
		mntmRegistrarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVacuna regVac = new RegistrarVacuna(null); //("Registrar Usuario",null);
				regVac.setModal(true);
				regVac.setVisible(true);
			}
		});
		mnVacuna.add(mntmRegistrarVacuna);

		JMenuItem mntmListarVacuna = new JMenuItem("Listar");
		mntmListarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVacuna listVac = new ListarVacuna(true);
				listVac.setVisible(true);
			}
		});
		mnVacuna.add(mntmListarVacuna);

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
		setHorarioPendiente();

	}
	
	private void openLogIn()
	{
		Usuario cuenta = null;
		Login login = new Login();
		login.setVisible(true);
		
		
		/*
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
		}*/
		
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
		try {
			modelAlergia.removeAllElements();
			
			if ( paciente.getMiRegistro().getEsPaciente() ) {
				
				for (String auxString : paciente.getMiRegistro().getMisAlergias())
				{
					temp = "Alergia: " + auxString; //aux.getFecha().getYear().toString();
					modelAlergia.addElement(temp);
				}
				for (Vacuna auxString : paciente.getMiRegistro().getTotalDeVacunasColocadas())
				{
					modelAlergia.addElement(temp);
					temp = "Vacuna C�digo: " + auxString.getCodigo() + " Colocada"; //aux.getFecha().getYear().toString();
				}
				for (Consulta aux : paciente.getMiRegistro().getMisConsultas() ) {
					Integer tempInteger = aux.getFecha().getDate();
					temp = "Consulta: " + aux.getCodigo() + " -- " + aux.getFecha(); //aux.getFecha().getYear().toString();
					modelAlergia.addElement(temp);
				}
				/*
				for (Cita aux : paciente.getMiRegistro().getMisCitas() ) {
					Integer tempInteger = aux.getFechaReal().getHours();
					temp = "Cita: " + aux.getCodigo() + " -- " + tempInteger.toString() + " :00";
					modelAlergia.addElement(temp);
				}*/
			}
			
			//paciente.getMiRegistro().getMisConsultas();
			//paciente.getMiRegistro().getMisCitas();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
	}
	
	
	private void setHorarioPendiente()
	{
		System.out.println("Cargar Horario IN");
		
		listHorasDelDia.removeAll();
		
		
		boolean isAdmin = false;
		Doctor dependienteSecre = null;
		
		if (cuentaUsuario instanceof Admin)
		{
			isAdmin = true;
		}
		else if (cuentaUsuario instanceof Secretaria)
		{
			dependienteSecre = ((Secretaria) cuentaUsuario).getDependiente();
		}
		
		DefaultListModel<String> model = new DefaultListModel<>();
;
		System.out.println("Model size "+model.getSize());
		System.out.println("Cuenta = " + cuentaUsuario);
		for(Cita auxCita : Hospital.getInstance().getMisCitas())
		{
			
			
			if (auxCita.getDoctor().getCodigo().equalsIgnoreCase(cuentaUsuario.getCodigo()) || auxCita.getPaciente().getCodigo().equalsIgnoreCase(cuentaUsuario.getCodigo()) || isAdmin)
			{
			
				if (auxCita.getFechaReal().getDay() == calendar.getDate().getDay() && auxCita.getFechaReal().getMonth() == calendar.getDate().getMonth() && auxCita.getFechaReal().getYear() == calendar.getDate().getYear())
				{
					if (cuentaUsuario instanceof Paciente)
					{
						model.addElement(auxCita.getCodigo() + " : " + auxCita.getDoctor().getNombre()+ " : " +  auxCita.getHora()+ ":00");
						
					}
					else if (cuentaUsuario instanceof Doctor)
					{
						model.addElement(auxCita.getCodigo() + " : " + auxCita.getPaciente().getNombre() + " " + auxCita.getHora()+ ":00");

					}
					
					else if (cuentaUsuario instanceof Admin) {
						
						model.addElement(auxCita.getCodigo() + " : " + auxCita.getPaciente().getNombre() + " : "+ auxCita.getDoctor().getNombre() + " : " + auxCita.getHora()+ ":00");
						}
					
				}
			}
			else 
			{
				try {
					if (auxCita.getDoctor().getCodigo().equalsIgnoreCase(dependienteSecre.getCodigo()))
					{
						if (auxCita.getFechaReal().getDay() == calendar.getDate().getDay() && auxCita.getFechaReal().getMonth() == calendar.getDate().getMonth() && auxCita.getFechaReal().getYear() == calendar.getDate().getYear())
						{
							model.addElement(auxCita.getCodigo() + " : " + auxCita.getPaciente().getNombre() + " : "+ auxCita.getDoctor().getNombre() + " : " + auxCita.getHora()+ ":00");

						}
							
					}
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
		}
		listHorasDelDia = new JList<>( model );
		listHorasDelDia.setValueIsAdjusting(true);
		listHorasDelDia.setVisibleRowCount(22);
		
		scrllDetallesDia.add(listHorasDelDia);
		listHorasDelDia.setBackground(myCalendarColorLight);
		listHorasDelDia.setFont(new Font("Tahoma", Font.BOLD, 11));
		listHorasDelDia.setForeground(myCalendarColorDark);
		scrllDetallesDia.setColumnHeaderView(listHorasDelDia);
	}
}

