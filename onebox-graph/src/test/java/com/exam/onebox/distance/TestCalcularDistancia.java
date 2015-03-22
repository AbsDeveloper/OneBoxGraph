package com.exam.onebox.distance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exam.onebox.actions.CalcularDistanciaRutaAction;
import com.exam.onebox.interfaces.Accion;
import com.exam.onebox.model.Ciudad;
import com.exam.onebox.utils.FileUtils;
import com.exam.onebox.utils.MapConverter;

public class TestCalcularDistancia {

	private static final String NOMBRE_FICHERO = "mapa.txt";

	private static final String RUTA_1 = "ABC";
	private static final String RUTA_2 = "AD";
	private static final String RUTA_3 = "ADC";
	private static final String RUTA_4 = "AEBCD";
	private static final String RUTA_5 = "AED";

	private static final int VALOR_ESPERADO_1 = 9;
	private static final int VALOR_ESPERADO_2 = 5;
	private static final int VALOR_ESPERADO_3 = 13;
	private static final int VALOR_ESPERADO_4 = 22;
	private static final int VALOR_ESPERADO_5 = -1;

	private Map<String, Ciudad> mapaCiudades;

	@Before
	public void init() {
		BufferedReader reader;
		try {
			reader = FileUtils.getFileInputStream(TestCalcularDistancia.NOMBRE_FICHERO);
			if (reader != null) {
				this.mapaCiudades = MapConverter.fromFile(reader);
			}
		} catch (FileNotFoundException e) {
			Assert.fail();
		} catch (IOException ioe) {
			Assert.fail();
		}
	}

	@Test
	public void calcularDistancia() {

		Integer distanciaCalculada = null;

		if (this.mapaCiudades == null) {
			Assert.fail();
		}

		Accion calcularDistanciaAccion = new CalcularDistanciaRutaAction();

		calcularDistanciaAccion.initialize(this.mapaCiudades, TestCalcularDistancia.RUTA_1);
		distanciaCalculada = (Integer) calcularDistanciaAccion.execute();
		Assert.assertTrue(distanciaCalculada.intValue() == TestCalcularDistancia.VALOR_ESPERADO_1);

		calcularDistanciaAccion.initialize(this.mapaCiudades, TestCalcularDistancia.RUTA_2);
		distanciaCalculada = (Integer) calcularDistanciaAccion.execute();
		Assert.assertTrue(distanciaCalculada.intValue() == TestCalcularDistancia.VALOR_ESPERADO_2);

		calcularDistanciaAccion.initialize(this.mapaCiudades, TestCalcularDistancia.RUTA_3);
		distanciaCalculada = (Integer) calcularDistanciaAccion.execute();
		Assert.assertTrue(distanciaCalculada.intValue() == TestCalcularDistancia.VALOR_ESPERADO_3);

		calcularDistanciaAccion.initialize(this.mapaCiudades, TestCalcularDistancia.RUTA_4);
		distanciaCalculada = (Integer) calcularDistanciaAccion.execute();
		Assert.assertTrue(distanciaCalculada.intValue() == TestCalcularDistancia.VALOR_ESPERADO_4);

		calcularDistanciaAccion.initialize(this.mapaCiudades, TestCalcularDistancia.RUTA_5);
		distanciaCalculada = (Integer) calcularDistanciaAccion.execute();
		Assert.assertTrue(distanciaCalculada == TestCalcularDistancia.VALOR_ESPERADO_5);


	}

}
