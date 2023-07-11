package logico;
import java.util.Date;

public class Consulta {
	private String codigo;
	private String estado;
	private Date fecha;
	private Paciente paciente;
	private Doctor doctor;
	private Diagnostico diagnostico;
	
	public Consulta(String codigo, String estado, Date fecha, Paciente paciente, Doctor doctor,
			Diagnostico diagnostico) {
		super();
		this.codigo = codigo;
		this.estado = estado;
		this.fecha = fecha;
		this.paciente = paciente;
		this.doctor = doctor;
		this.diagnostico = diagnostico;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
}

