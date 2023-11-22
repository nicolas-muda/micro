package microservicioViajes.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import microservicioViajes.modelo.Configuracion;

@Service
public class ConfiguracionServicio {
	@Value("${configuracion_url}")
	private String baseUrl;

	private final RestTemplate rest;

	public ConfiguracionServicio(RestTemplate rest) {
		this.rest = rest;
	}
	
	public List<Configuracion> traerListadoPrecios() {
		ResponseEntity<List<Configuracion>> responseEntity = rest.exchange(baseUrl + "/traer", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Configuracion>>() {
				});

		return responseEntity.getBody();
	}

}
