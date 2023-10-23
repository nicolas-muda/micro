package microservicioViajes.modelo;

import java.time.LocalTime;

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
	private LocalTime inicioPausa;

	@Column
	private LocalTime finPausa;

	public Pausa() {
	}

	public Pausa(int idViaje) {
		this.idViaje = idViaje;
		this.inicioPausa = LocalTime.now().withNano(0);
	}

	public int getId() {
		return id;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public LocalTime getInicioParada() {
		return inicioPausa;
	}

	public LocalTime getFinParada() {
		return finPausa;
	}

	public void setFinParada(LocalTime finParada) {
		this.finPausa = finParada;
	}
}
