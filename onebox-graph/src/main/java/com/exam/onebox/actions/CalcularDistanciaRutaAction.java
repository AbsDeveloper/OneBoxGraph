package com.exam.onebox.actions;

import java.math.BigInteger;
import java.util.Map;
import java.util.logging.Logger;

import com.exam.onebox.interfaces.Accion;
import com.exam.onebox.model.Ciudad;
import com.exam.onebox.model.Ruta;

public class CalcularDistanciaRutaAction implements Accion {

	private static final Logger log = Logger.getLogger( CalcularDistanciaRutaAction.class.getName() );

	private char[] arrRuta;

	private Map<String, Ciudad> mapCiudades;

	public CalcularDistanciaRutaAction() {
	}

	@Override
	public Object execute() {

		Ciudad ciudadActual = null;
		Integer distancia = null;
		Ruta objRuta = null;

		CalcularDistanciaRutaAction.log.info("[execute] --> Inicio obteniendo distancia");

		if (this.arrRuta != null && this.arrRuta.length > 0) {
			distancia = BigInteger.ZERO.intValue();
			for (char ciudad : this.arrRuta) {
				CalcularDistanciaRutaAction.log.info("[execute] --> Ciudad : " + ciudad);
				if (ciudadActual != null) {
					objRuta = this.obtenerRutaDestino(ciudadActual, distancia, ciudad);
					if (objRuta != null) {
						CalcularDistanciaRutaAction.log.info("[execute] --> Add distancia : " + objRuta.getDistancia());
						distancia += objRuta.getDistancia();
					} else {
						distancia = -1;
						break;
					}
				}

				ciudadActual = this.mapCiudades.get(String.valueOf(ciudad));

			}
		}

		CalcularDistanciaRutaAction.log.info("[execute] --> Fin obteniendo distancia");

		return distancia;
	}

	private Ruta obtenerRutaDestino(Ciudad ciudadActual, Integer distancia, char ciudad) {
		Ruta result = null;
		for (Ruta ruta : ciudadActual.getLstRutasDirectas()) {
			if (ruta.getDestino().equals(String.valueOf(ciudad))) {
				CalcularDistanciaRutaAction.log.info("[obtenerRutaDestino] --> Destino encontrado : " + ciudad);
				result = ruta;
				break;
			}
		}
		return result;
	}

	@Override
	public void initialize(Map<String, Ciudad> mapCiudades, String pRoute) {
		CalcularDistanciaRutaAction.log.info("[initialize] --> Cargando mapa");
		if (pRoute != null && !pRoute.isEmpty()) {
			this.mapCiudades = mapCiudades;
			this.arrRuta = pRoute.toCharArray();
		} else {
			throw new IllegalArgumentException("Error al inicializar la ruta");
		}
	}

}
