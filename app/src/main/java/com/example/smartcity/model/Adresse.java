package com.example.smartcity.model;

public class Adresse {
	private String route;
	private String numero;
	private Integer codePostal;
	private String localite;

	public Adresse(String route, String numero, Integer codePostal, String localite) {
		this.route = route;
		this.numero = numero;
		this.codePostal = codePostal;
		this.localite = localite;
	}

	public Adresse() {
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}
}
