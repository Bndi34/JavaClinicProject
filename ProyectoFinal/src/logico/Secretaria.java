package logico;

public class Secretaria extends Usuario{

	private static final long serialVersionUID = 1L;
	private Doctor dependiente;
	
	public Secretaria(String codigo, String nombre, String cedula, String telefono, String contrasenia, Doctor dependiente) {
		super(codigo, nombre, cedula, contrasenia, telefono);
		
		this.dependiente = dependiente;
	}

	public Doctor getDependiente() {
		return dependiente;
	}

	public void setDependiente(Doctor dependiente) {
		this.dependiente = dependiente;
	}
}