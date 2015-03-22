package com.exam.onebox.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.exam.onebox.actions.CalcularDiferentesRutasAction;
import com.exam.onebox.actions.CalcularDistanciaRutaAction;
import com.exam.onebox.interfaces.Accion;
import com.exam.onebox.model.Ciudad;
import com.exam.onebox.model.Traza;
import com.exam.onebox.utils.FileUtils;
import com.exam.onebox.utils.MapConverter;
import com.neksos.onebox.constantes.Constantes;

public class App {

	private static final Logger log = Logger.getLogger(App.class.getName());

	private static final Integer NO_SUCH_ROUTE = -1;

	public static void main(String[] args) {

		Accion action = null;
		BufferedReader reader = null;
		Map<String, Ciudad> mapCiudades = null;
		Object result = null;
		List<Traza> lstTrazas = null;

		if (args.length != 3) {
			System.exit(1);
		}

		String filemap = args[0];
		String accion = args[1];
		String ciudades = args[2];

		App.log.info("Parametro filemap : " + filemap);
		App.log.info("Parametro accion : " + accion);
		App.log.info("Parametro ciudades : " + ciudades);

		try {

			reader = FileUtils.getFileInputStream(filemap);
			mapCiudades = MapConverter.fromFile(reader);

			if (Constantes.ACCION_DISTANCE.equals(accion)){
				action = new CalcularDistanciaRutaAction();
			}else if (Constantes.ACCION_ALL_ROUTES_WITH_SHORTEST.equals(accion)){
				action = new CalcularDiferentesRutasAction();
			}

			action.initialize(mapCiudades, ciudades);
			result = action.execute();

			if (result instanceof Integer) {
				// Accion calcular distancia
				if (App.NO_SUCH_ROUTE.equals(result)) {
					App.log.info("[main] --> Resultado : NO SUCH ROUTE");
				} else {
					App.log.info("[main] --> Resultado : " + result);
				}
			} else {
				// Accion calcular rutas
				if (result != null) {
					lstTrazas = (List<Traza>) result;
					App.pintarTrazas(lstTrazas);
				}
			}

		} catch (FileNotFoundException fnfe) {
			App.log.severe("[main] --> FileNotFoundException" + fnfe);
			System.exit(1);
		} catch (IOException ioe) {
			App.log.severe("[main] --> IOException" + ioe);
			System.exit(1);
		} catch (Exception e) {
			App.log.severe("[main] --> Exception" + e);
			System.exit(1);
		}
		System.exit(0);
	}

	private static void pintarTrazas(List<Traza> lstTrazas) {
		// TODO Auto-generated method stub
		Integer shorterRoute = Integer.MAX_VALUE;
		Traza objTraza = null;
		int position = 0;

		App.log.info("############ Pintando todas las rutas ###########");
		for (int i = 0; i < lstTrazas.size() ; i++) {
			objTraza = lstTrazas.get(i);
			App.log.info(objTraza.toString());
			if (shorterRoute >= objTraza.getDistancia()){
				shorterRoute = objTraza.getDistancia();
				position = i;
			}
		}
		App.log.info("#################################################");

		App.log.info("############ Pintando ruta mas corta ############");
		App.log.info(lstTrazas.get(position).toString());
		App.log.info("#################################################");
	}
}
