package microservicioViajes.repositorio;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import microservicioViajes.modelo.viaje;

public interface ViajeRepositorio extends JpaRepository<viaje, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE viaje v SET v.fechaFin = :fechaFin, v.horaFin = :horaFin, v.idParadaFin = :idParada, v.kmRecorridos= :kmReco WHERE v.id = :idViaje")
	void finalizarViaje(int idViaje, Date fechaFin, Time horaFin, int idParada, float kmReco);

	@Query(value = "SELECT id_Monopatin, SUM(km_Recorridos) AS kilometrosTotales FROM viaje GROUP BY id_Monopatin ORDER BY kilometrosTotales DESC", nativeQuery = true)
	List<Object[]> monopatinesKm();

}
