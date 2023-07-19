
package logico;

import java.io.Serializable;

public abstract class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String codigo;
	protected String nombre;
	protected String cedula;
	protected String telefono;
	protected String contrasenia;
	
	public Usuario(String codigo, String nombre, String cedula,String telefono, String contrasenia) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.contrasenia = contrasenia;
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}