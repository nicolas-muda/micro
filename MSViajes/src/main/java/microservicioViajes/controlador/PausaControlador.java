package microservicioViajes.controlador;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservicioViajes.modelo.Pausa;
import microservicioViajes.repositorio.PausaRepositorio;

@RestController
@RequestMapping("/MSViajes/Pausa")
public class PausaControlador {

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
		int totalMinutos = 0;
		List<Pausa> lista = pausaRepositorio.PausasIdViaje(idViaje);
		// por cada pausa agarra la hora inicio y final las resta y las convierte en
		// minutos
		for (int i = 0; i < lista.size(); i++) {
			long tiempoInicio = lista.get(i).getInicioPausa().getTime();
			long tiempoFinal = lista.get(i).getFinPausa().getTime();

			long diferenciaTiempo = tiempoFinal - tiempoInicio;
			// Convierte la diferencia de milisegundos a minutos
			int minutosEnIntervalo = (int) (diferenciaTiempo / (60 * 1000));

			totalMinutos += minutosEnIntervalo;
		}
		return totalMinutos;
	}

}
