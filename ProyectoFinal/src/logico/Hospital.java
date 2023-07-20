package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

//Hay que poner que solo se pueda de una instancia a la vez
public class Hospital {
	private ArrayList<Usuario> misCuentas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Diagnostico> misDiagnosticos;
	private ArrayList<Cita> misCitas;
	private ArrayList<Enfermedad> enfermedadesReg;
	private ArrayList<Vacuna> misVacunas;
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
		misDiagnosticos = new ArrayList<Diagnostico>();
		misCitas = new ArrayList<Cita>();
		enfermedadesReg = new ArrayList<Enfermedad>();
		misVacunas = new ArrayList<Vacuna>();
		
	}
	
	public static Hospital getInstance() {
		if (hospi == null) {
			hospi = new Hospital();
		}
		return hospi;
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
	
	public float porcentajeEfermedadPorPaciente(String codigoEnfermedad){
		float porcentaje = 0;
		
		//for ()
		for (Usuario aux : misCuentas) {
			if (aux instanceof Paciente) {
				
			}
		}
		
		
		return porcentaje;
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
	
	/*public static ArrayList<Diagnostico> buscarHorasDisponiblesDiagnostico(Date day, Doctor doc) {
		ArrayList<Diagnostico>diagnosticoDia;
		
		for (Diagnostico aux : misDiagnosticos) {
			if (aux.getFecha().getDay() == day.getDay()) {
				if (aux.getDoc().getCodigo().equalsIgnoreCase(doc.getCodigo()) ) {
					diagnosticoDia.add(aux);
				}
			}
		}
		return diagnosticoDia;
	}
	
	public static ArrayList<Consulta> buscarHorasDisponiblesConsulta(Date day, Doctor doc) {
		ArrayList<Consulta>ConsultaDia;
		
		for (Consulta aux : misConsultas) {
			if (aux.getFecha().getDay() == day.getDay()) {
				if (aux.getDoctor().getCodigo().equalsIgnoreCase(doc.getCodigo()) ) {
					ConsultaDia.add(aux);
				}
			}
		}
		return ConsultaDia;
	}*/
	
	//Buscar by code
	Usuario buscarUsuarioByCode(String cod) {
		Usuario temp = null;
		for (Usuario aux : misCuentas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	
	Consulta buscarConsultasByCode(String cod) {
		Consulta temp = null;
		for (Consulta aux : misConsultas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	Cita buscarCitasByCode(String cod) {
		Cita temp = null;
		for (Cita aux : misCitas) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	Enfermedad buscarEnfermedadesByCode(String cod) {
		Enfermedad temp = null;
		for (Enfermedad aux : enfermedadesReg) {
			if (aux.getCodigo().equalsIgnoreCase(cod)) {
				temp = aux;
			}
		}
		return temp;
	}
	
	Vacuna buscarVacunasByCode(String cod) {
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
	
	/*public void loadUser() {
    	File file = new File("misUsers.dat");
    	FileInputStream f = new FileInputStream(file);
    	ObjectInputStream oos = new ObjectInputStream(f);
    	
    	int size = oos.readInt();
    	for (int i =0; i < size; i++) {
    	Usuario aux = (aux)oos.readObject();
    	misCuentas.add(aux);
    	}
    	
    	
	}*/
	
	
	
}
