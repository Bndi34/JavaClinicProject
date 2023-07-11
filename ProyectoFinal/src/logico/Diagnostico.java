package logico;
import java.util.ArrayList;
import java.util.Date;

public class Diagnostico {
	private String codigo;
	private Date Fecha;
	private Paciente paciente;
	private Doctor Doc;
	private ArrayList<String> Sintomas;
	
	public Diagnostico(String codigo, Date fecha, Paciente paciente, Doctor doc, ArrayList<String> sintomas) {
		super();
		this.codigo = codigo;
		Fecha = fecha;
		this.paciente = paciente;
		Doc = doc;
		Sintomas = sintomas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoc() {
		return Doc;
	}

	public void setDoc(Doctor doc) {
		Doc = doc;
	}

	public ArrayList<String> getSintomas() {
		return Sintomas;
	}

	public void setSintomas(ArrayList<String> sintomas) {
		Sintomas = sintomas;
	}
	
}

