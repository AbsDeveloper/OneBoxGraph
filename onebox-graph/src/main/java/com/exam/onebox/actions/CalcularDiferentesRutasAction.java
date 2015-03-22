package com.exam.onebox.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.exam.onebox.interfaces.Accion;
import com.exam.onebox.model.Ciudad;
import com.exam.onebox.model.Ruta;
import com.exam.onebox.model.Traza;

public class CalcularDiferentesRutasAction implements Accion {

	private static final Logger log = Logger.getLogger( CalcularDiferentesRutasAction.class.getName() );
	// 0 - Origen
	// 1 - Destino
	private char[] arrCiudades;

	private String ciudadDestino;

	private Map<String, Ciudad> mapCiudades;

	List<Traza> lstTraza = new ArrayList<Traza>();

	public CalcularDiferentesRutasAction() {
	}

	/**
	 * Algoritmo de fuerza bruta, que calcula todas las rutas
	 * A partir de una ciduad calculamos todas las rutas posibles de destino, siempre y cuando la misma ruta,
	 * no haya pase dos veces por esa misma ciudad (blacklist), para evitar bucles.
	 * En caso de tener mas de un destino posible y valido para una ciudad, se deben replicar los datos de la traza original, eliminando el ultimo paso que hemos dado.
	 */
	@Override
	public Object execute() {

		Traza objTrazaInicial = new Traza();
		List<Traza> lstTrazasValidas = new ArrayList<Traza>();
		objTrazaInicial.getLstCiudades().add(String.valueOf(this.arrCiudades[0]));
		this.lstTraza.add(objTrazaInicial);
		Traza objTraza = null;
		boolean isPrimeraClonada = Boolean.FALSE;

		CalcularDiferentesRutasAction.log.info("[execute] --> Inicio obteniendo todas las rutas");

		if (this.mapCiudades != null) {
			// Lista de rutas que actualmente se estan procesando
			// Mientras haya rutas que calcular
			while (!this.lstTraza.isEmpty()) {
				//Recorremos todas las trazas
				for (int i = 0; i < this.lstTraza.size(); i++) {

					//Obtenemos la traza actual
					objTraza = this.lstTraza.get(i);
					isPrimeraClonada = Boolean.FALSE;

					String ciudadActual = objTraza.getLstCiudades().get(objTraza.getLstCiudades().size() - 1);
					for (Ruta objRuta : this.mapCiudades.get(ciudadActual).getLstRutasDirectas()) {
						// Comprobar que la nueva ruta no hayamos pasado ya
						// Comprobar que la nueva ruta no es el destino
						if (this.blackList(objRuta.getDestino(), objTraza)) {
							// Ruta no valida, ya hemos pasado por alli,
							// eliminamos
							this.lstTraza.remove(objTraza);
						}

						// La primera nunca se clona
						if (!isPrimeraClonada) {
							isPrimeraClonada = Boolean.TRUE;
						} else {
							// Clonamos
							objTraza = objTraza.clone(objTraza.getLstCiudades());
							//Se elimina la ultima ciudad, para volver a la ciudad actual
							this.volverCiudadActual(objTraza, this.mapCiudades.get(ciudadActual).getLstRutasDirectas());
							this.lstTraza.add(objTraza);
						}

						objTraza.getLstCiudades().add(objRuta.getDestino());
						objTraza.setDistancia(objTraza.getDistancia() + objRuta.getDistancia());

						if (this.destinoValido(objRuta.getDestino())) {
							// Guardamos y eliminamos de la lista de trazas pendientes de calculo
							lstTrazasValidas.add(objTraza);
							this.lstTraza.remove(objTraza);
						}

					}
				}

			}
		}

		CalcularDiferentesRutasAction.log.info("[execute] --> Fin obteniendo todas las rutas");

		return lstTrazasValidas;
	}

	private Traza volverCiudadActual(Traza objTraza, List<Ruta> lstRutas) {
		for (Ruta objRuta : lstRutas){
			if(objRuta.getDestino().equals(objTraza.getLstCiudades().get(objTraza.getLstCiudades().size() - 1))){
				//Hemos encontrado la distancia del ultimo paso.
				objTraza.setDistancia(objTraza.getDistancia() - objRuta.getDistancia());
			}
		}
		objTraza.getLstCiudades().remove(objTraza.getLstCiudades().size() - 1);
		return objTraza;
	}

	private boolean destinoValido(String destino) {
		boolean res = Boolean.FALSE;
		if (destino != null && !destino.isEmpty()) {
			res = destino.equals(this.ciudadDestino);
		}
		return res;
	}

	private boolean blackList(String destino, Traza objTraza) {
		boolean res = Boolean.FALSE;

		if (objTraza.getLstCiudades().contains(destino)) {
			res = Boolean.TRUE;
		}
		return res;
	}

	@Override
	public void initialize(Map<String, Ciudad> mapCiudades, String pCiudades) {
		// TODO Auto-generated method stub
		if (pCiudades != null && !pCiudades.isEmpty()) {
			this.mapCiudades = mapCiudades;
			this.arrCiudades = pCiudades.toCharArray();
			this.ciudadDestino = String.valueOf(this.arrCiudades[1]);
		} else {
			// Todo lanzar ActionNoValidException
			throw new IllegalArgumentException("Error al inicializar la ruta");
		}
	}

}
