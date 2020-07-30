package com.example.smartcity.model;

import java.io.Serializable;
import java.util.Date;

public class Annonce implements Serializable {
	private int id;
	private String poste;
	private Date dateDebut;
	private Date dateFin;
	private int paie;
	private UserEntreprise entreprise;

	public Annonce(String poste, Date dateDebut, Date dateFin, int paie, UserEntreprise userEntreprise, Adresse address) {
		this.poste = poste;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.paie = paie;
	}

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


	public UserEntreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(UserEntreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String toString() {
		return this.poste + " \npayé :" + this.paie + " \ndébute le " + dateDebut + " \nfini le " + dateFin;
	}
}
