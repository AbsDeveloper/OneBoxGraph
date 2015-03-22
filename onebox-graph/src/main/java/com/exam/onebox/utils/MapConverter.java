package com.exam.onebox.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.exam.onebox.model.Ciudad;
import com.exam.onebox.model.Ruta;

public class MapConverter {

	public static final int GRAFO_LENGHT = 3;

	private static final Logger log = Logger.getLogger( MapConverter.class.getName() );

	public static Map<String, Ciudad> fromFile(final BufferedReader bufferReader) throws IOException {
		String linea;
		String[] arrGrafo;
		Map<String, Ciudad> mapaCiudades = null;
		try {
			if (bufferReader != null) {
				mapaCiudades = new TreeMap<String, Ciudad>();
				while ((linea = bufferReader.readLine()) != null) {
					if (linea != null && !linea.isEmpty()) {
						arrGrafo = linea.split("[\\s,]");
						for (String grafo : arrGrafo) {
							if (grafo.length() != MapConverter.GRAFO_LENGHT) {
								throw new IllegalArgumentException("Grafo no valido!");
							}

							int distance = 0;
							String ciudadOrigen = grafo.substring(0, 1);
							String ciudadDestino = grafo.substring(1, 2);
							String strDistancia = grafo.substring(2);
							boolean isNumeroValido = strDistancia.matches("[0-9]*");
							if (isNumeroValido) {
								distance = Integer.parseInt(strDistancia);
							} else {
								throw new IllegalArgumentException("Grafo no valido!");
							}

							Ciudad objCiudadOrigen = new Ciudad(ciudadOrigen);
							Ruta objRuta = new Ruta(ciudadDestino, distance);

							if (mapaCiudades.containsKey(ciudadOrigen)){
								mapaCiudades.get(ciudadOrigen).anyadirRuta(objRuta);
							}else{
								objCiudadOrigen.anyadirRuta(objRuta);
								mapaCiudades.put(ciudadOrigen, objCiudadOrigen);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			MapConverter.log.severe("[fromFile] --> IOException" + e);
			throw e;
		}
		return mapaCiudades;
	}

}
