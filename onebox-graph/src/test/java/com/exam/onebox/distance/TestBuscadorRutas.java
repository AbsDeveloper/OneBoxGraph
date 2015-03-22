package com.exam.onebox.distance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exam.onebox.actions.CalcularDiferentesRutasAction;
import com.exam.onebox.interfaces.Accion;
import com.exam.onebox.model.Ciudad;
import com.exam.onebox.model.Traza;
import com.exam.onebox.utils.FileUtils;
import com.exam.onebox.utils.MapConverter;

public class TestBuscadorRutas {

	private static final String NOMBRE_FICHERO = "mapa.txt";

	private static final String RUTA_1 = "AB";

	private static final int VALOR_ESPERADO_1 = 4;

	private static final int DISTANCIA_0 = 5;

	private static final int DISTANCIA_1 = 10;

	private static final int DISTANCIA_2 = 14;

	private static final int DISTANCIA_3 = 18;

	private Map<String, Ciudad> mapaCiudades;

	@Before
	public void init() {
		BufferedReader reader;
		try {
			reader = FileUtils.getFileInputStream(TestBuscadorRutas.NOMBRE_FICHERO);
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
	public void testBuscadorRutas() {

		List<Traza> lstTraza = null;

		if (this.mapaCiudades == null) {
			Assert.fail();
		}

		Accion buscadorRutasAccion = new CalcularDiferentesRutasAction();

		buscadorRutasAccion.initialize(this.mapaCiudades, TestBuscadorRutas.RUTA_1);
		lstTraza = (List<Traza>) buscadorRutasAccion.execute();
		if (lstTraza != null){
			Assert.assertTrue(lstTraza.size() == TestBuscadorRutas.VALOR_ESPERADO_1);
		}else{
			Assert.fail();
		}

		Assert.assertTrue(lstTraza.get(0).getDistancia().equals(TestBuscadorRutas.DISTANCIA_0));

		Assert.assertTrue(lstTraza.get(1).getDistancia().equals(TestBuscadorRutas.DISTANCIA_1));

		Assert.assertTrue(lstTraza.get(2).getDistancia().equals(TestBuscadorRutas.DISTANCIA_2));

		Assert.assertTrue(lstTraza.get(3).getDistancia().equals(TestBuscadorRutas.DISTANCIA_3));


	}

}
