package microservicioViajes.controlador;

import java.time.LocalTime;

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

	// crear monopatin
	@PostMapping
	public void crearPausa(@RequestBody Pausa p) {
		pausaRepositorio.save(p);
	}

	// ingresa la hora del fin de la pausa
	@PutMapping("/finalPausa/{idPausa}")
	public void IngresaFinPausa(@PathVariable int idPausa) {
		LocalTime horaActual = LocalTime.now().withNano(0);
		pausaRepositorio.IngresaFinPausa(idPausa, horaActual);
	}

	// ***FALTA TERMINAR*** retorna el tiempo de la pausa
	@GetMapping("/tiempoPausa/{idViaje}")
	public int getTiempoParada() {
		int tiempoPausa = 0;
		return tiempoPausa;
	}

}
