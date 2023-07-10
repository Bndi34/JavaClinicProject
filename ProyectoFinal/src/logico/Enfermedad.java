
package logico;

public class Enfermedad {

	private String codigo;
	private String nombre;
	private ArrayList<String>Sintomas;
	//private String tipo;
	private boolean curaEncontrada;
	
	public Enfermedad(String codigo, String nombre, ArrayList<String> sintomas, boolean curaEncontrada) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		Sintomas = sintomas;
		this.curaEncontrada = curaEncontrada;
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
	public ArrayList<String> getSintomas() {
		return Sintomas;
	}
	public void setSintomas(ArrayList<String> sintomas) {
		Sintomas = sintomas;
	}
	public boolean isCuraEncontrada() {
		return curaEncontrada;
	}
	public void setCuraEncontrada(boolean curaEncontrada) {
		this.curaEncontrada = curaEncontrada;
	}
}
