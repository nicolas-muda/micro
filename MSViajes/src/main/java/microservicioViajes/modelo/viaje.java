package microservicioViajes.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class viaje {

	// id viaje
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// id usuario que realizo el viaje
	@Column
	private int idUsuario;

	// id de la parada donde comienza el viaje
	@Column
	private int idParadaInicio;

	// id de la para donde termina el viaje
	@Column
	private int idParadaFin;

	// id del monopatin utilizado
	@Column
	private int idMonopatin;

	// fechas del viaje
	@Column
	private LocalDate fechaInicio;

	@Column
	private LocalDate fechaFin;

	// horas del viaje
	@Column
	private LocalTime horaInicio;

	@Column
	private LocalTime horaFin;

	// constructor vacio
	public viaje() {
	}

	public viaje(int idUsuario, int idParadaInicio, int idMonopatin) {
		this.idUsuario = idUsuario;
		this.idParadaInicio = idParadaInicio;
		this.idMonopatin = idMonopatin;
		this.fechaInicio = LocalDate.now();
		this.horaInicio = LocalTime.now().withNano(0);
	}

	public int getId() {
		return id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getIdParadaInicio() {
		return idParadaInicio;
	}

	public int getIdParadaFin() {
		return idParadaFin;
	}

	public void setIdParadaFin(int idParadaFin) {
		this.idParadaFin = idParadaFin;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return "viaje [id=" + id + ", idUsuario=" + idUsuario + ", idParadaInicio=" + idParadaInicio + ", idParadaFin="
				+ idParadaFin + ", idMonopatin=" + idMonopatin + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + "]";
	}
}
