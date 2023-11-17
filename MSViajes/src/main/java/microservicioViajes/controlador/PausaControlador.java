package microservicioViajes.controlador;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import microservicioViajes.modelo.Pausa;
import microservicioViajes.repositorio.PausaRepositorio;
import microservicioViajes.servicio.PausaServicio;

@RestController
@RequestMapping("/MSViajes/Pausa")
@Tag(name = "Servicio pausa", description = "se encarga de todo lo referente a las pausas")
public class PausaControlador {

	@Autowired
	private PausaServicio pausaServicio;

	@Autowired
	private PausaRepositorio pausaRepositorio;

	// crear pausa listo
	@PostMapping
	public void crearPausa(@RequestBody Pausa p) {
		pausaRepositorio.save(p);
	}

	// ingresa la hora del fin de la pausa listo
	@PutMapping("/finalPausa/{idPausa}")
	public void IngresaFinPausa(@PathVariable int idPausa) {
		// agarro la hora
		LocalTime horaActual = LocalTime.now();
		// lo convierto en Time que lo toma mysql
		Time horaActualSQL = Time.valueOf(horaActual);
		pausaRepositorio.IngresaFinPausa(idPausa, horaActualSQL);
	}

	// retorna el tiempo de la pausa
	@GetMapping("/tiempoPausa/{idViaje}")
	public int getTiempoParada(@PathVariable int idViaje) {
		int totalMinutos = pausaServicio.getTiempoParada(idViaje);
		return totalMinutos;
	}

}
