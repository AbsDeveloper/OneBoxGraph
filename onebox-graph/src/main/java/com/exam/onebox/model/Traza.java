package com.exam.onebox.model;

import java.util.ArrayList;
import java.util.List;

public class Traza implements Cloneable {

	List<String> lstCiudades;

	private Integer distancia;

	public Traza() {
		this.distancia = 0;
		this.lstCiudades = new ArrayList<String>();
	}

	public List<String> getLstCiudades() {
		return this.lstCiudades;
	}

	public void setLstCiudades(List<String> lstCiudades) {
		this.lstCiudades = lstCiudades;
	}

	public Integer getDistancia() {
		return this.distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Traza clone(List<String> lstCiudades) {
		Traza clone = null;
		try {
			clone = (Traza) super.clone();
			clone.setLstCiudades(new ArrayList<String>(lstCiudades));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clone;
	}

	@Override
	public String toString() {
		return "Traza [lstCiudades=" + this.lstCiudades + ", distancia=" + this.distancia + "]";
	}


}
