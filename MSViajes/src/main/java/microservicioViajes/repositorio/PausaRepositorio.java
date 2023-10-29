package microservicioViajes.repositorio;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import microservicioViajes.modelo.Pausa;

public interface PausaRepositorio extends JpaRepository<Pausa, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Pausa p SET p.finPausa = :horaActualSQL WHERE p.id = :idPausa")
	void IngresaFinPausa(int idPausa, Time horaActualSQL);

	@Query("SELECT p FROM Pausa p WHERE p.idViaje = :idViaje")
	List<Pausa> PausasIdViaje(int idViaje);

}
