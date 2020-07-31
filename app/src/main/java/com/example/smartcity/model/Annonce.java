package com.example.smartcity.model;

import java.io.Serializable;
import java.util.Date;

public class Annonce implements Serializable {
	private int id;
	private String poste;
	private Date dateDebut;
	private Date dateFin;
	private int paie;
	private Entreprise entreprise;

	public Annonce() {

	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getPaie() {
		return paie;
	}

	public void setPaie(int paie) {
		this.paie = paie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
}
