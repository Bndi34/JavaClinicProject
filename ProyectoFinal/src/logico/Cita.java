package logico;
import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable{//
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String Estado;
	private Date fechaDeConsulta; 
	private Date atrasos;
	private Doctor doctor;
	private Paciente paciente;
	
	public Cita(String codigo, String estado, Date fechaDeConsulta, Date atrasos, Doctor doctor,
			Paciente paciente) {
		super();
		this.codigo = codigo;
		Estado = estado;
		this.fechaDeConsulta = fechaDeConsulta;
		this.atrasos = atrasos;
		this.doctor = doctor;
		this.paciente = paciente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Date getFechaDeConsulta() {
		return fechaDeConsulta;
	}

	public void setFechaDeConsulta(Date fechaDeConsulta) {
		this.fechaDeConsulta = fechaDeConsulta;
	}

	public Date getAtrasos() {
		return atrasos;
	}

	public void setAtrasos(Date atrasos) {
		this.atrasos = atrasos;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}