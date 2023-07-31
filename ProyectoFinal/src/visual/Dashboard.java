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
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
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
	private Date fechaActual;
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

	public void start(Stage primayStage) throws Exception{
		
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
 			new PieChart.Data("1", 40),
			new PieChart.Data("2", 40),
			new PieChart.Data("3", 40)
			
		); 
		PieChart pChart = new PieChart(pieData);
		Group root = new Group(pChart);
		Scene scene = new Scene(root,600,400);
		primayStage.setTitle(" xd ");
		primayStage.setScene(scene);
		primayStage.show();
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
