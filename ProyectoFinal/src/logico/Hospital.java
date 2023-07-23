package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

//Hay que poner que solo se pueda de una instancia a la vez
public class Hospital {
	
	private ArrayList<Admin> misAdmins;
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
		
		misAdmins = new ArrayList<Admin>();
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
	
	public Admin buscarAdminByUser(String user)
	{
		Admin temp = null;
		for (Admin aux : misAdmins) {
			if (aux.getUsuario().equalsIgnoreCase(user)) {
				temp = aux;
			}
		}
		return temp;
	}
	
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

	public ArrayList<Admin> getMisAdmins() {
		return misAdmins;
	}

	public void setMisAdmins(ArrayList<Admin> misAdmins) {
		this.misAdmins = misAdmins;
	}

	public ArrayList<Diagnostico> getMisDiagnosticos() {
		return misDiagnosticos;
	}

	public void setMisDiagnosticos(ArrayList<Diagnostico> misDiagnosticos) {
		this.misDiagnosticos = misDiagnosticos;
	}
	
	//Leer ficheros
	public void loadUser() throws IOException, ClassNotFoundException{
		File file = new File("misUsers.dat");
	   	if( file.exists()) {
	    	FileInputStream f = new FileInputStream(file);
	    	ObjectInputStream oos = new ObjectInputStream(f);
	    	
	    	generadorUsuario = oos.readInt();
	    	for (int i =0; i < generadorUsuario; i++) {
		    	Usuario temp = (Usuario)oos.readObject();
		    	misCuentas.add(temp);
	    	}
	    	oos.close();
    	}
	}
	
	public void loadConsulta() throws IOException, ClassNotFoundException{
    	File file = new File("misConsultas.dat");
    	if( file.exists()) {
	    	FileInputStream f = new FileInputStream(file);
	    	ObjectInputStream oos = new ObjectInputStream(f);
	    	
	    	generadorConsulta = oos.readInt();
	    	for (int i =0; i < generadorConsulta; i++) {
	    		Consulta temp = (Consulta)oos.readObject();
		    	misConsultas.add(temp);
	    	}
	    	oos.close();
    	}
    }
	
	public void loadCita() throws IOException, ClassNotFoundException{
    	File file = new File("misCitas.dat");
    	if( file.exists()) {
	    	FileInputStream f = new FileInputStream(file);
	    	ObjectInputStream oos = new ObjectInputStream(f);
	    	
	    	generadorCita = oos.readInt();
	    	for (int i =0; i < generadorCita; i++) {
	    		Cita temp = (Cita)oos.readObject();
		    	misCitas.add(temp);
	    	}
	    	oos.close();
    	}
    }
	
	public void loadeEfermedad() throws IOException, ClassNotFoundException{
    	File file = new File("misEfermedades.dat");
    	if( file.exists()) {
	    	FileInputStream f = new FileInputStream(file);
	    	ObjectInputStream oos = new ObjectInputStream(f);
	    	
	    	generadorEnfermedad = oos.readInt();
	    	for (int i =0; i < generadorEnfermedad; i++) {
	    		Enfermedad temp = (Enfermedad)oos.readObject();
	    		enfermedadesReg.add(temp);
	    	}
	    	oos.close();
    	}
    }
	
	public void loadeVacuna() throws IOException, ClassNotFoundException{
    	File file = new File("misVacunas.dat");
    	if( file.exists()) {
	    	FileInputStream f = new FileInputStream(file);
	    	ObjectInputStream oos = new ObjectInputStream(f);
	    	
	    	generadorVacuna = oos.readInt();
	    	for (int i =0; i < generadorVacuna; i++) {
	    		Vacuna temp = (Vacuna)oos.readObject();
	    		misVacunas.add(temp);
	    	}
	    	oos.close();
    	}
    	
    }
	
	//Guardar en ficheros
	public void saveUsers() throws IOException, ClassNotFoundException{
    	File file = new File("misCuentas.dat");
    	FileOutputStream f = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(f);
 		oos.writeInt(misCuentas.size());
 		for (Usuario usr : misCuentas) {
				oos.writeObject(usr);
		}
 		oos.close();
	}
	
	public void saveConsultas() throws IOException, ClassNotFoundException{
    	File file = new File("misConsultas.dat");
    	FileOutputStream f = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(f);
 		oos.writeInt(misConsultas.size());
 		for (Consulta consu : misConsultas) {
				oos.writeObject(consu);
		}
 		oos.close();
	}

	public void saveCitas() throws IOException, ClassNotFoundException{
    	File file = new File("misCitas.dat");
    	FileOutputStream f = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(f);
 		oos.writeInt(misCitas.size());
 		for (Cita cita : misCitas) {
				oos.writeObject(cita);
		}
 		oos.close();
	}

	public void saveEnfermedades() throws IOException, ClassNotFoundException{
    	File file = new File("misEfermedades.dat");
    	FileOutputStream f = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(f);
 		oos.writeInt(enfermedadesReg.size());
 		for (Enfermedad enf : enfermedadesReg) {
				oos.writeObject(enf);
		}
 		oos.close();
	}

	public void saveVacunas() throws IOException, ClassNotFoundException{
    	File file = new File("misVacunas.dat");
    	FileOutputStream f = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(f);
 		oos.writeInt(enfermedadesReg.size());
 		for (Vacuna vac : misVacunas) {
				oos.writeObject(vac);
		}
 		oos.close();
	}
	
	
	
}
