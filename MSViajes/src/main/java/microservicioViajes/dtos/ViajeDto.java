package microservicioViajes.dtos;

import java.sql.Date;
import java.sql.Time;

public class ViajeDto {
	// id usuario que realizo el viaje
	private int idUsuario;

	// id de la parada donde comienza el viaje
	private int idParadaInicio;

	// id del monopatin utilizado
	private int idMonopatin;

	// fechas del viaje
	private Date fechaInicio;

	// horas del viaje
	private Time horaInicio;

	public ViajeDto(int idUsuario, int idParadaInicio, int idMonopatin, Date diaInicio, Time horainicio) {
		this.idUsuario = idUsuario;
		this.idParadaInicio = idParadaInicio;
		this.idMonopatin = idMonopatin;
		this.fechaInicio = diaInicio;
		this.horaInicio = horainicio;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdParadaInicio() {
		return idParadaInicio;
	}

	public void setIdParadaInicio(int idParadaInicio) {
		this.idParadaInicio = idParadaInicio;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(int idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

}
