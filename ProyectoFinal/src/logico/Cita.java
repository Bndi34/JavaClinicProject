package logico;
import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable{//
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String estado;
	private Date fechaOriginal;
	private Date fechaReal;
	private int hora;
	private Doctor doctor;
	private Paciente paciente;
	
	public Cita(String codigo, String estado, Date fechaDeConsulta, int hora, Doctor doctor, Paciente paciente) {
		super();
		this.codigo = codigo;
		this.estado = estado;
		this.fechaOriginal = fechaDeConsulta;
		this.fechaReal = fechaDeConsulta;
		this.hora = hora;
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
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaOriginal(){
		return fechaOriginal;
	}
	
	public Date getFechaReal() {
		return fechaReal;
	}

	public void setFechaReal(Date fechaDeConsulta) {
		this.fechaReal = fechaDeConsulta;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
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