package logico;

public class Doctor extends Usuario {
	
	private static final long serialVersionUID = 1L;
	private String areaMedica;
	
	public Doctor(String codigo, String nombre, String cedula, String telefono, String contrasenia, String areaMedica) {
		super(codigo, nombre, cedula, telefono ,contrasenia);
		this.areaMedica = areaMedica;
	}

	public String getAreaMedica() {
		return areaMedica;
	}


	public void setAreaMedica(String areaMedica) {
		this.areaMedica = areaMedica;
	}

}

