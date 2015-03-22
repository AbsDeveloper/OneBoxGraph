package com.exam.onebox.interfaces;

import java.util.Map;

import com.exam.onebox.model.Ciudad;


public interface Accion {

	Object execute();

	void initialize(Map<String, Ciudad> mapCiudades, String pRoute);

}
