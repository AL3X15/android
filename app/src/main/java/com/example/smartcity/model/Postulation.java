package com.example.smartcity.model;

public class Postulation {
	private int id;
	private Boolean estAccepte;
	private Annonce annonce;

	public Postulation() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getEstAccepte() {
		return estAccepte;
	}

	public void setEstAccepte(Boolean estAccepte) {
		this.estAccepte = estAccepte;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
}
