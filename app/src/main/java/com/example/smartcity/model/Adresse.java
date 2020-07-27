package com.example.smartcity.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Adresse implements Serializable {
	private String rue;
	private String numero;
	private Localite localite;

	public Adresse(String rue, String numero, String codePostal, String localite) {
		this.rue = rue;
		this.numero = numero;
		this.localite = new Localite(codePostal, localite);
	}

	public Adresse() {
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Localite getLocalite() {
		return localite;
	}

	public void setLocalite(Localite localite) {
		this.localite = localite;
	}

	@NonNull
	@Override
	public String toString() {
		return getNumero() + " " + getRue() + ", " + getLocalite();
	}
}
