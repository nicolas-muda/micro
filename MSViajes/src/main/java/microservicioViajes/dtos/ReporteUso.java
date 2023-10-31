package microservicioViajes.dtos;

public class ReporteUso {
	private int idMonopatin;
	private int duracion;
	private double kilometros;

	public ReporteUso(int idMonopatin, int duracion) {
		this.idMonopatin = idMonopatin;
		this.duracion = duracion;
		this.kilometros = 0;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(int idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getKilometros() {
		return kilometros;
	}

	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}

	public void agregarDuracion(int minutosEnIntervalo) {
		this.duracion += minutosEnIntervalo;
	}
}
