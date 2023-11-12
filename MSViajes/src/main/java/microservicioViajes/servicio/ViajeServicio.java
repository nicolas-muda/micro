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
import microservicioViajes.modelo.Configuracion;
import microservicioViajes.modelo.viaje;

@Service
public class ViajeServicio {
	@Autowired
	private ViajeRepositorio viajeRepositorio;
	@Autowired
	private PausaControlador pausaControlador;
	@Autowired
	private ConfiguracionServicio ConfiguracionServicio;

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

	public List<Integer> reporteMonopatinesUsados(int cantViajes, int año) {
		List<viaje> viajes = viajeRepositorio.findAll();
		// retornamos una lista con los id de los monopatines que cumplan
		List<Integer> monopatines = new ArrayList<>();

		// por cada viaje agarro el id del monopatin y cuento cuantas veces aparecio
		for (int i = 0; i < viajes.size(); i++) {
			int idActual = viajes.get(i).getIdMonopatin();
			int usosAño = 0;

			// recorro para ver cuantas veces se uso en el año
			for (int j = 0; j < viajes.size(); j++) {
				if (viajes.get(i).getFechaInicio().getYear() == año) {
					usosAño += 1;
				}
			}

			if (usosAño >= cantViajes) {
				monopatines.add(idActual);
			}
		}

		return monopatines;
	}

	public float Reporteganancias(Date fechaInicio, Date fechaFin) {
		// traigo todos los viajes
		List<viaje> listaViaje = viajeRepositorio.findAll();
		// traigo todos los precios
		List<Configuracion> listaPrecio = ConfiguracionServicio.traerListadoPrecios();

		float ganancia = 0;
		float tarifa1 = 0;
		float tarifa2 = 0;

		for (int i = 0; i < listaViaje.size(); i++) {
			// obtengo la fecha que se realizo el viaje
			Date inicioViaje = listaViaje.get(i).getFechaInicio();

			// si la fecha del viaje esta entre fecha inicio y fecha fin ahi calculo el
			// precio
			if ((inicioViaje.after(fechaInicio) || inicioViaje.equals(fechaInicio))
					&& (inicioViaje.before(fechaFin) || inicioViaje.equals(fechaFin))) {

				// busco los precio en el momento de la fecha del viaje
				for (int j = 0; j < listaPrecio.size(); j++) {
					// si la fecha del precio es menor que la fecha del viaje lo actualiza
					if (listaPrecio.get(i).getFechaCambio().before(inicioViaje)) {
						tarifa1 = listaPrecio.get(i).getTarifa1();
						tarifa2 = listaPrecio.get(i).getTarifa2();
					}
				}

				// ahora veo cual precio aplico si pasa los 15 aplico precio2
				if (pausaControlador.getTiempoParada(listaViaje.get(i).getId()) > 15) {
					ganancia += listaViaje.get(i).getKmRecorridos() * tarifa2;
				}
				// sino precio1
				else {
					ganancia += listaViaje.get(i).getKmRecorridos() * tarifa1;
				}

			}

		}
		return ganancia;
	}
}
