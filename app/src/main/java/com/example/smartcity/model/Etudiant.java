package com.example.smartcity.model;

import java.util.ArrayList;
import java.util.Date;

public class Etudiant {
	private String prenom;
	private String sexe;
	private Date dateNaissance;
	private String registreNational;
	private Adresse adresse;
	private ArrayList<Tag> tags;
	private UserEtudiant user;
	private String rowVersion;

	public String getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(String rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Etudiant() {
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getRegistreNational() {
		return registreNational;
	}

	public void setRegistreNational(String registreNational) {
		this.registreNational = registreNational;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public UserEtudiant getUser() {
		return user;
	}

	public void setUser(UserEtudiant user) {
		this.user = user;
	}
}
