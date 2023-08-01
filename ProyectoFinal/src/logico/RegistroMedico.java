package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class RegistroMedico implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esPaciente;
	private ArrayList<Cita>misCitas;
	private ArrayList<Consulta>misConsultas;
	private ArrayList<Vacuna>TotalDeVacunasColocadas;
	
	public RegistroMedico(boolean esPaciente, ArrayList<Cita> misCitas, ArrayList<Consulta> misConsultas,
			ArrayList<Vacuna> totalDeVacunasColocadas) {
		super();
		this.esPaciente = esPaciente;
		this.misCitas = misCitas;
		this.misConsultas = misConsultas;
		TotalDeVacunasColocadas = totalDeVacunasColocadas;
	}

	public boolean getEsPaciente() {
		return esPaciente;
	}

	public void setEsPaciente(boolean esPaciente) {
		this.esPaciente = esPaciente;
	}

	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public ArrayList<Vacuna> getTotalDeVacunasColocadas() {
		return TotalDeVacunasColocadas;
	}

	public void setTotalDeVacunasColocadas(ArrayList<Vacuna> totalDeVacunasColocadas) {
		TotalDeVacunasColocadas = totalDeVacunasColocadas;
	}
}
