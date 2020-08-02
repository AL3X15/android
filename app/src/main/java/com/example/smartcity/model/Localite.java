package com.example.smartcity.model;

import java.io.Serializable;

public class Localite implements Serializable {
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Localite() {
	}

}
