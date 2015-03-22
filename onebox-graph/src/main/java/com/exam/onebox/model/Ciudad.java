package com.exam.onebox.model;

import java.util.ArrayList;
import java.util.List;

public class Ciudad {

	List<Ruta> lstRutasDirectas = new ArrayList<Ruta>();
	String nombre;

	public Ciudad (){
	}

	public Ciudad(String pNombre){
		this.nombre = pNombre;
	}

	public List<Ruta> getLstRutasDirectas() {
		return this.lstRutasDirectas;
	}

	public void setLstRutasDirectas(List<Ruta> lstRutasDirectas) {
		this.lstRutasDirectas = lstRutasDirectas;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void anyadirRuta(Ruta pRuta){
		this.lstRutasDirectas.add(pRuta);
	}

	@Override
	public String toString() {
		return "Ciudad [lstRutasDirectas=" + this.lstRutasDirectas + ", nombre=" + this.nombre + "]";
	}

}
