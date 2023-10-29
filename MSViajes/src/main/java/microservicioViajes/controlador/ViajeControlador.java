package microservicioViajes.controlador;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservicioViajes.modelo.viaje;
import microservicioViajes.repositorio.ViajeRepositorio;

@RestController
@RequestMapping("/MSViajes/Viaje")
public class ViajeControlador {

	@Autowired
	private ViajeRepositorio viajeRepositorio;

	// crear viaje listo
	@PostMapping
	public void crearViaje(@RequestBody viaje v) {
		viajeRepositorio.save(v);
	}

	// finalizar viaje listo
	@PutMapping("/finalizarViaje/{idViaje}/{idParada}/{kmReco}")
	public void finalizarViaje(@PathVariable int idViaje, @PathVariable int idParada, @PathVariable float kmReco) {
		// consigo la hora actual
		LocalTime horaActual = LocalTime.now();
		// lo convierto en Time que lo toma mysql
		Time horaFin = Time.valueOf(horaActual);

		// consigo el dia actual
		LocalDate diaActual = LocalDate.now();
		// lo convierto en Time que lo toma mysql
		Date fechaFin = Date.valueOf(diaActual);

		viajeRepositorio.finalizarViaje(idViaje, fechaFin, horaFin, idParada, kmReco);
	}
	
	//calcularTiempoUso
	@GetMapping("/calcularTiempo/")
	public int tiempoUsoMonopatin() {
		return 0;
	}
	
	//calcularKmRecorridos
	// calcularPrecioViaje
}
