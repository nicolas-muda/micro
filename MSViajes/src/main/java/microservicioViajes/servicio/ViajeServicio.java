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
import microservicioViajes.dtos.*;

@Service
public class ViajeServicio {
	@Autowired
	private ViajeRepositorio viajeRepositorio;

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
	
	public List<reporteUsoPorKm> reporteKm() {
		List<Object[]> resultado = viajeRepositorio.monopatinesKm();
        List<reporteUsoPorKm> reportes = new ArrayList<>();
        
        for (Object[] row : resultado) {
            int idMonopatin = (int) row[0];
            double kilometros = (double) row[1];
            reporteUsoPorKm reporte = new reporteUsoPorKm(idMonopatin, kilometros);
            reportes.add(reporte);
        }
		return reportes;
	}

	public List<reporteUsoPorTiempo> reporteTiempoSinPausa() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<reporteUsoPorTiempo> reporteTiempoConPausa() {
		// TODO Auto-generated method stub
		return null;
	}
}
