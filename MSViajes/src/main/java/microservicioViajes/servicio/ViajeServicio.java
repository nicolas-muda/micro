package microservicioViajes.servicio;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservicioViajes.repositorio.ViajeRepositorio;
import microservicioViajes.controlador.PausaControlador;
import microservicioViajes.dtos.*;
import microservicioViajes.modelo.viaje;

@Service
public class ViajeServicio {
	@Autowired
	private ViajeRepositorio viajeRepositorio;
	@Autowired
	private PausaControlador pausaControlador;

	public void finalizarViaje(int idViaje, int idParada, float kmReco) {
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

	public List<ReporteUso> reporteUso(int selector) {
		if (selector == 0) {
			List<ReporteUso> reportes = reportesTiempoSinPausa();
			return cargarKm(reportes);
		} else {
			List<ReporteUso> reportes = reportesTiempoConPausa();
			return cargarKm(reportes);
		}
	}

	private List<ReporteUso> cargarKm(List<ReporteUso> reportes) {
		List<Object[]> resultado = viajeRepositorio.monopatinesKm();

		for (Object[] row : resultado) {
			int idMonopatinActual = (int) row[0];
			double kilometros = (double) row[1];

			for (int i = 0; i < reportes.size(); i++) {
				if (reportes.get(i).getIdMonopatin() == idMonopatinActual) {
					reportes.get(i).setKilometros(kilometros);
				}
			}
		}
		return reportes;
	}

	private List<ReporteUso> reportesTiempoSinPausa() {
		List<viaje> viajes = viajeRepositorio.findAll();
		List<ReporteUso> resultados = new ArrayList<>();
		for (viaje viaje : viajes) {
			// este if es para que no se rompa al tener un valor null mientras aun sigue en
			// viaje
			if (viaje.getHoraFin() != null) {
				// agarro el id
				int idMonopatin = viaje.getIdMonopatin();
				long tiempoInicio = viaje.getHoraInicio().getTime();
				long tiempoFin = viaje.getHoraFin().getTime();

				// hago la diferencia de tiempo entre los dos
				long diferenciaTiempo = tiempoFin - tiempoInicio;

				// Convierte la diferencia de milisegundos a minutos
				int minutosEnIntervalo = (int) (diferenciaTiempo / (60 * 1000));

				// en caso de que las hora esten invertidas
				if (minutosEnIntervalo < 0) {
					minutosEnIntervalo = minutosEnIntervalo * (-1);
				}

				ReporteUso existente = null;
				// busca si ya existe
				for (ReporteUso resultado : resultados) {
					if (resultado.getIdMonopatin() == idMonopatin) {
						existente = resultado;
						break;
					}
				}
				if (existente != null) {
					// Actualizar la duración existente
					existente.agregarDuracion(minutosEnIntervalo);
				} else {
					// Agregar un nuevo registro
					ReporteUso nuevoResultado = new ReporteUso(idMonopatin, minutosEnIntervalo);
					resultados.add(nuevoResultado);
				}
			}
		}

		return resultados;
	}

	private List<ReporteUso> reportesTiempoConPausa() {
		List<viaje> viajes = viajeRepositorio.findAll();
		List<ReporteUso> resultados = new ArrayList<>();
		for (viaje viaje : viajes) {
			// este if es para que no se rompa al tener un valor null mientras aun sigue en
			// viaje
			if (viaje.getHoraFin() != null) {
				// agarro el id
				int idViaje = viaje.getId();
				int idMonopatin = viaje.getIdMonopatin();
				long tiempoInicio = viaje.getHoraInicio().getTime();
				long tiempoFin = viaje.getHoraFin().getTime();

				// hago la diferencia de tiempo entre los dos
				long diferenciaTiempo = tiempoFin - tiempoInicio;

				// Convierte la diferencia de milisegundos a minutos
				int minutosEnIntervalo = (int) (diferenciaTiempo / (60 * 1000));

				// en caso de que las hora esten invertidas
				if (minutosEnIntervalo < 0) {
					minutosEnIntervalo = minutosEnIntervalo * (-1);
				}

				// llama a contrulador de pausa para traer los tiempos de las pausas y los suma
				int tiempoPausa = pausaControlador.getTiempoParada(idViaje);
				minutosEnIntervalo += tiempoPausa;

				ReporteUso existente = null;
				// busca si ya existe
				for (ReporteUso resultado : resultados) {
					if (resultado.getIdMonopatin() == idMonopatin) {
						existente = resultado;
						break;
					}
				}
				if (existente != null) {
					// Actualizar la duración existente
					existente.agregarDuracion(minutosEnIntervalo);
				} else {
					// Agregar un nuevo registro
					ReporteUso nuevoResultado = new ReporteUso(idMonopatin, minutosEnIntervalo);
					resultados.add(nuevoResultado);
				}
			}
		}

		return resultados;
	}
}
