package microservicioViajes.modelo;

import java.sql.Date;
import java.sql.Time;
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
	private Date fechaInicio;

	@Column
	private Date fechaFin;

	// horas del viaje
	@Column
	private Time horaInicio;

	@Column
	private Time horaFin;
	
	// id del monopatin utilizado
	@Column
	private float kmRecorridos;

	// constructor vacio
	public viaje() {
	}

	public viaje(int idUsuario, int idParadaInicio, int idMonopatin,Date diaInicio,Time horainicio) {
		this.idUsuario = idUsuario;
		this.idParadaInicio = idParadaInicio;
		this.idMonopatin = idMonopatin;
		this.fechaInicio = diaInicio;
		this.horaInicio = horainicio;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return "viaje [id=" + id + ", idUsuario=" + idUsuario + ", idParadaInicio=" + idParadaInicio + ", idParadaFin="
				+ idParadaFin + ", idMonopatin=" + idMonopatin + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + "]";
	}
}
