package logico;

public class Doctor extends Usuario {
	
	private float sueldo;
	private String areaMedica;
	
	public Doctor(String codigo, String nombre, String telefono,float sueldo, String areaMedica) {
		super(codigo, nombre, telefono);
		this.sueldo = sueldo;
		this.areaMedica = areaMedica;
	}

	public float getSueldo() {
		return sueldo;
	}


	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}


	public String getAreaMedica() {
		return areaMedica;
	}


	public void setAreaMedica(String areaMedica) {
		this.areaMedica = areaMedica;
	}

}

