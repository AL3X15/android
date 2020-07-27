package com.example.smartcity.model;

public class Entreprise {
	private String nomResponsable;
	private String numeroBanqueCarrefourEts;
	private Adresse adresse;

	public String getNomResponsable() {
		return nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}

	public String getNumeroBanqueCarrefourEts() {
		return numeroBanqueCarrefourEts;
	}

	public void setNumeroBanqueCarrefourEts(String numeroBanqueCarrefourEts) {
		this.numeroBanqueCarrefourEts = numeroBanqueCarrefourEts;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
