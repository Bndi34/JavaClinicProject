package logico;

public class Secretaria extends Usuario{

	private static final long serialVersionUID = 1L;
	private Doctor dependiente;
	
	public Secretaria(String codigo, String nombre, String telefono, Doctor dependiente) {
		super(codigo, nombre, telefono);
		
		this.dependiente = dependiente;
	}

	public Doctor getDependiente() {
		return dependiente;
	}

	public void setDependiente(Doctor dependiente) {
		this.dependiente = dependiente;
	}
}