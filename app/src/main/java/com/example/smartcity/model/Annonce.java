package com.example.smartcity.model;

import java.io.Serializable;
import java.util.Date;

public class Annonce implements Serializable, Comparable<Annonce> {
	private int id;
	private String poste;
	private Date dateDebut;
	private Date dateFin;
	private int paie;
	private Entreprise entreprise;
	private Adresse adresse;
	private Boolean estUrgent;

	@Override
	public int compareTo(Annonce annonce){
		return (this.getEstUrgent() ? (annonce.getEstUrgent()? 0 : -1 ) : (annonce.getEstUrgent() ? 1 : 0));
	}

	public Boolean getEstUrgent() {
		return estUrgent;
	}

	public void setEstUrgent(Boolean estUrgent) {
		this.estUrgent = estUrgent;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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


	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
}
