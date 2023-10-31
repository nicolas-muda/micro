package microservicioViajes.controlador;

import java.util.List;
import java.util.Optional;

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
import microservicioViajes.servicio.ViajeServicio;
import microservicioViajes.dtos.*;

@RestController
@RequestMapping("/MSViajes/Viaje")
public class ViajeControlador {

	@Autowired
	private ViajeServicio viajeServicio;
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
		viajeServicio.finalizarViaje(idViaje, idParada, kmReco);
	}

	// reporte de monopatines por tiempo listo
	@GetMapping("/reporte/{selector}")
	public List<ReporteUso> ReporteUso(@PathVariable int selector) {
		return viajeServicio.reporteUso(selector);
	}

	// calcularPrecioViaje
	@GetMapping("/precio/{idViaje}/{precioKm}")
	public float calculadorPrecio(@PathVariable int idViaje, @PathVariable float precioKm) {
		Optional<viaje> v = viajeRepositorio.findById(idViaje);
		if (v.isPresent()) {
			float distanciaKm = v.get().getKmRecorridos();
			return distanciaKm * precioKm;
		} else {
			return 0;
		}
	}
}
