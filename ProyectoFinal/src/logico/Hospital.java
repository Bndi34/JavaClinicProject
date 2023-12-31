package logico;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Hospital implements Serializable{
	
	private static final long serialVersionUID = 5219225790495881149L;
	private ArrayList<Usuario> misCuentas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Enfermedad> enfermedadesReg;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<String>sintomasRegistrados;
	private ArrayList<String>alergiasRegistradas;
	private static Hospital hospi = null;
	
	public static int generadorUsuario = 1;
	public static int generadorConsulta = 1;
	public static int generadorCita = 1;
	public static int generadorEnfermedad = 1;
	public static int generadorVacuna = 1;
	
	public Hospital() {
		super();
		
		misCuentas = new ArrayList<Usuario>();
		misConsultas = new ArrayList<Consulta>();
		misCitas = new ArrayList<Cita>();
		enfermedadesReg = new ArrayList<Enfermedad>();
		misVacunas = new ArrayList<Vacuna>();
		sintomasRegistrados = new ArrayList<String>();
		alergiasRegistradas = new ArrayList<String>();
		
	}
	
	public static Hospital getInstance() {
		if (hospi == null) {
			hospi = new Hospital();
		}
		return hospi;
	}
	
	public ArrayList<Cita> buscarHorasDisponiblesCitas(Date day, Doctor doc) {
		ArrayList<Cita>citaDia = new ArrayList<>();
		//citaDia = null;
		
		for (Cita aux : misCitas) {
			
			
			if (aux.getFechaReal().getDay() == day.getDay() && aux.getFechaReal().getMonth() == day.getMonth() && aux.getFechaReal().getYear() == day.getYear()) {
				if (aux.getDoctor().getCodigo().equalsIgnoreCase(doc.getCodigo()) ) {
					citaDia.add(aux);
				}
			}
		}
		return citaDia;
	}
	
	public ArrayList<Consulta> buscarHorasDisponiblesConsultas(Date day, Doctor doc) {
		ArrayList<Consulta>ConsultaDia;
		ConsultaDia = null;
		
		for (Consulta aux : misConsultas) {
			if (aux.getFecha().getDay() == day.getDay()) {
				if (aux.getDoctor().getCodigo().equalsIgnoreCase(doc.getCodigo()) ) {
					ConsultaDia.add(aux);
				}
			}
		}
		return ConsultaDia;
	}
	public int cantidadDeConsultasPorMes(Date fechaSolicitada) {
		int count = 0;
		for ( Consulta temp : misConsultas){
			if (temp.getFecha().getMonth() == fechaSolicitada.getMonth()) {
				count++;
			}
			
		}
		return count;
	}
	
	public int cantidadDeCitasPorMes(Date fechaSolicitada) {
		int count = 0;
		for ( Cita temp : misCitas){
			if (temp.getFechaReal().getMonth() == fechaSolicitada.getMonth()) {
				count++;
			}
			
		}
		return count;
	}

	public int ContarPacientesConAlergia(String temp) {
		int count = 0;
		for (Usuario aux : misCuentas ) {
			if (aux instanceof Paciente) {
				for ( int i = 0;((Paciente) aux).getMiRegistro().getMisAlergias().size() != i; i++) {
					if ( ((Paciente) aux).getMiRegistro().getMisAlergias().get(i).equalsIgnoreCase(temp) ) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	public int contarVacunaEnRegistro(String temp) {
		int count = 0;
		for (Usuario aux : misCuentas ) {
			if (aux instanceof Paciente) {
				for ( int i = 0;((Paciente) aux).getMiRegistro().getTotalDeVacunasColocadas().size() != i; i++) {
					if ( ((Paciente) aux).getMiRegistro().getTotalDeVacunasColocadas().get(i).getCodigo().equalsIgnoreCase(temp) ) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	
	public ArrayList<Usuario> getMisCuentas() {
		return misCuentas;
	}
	public void setMisCuentas(ArrayList<Usuario> misCuentas) {
		this.misCuentas = misCuentas;
	}
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}
	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}
	public ArrayList<Enfermedad> getEnfermedadesReg() {
		return enfermedadesReg;
	}
	public void setEnfermedadesReg(ArrayList<Enfermedad> enfermedadesReg) {
		this.enfermedadesReg = enfermedadesReg;
	}
	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public ArrayList<String> getSintomasRegistrados() {
		return sintomasRegistrados;
	}

	public void setSintomasRegistrados(ArrayList<String> sintomasRegistrados) {
		this.sintomasRegistrados = sintomasRegistrados;
	}
	
	public ArrayList<String> getAlergiasRegistradas() {
		return alergiasRegistradas;
	}

	public void setAlergiasRegistradas(ArrayList<String> alergiasRegistrados) {
		this.alergiasRegistradas = alergiasRegistrados;
	}

	//Parte de insertar
	public void insertarUsuario(Usuario aux){
		
		misCuentas.add(aux);
		generadorUsuario++;
	}
	
	public void insertarConsulta(Consulta aux){
		misConsultas.add(aux);
		generadorConsulta++;
	}
	
	public void insertarCita(Cita aux){
		System.out.println(aux);
		misCitas.add(aux);
		generadorCita++;
	}
	
	public void insertarEnfermedad(Enfermedad aux){
		enfermedadesReg.add(aux);
		generadorEnfermedad++;
	}
	
	public void insertarVacuna(Vacuna aux){
		misVacunas.add(aux);
		generadorVacuna++;
	}
	
	
	public boolean adminFound()
	{
		for (Usuario aux : misCuentas) {
			if (aux instanceof Admin) {
				return true;
			}
		}
		return false;
	}
	
	/*public Admin buscarAdminByUser(String user)
	{
		Admin temp = null;
		for (Admin aux : misAdmins) {
			if (aux.getNombre().equalsIgnoreCase(user)) {
				temp = aux;
			}
		}
		return temp;
	}*/
	
	public Usuario buscarUsuarioByCedula(String cod) {
		Usuario temp = null;
		for (Usuario aux : misCuentas) {
			if (aux.getCedula().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	//Buscar by code
	public Usuario buscarUsuarioByCode(String cod) {
		Usuario temp = null;
		for (Usuario aux : misCuentas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	
	public Consulta buscarConsultasByCode(String cod) {
		Consulta temp = null;
		for (Consulta aux : misConsultas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	public Cita buscarCitasByCode(String cod) {
		Cita temp = null;
		for (Cita aux : misCitas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	public Enfermedad buscarEnfermedadesByCode(String cod) {
		Enfermedad temp = null;
		for (Enfermedad aux : enfermedadesReg) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	public Vacuna buscarVacunasByCode(String cod) {
		Vacuna temp = null;
		for (Vacuna aux : misVacunas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	//buscarIndex
	public int buscarIndexByUsuario(String codigo) {
		int aux = -1;
		int i = 0;
		boolean encontrado = false;
		while(i<generadorUsuario-1 && !encontrado){
			if(misCuentas.get(i).getCodigo().equalsIgnoreCase(codigo)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public int buscarIndexByConsulta(String codigo) {
		int aux = -1;
		int i = 0;
		boolean encontrado = false;
		while(i<generadorConsulta-1 && !encontrado){
			if(misConsultas.get(i).getCodigo().equalsIgnoreCase(codigo)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public int buscarIndexByCita(String codigo) {
		int aux = -1;
		int i = 0;
		boolean encontrado = false;
		while(i<generadorCita-1 && !encontrado){
			if(misCitas.get(i).getCodigo().equalsIgnoreCase(codigo)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public int buscarIndexByEnfermedad(String codigo) {
		int aux = -1;
		int i = 0;
		boolean encontrado = false;
		while(i<generadorEnfermedad-1 && !encontrado){
			if(enfermedadesReg.get(i).getCodigo().equalsIgnoreCase(codigo)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public int buscarIndexByVacuna(String codigo) {
		int aux = -1;
		int i = 0;
		boolean encontrado = false;
		while(i<generadorVacuna-1 && !encontrado){
			if(misVacunas.get(i).getCodigo().equalsIgnoreCase(codigo)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	//Modificar
	public void modificarUsuario(Usuario selected) {
		int index = -1;
		index = buscarIndexByUsuario(selected.getCodigo());
		misCuentas.set(index, selected);
	}
	
	public void modificarConsulta(Consulta selected) {
		int index = -1;
		index = buscarIndexByConsulta(selected.getCodigo());
		misConsultas.set(index, selected);
	}
	
	public void modificarCita(Cita selected) {
		int index = -1;
		index = buscarIndexByCita(selected.getCodigo());
		misCitas.set(index, selected);
	}
	
	public void modificarEnfermedad(Enfermedad selected) {
		int index = -1;
		index = buscarIndexByEnfermedad(selected.getCodigo());
		enfermedadesReg.set(index, selected);
	}  
	
	public void modificarVacuna(Vacuna selected) {
		int index = -1;
		index = buscarIndexByVacuna(selected.getCodigo());
		misVacunas.set(index, selected);
	}
	
	public static void load() throws IOException, ClassNotFoundException {
		Hospital.getInstance();
		File file = new File("main.dat");	
		file.createNewFile(); 
		try {		
			
			FileInputStream f = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(f);
			Hospital temp = (Hospital) oos.readObject();
			hospi = temp;
			generadorEnfermedad = hospi.getEnfermedadesReg().size() + 1;
			generadorUsuario = hospi.getMisCuentas().size()+1;
			generadorConsulta = hospi.getMisConsultas().size()+1;
			generadorCita = hospi.getMisCitas().size()+1;
			generadorVacuna = hospi.getMisVacunas().size()+1;
			oos.close();
		} catch (EOFException e) {
		}	
		
	}
	
	public static void save() throws IOException, ClassNotFoundException {
		getInstance();
    	File file = new File("main.dat");
    
    	file.createNewFile();
    	FileOutputStream f = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(f);
    	oos.writeObject(hospi);
    	oos.close();
	}

}
