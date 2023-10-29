package microservicioViajes.modelo;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pausa {

	// id viaje
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int idViaje;

	@Column
	private Time inicioPausa;
	
	@Column
	private Time finPausa;

	public Pausa() {
	}

	public Pausa(int idViaje,Time inicioPausa) {
	    this.idViaje = idViaje;
	    this.inicioPausa = inicioPausa; 
	}

	public int getId() {
		return id;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public Time getInicioPausa() {
	    return inicioPausa;
	}

	public void setInicioPausa(Time inicioPausa) {
		this.inicioPausa = inicioPausa;
	}

	public Time getFinPausa() {
	    return finPausa;
	}

	public void setFinPausa(Time finPausa) {
	    this.finPausa = finPausa;
	}
}
