package logico;
import java.util.ArrayList;

public class Paciente extends Usuario {

	private static final long serialVersionUID = 1L;
	private boolean esPaciente;
	private char sexo;
	private String direccion;
	private RegistroMedico miRegistro;
	
	public Paciente(String codigo, String nombre, String cedula, String telefono, String contrasenia,  
					String direccion, boolean esPaciente, char sexo,RegistroMedico miRegistro) {
		super(codigo, nombre, cedula, telefono,contrasenia);
		this.esPaciente = esPaciente;
		this.sexo = sexo;
		this.direccion = direccion;
		this.miRegistro = miRegistro;
	}

	public boolean isEsPaciente() {
		return esPaciente;
	}
	public void setEsPaciente(boolean esPaciente) {
		this.esPaciente = esPaciente;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public RegistroMedico getMiRegistro() {
		return miRegistro;
	}

	public void setMiRegistro(RegistroMedico miRegistro) {
		this.miRegistro = miRegistro;
	}
}
//Esta clase es equivalente a la de cliente en el UML
//Al profe no le gusto que la clase se llamase ciente
//Paciente suena mejor, pero nocreo que sea lo suficientemente descriptivo


