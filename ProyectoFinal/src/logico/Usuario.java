
package logico;

public abstract class Ususario {
	private String codigo;
	private String nombre;
	private String telefono;
	
	public Ususario(String codigo, String nombre, String telefono) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
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