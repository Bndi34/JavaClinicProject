package logico;
import java.util.ArrayList;

public class Paciente extends Usuario {
	private static final long serialVersionUID = 1L;
	private String estado;
	private boolean esPaciente;
	private char sexo;
	private ArrayList<String> Alergias;
	private String direccion;
	
	public Paciente(String codigo, String nombre, String cedula, String telefono, String contrasenia,  
					String direccion, String estado, boolean esPaciente, char sexo, ArrayList<String> alergias) {
		super(codigo, nombre, cedula, contrasenia, telefono);
		this.estado = estado;
		this.esPaciente = esPaciente;
		this.sexo = sexo;
		Alergias = alergias;
		this.direccion = direccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public ArrayList<String> getAlergias() {
		return Alergias;
	}
	public void setAlergias(ArrayList<String> alergias) {
		Alergias = alergias;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
//Esta clase es equivalente a la de cliente en el UML
//Al profe no le gusto que la clase se llamase ciente
//Paciente suena mejor, pero nocreo que sea lo suficientemente descriptivo


