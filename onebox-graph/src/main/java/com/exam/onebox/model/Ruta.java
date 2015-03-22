package com.exam.onebox.model;

public class Ruta {
	private String destino;
	private Integer distancia;

	public Ruta(){}

	public Ruta(String pDestino, Integer pDistancia){
		this.destino = pDestino;
		this.distancia = pDistancia;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getDistancia() {
		return this.distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "Ruta [destino=" + this.destino + ", distancia=" + this.distancia + "]";
	}

}
