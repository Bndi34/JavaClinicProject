package logico;
import java.util.ArrayList;

public class Vacuna {
	private String codigo;
	private ArrayList<Enfermedad>EnfermedadesPrevenidas;
	private ArrayList<String> posiblesAlergias;
	
	public Vacuna(String codigo, ArrayList<Enfermedad> enfermedadesPrevenidas, ArrayList<String> posiblesAlergias) {
		super();
		this.codigo = codigo;
		EnfermedadesPrevenidas = enfermedadesPrevenidas;
		this.posiblesAlergias = posiblesAlergias;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public ArrayList<Enfermedad> getEnfermedadesPrevenidas() {
		return EnfermedadesPrevenidas;
	}
	public void setEnfermedadesPrevenidas(ArrayList<Enfermedad> enfermedadesPrevenidas) {
		EnfermedadesPrevenidas = enfermedadesPrevenidas;
	}
	public ArrayList<String> getPosiblesAlergias() {
		return posiblesAlergias;
	}
	public void setPosiblesAlergias(ArrayList<String> posiblesAlergias) {
		this.posiblesAlergias = posiblesAlergias;
	}
}
