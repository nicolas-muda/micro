package microservicioViajes.repositorio;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import microservicioViajes.modelo.Pausa;

public interface PausaRepositorio extends JpaRepository<Pausa, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Pausa p SET p.finPausa = :horaActual WHERE p.id = :idPausa")
	void IngresaFinPausa(int idPausa, LocalTime horaActual);

}
