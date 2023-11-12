package microservicioViajes.modelo;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Configuracion {

	// id viaje
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//	fecha
	@Column
	private Date fechaCambio;

	// hora 
	@Column
	private Time horaCambio;
	
	// ctarifas
	@Column
	private float tarifa1;

	@Column
	private float tarifa2;

	// constructor vacio
	public Configuracion() {
	}

	public Configuracion(float tarifa1,float tarifa2, Date diaCambio,Time horaCambio) {
		this.tarifa1 = tarifa1;
		this.tarifa2 = tarifa2;
		this.fechaCambio = diaCambio;
		this.horaCambio = horaCambio;
	}

	public int getId() {
		return id;
	}

	public Date getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public Time getHoraCambio() {
		return horaCambio;
	}

	public void setHoraCambio(Time horaCambio) {
		this.horaCambio = horaCambio;
	}

	public float getTarifa1() {
		return tarifa1;
	}

	public void setTarifa1(float tarifa1) {
		this.tarifa1 = tarifa1;
	}

	public float getTarifa2() {
		return tarifa2;
	}

	public void setTarifa2(float tarifa2) {
		this.tarifa2 = tarifa2;
	}

	@Override
	public String toString() {
		return "configuracion [id=" + id + ",  fechaCambio=" + fechaCambio + ", horaCambio=" + horaCambio + 
		",  tarifa1=" + tarifa1 +",  tarifa2=" + tarifa2 + "]";
	}
}
