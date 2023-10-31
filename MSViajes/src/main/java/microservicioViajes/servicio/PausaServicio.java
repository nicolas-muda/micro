package microservicioViajes.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservicioViajes.modelo.Pausa;
import microservicioViajes.repositorio.PausaRepositorio;

@Service
public class PausaServicio {

	@Autowired
	private PausaRepositorio pausaRepositorio;

	public int getTiempoParada(int idViaje) {
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
