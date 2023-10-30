package microservicioViajes.dtos;

public class reporteUsoPorKm {
	private int monopatinId;
	private double kilometros;
	
	public reporteUsoPorKm(int monopatinId, double kilometros) {
        this.monopatinId = monopatinId;
        this.kilometros = kilometros;
    }

	public int getMonopatinId() {
		return monopatinId;
	}

	public double getKilometros() {
		return kilometros;
	}
}
