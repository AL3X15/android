package com.example.smartcity.model;

import androidx.annotation.NonNull;

public class Localite {
	private String codePostal;
	private String nom;

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Localite(String codePostal, String nom) {
		this.codePostal = codePostal;
		this.nom = nom;
	}

	public Localite() {
	}

	@NonNull
	@Override
	public String toString() {
		return getNom();
	}
}
