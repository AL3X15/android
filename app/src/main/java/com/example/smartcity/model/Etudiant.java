package com.example.smartcity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Etudiant implements Serializable {
	private int id;
	private String nom;
	private String prenom;
	private String password;
	private Character sexe;
	private String numTel;
	private String mail;
	private GregorianCalendar dateNaissance;
	private String registreNational;
	private GregorianCalendar expirationBadge;
	private String cv;
	private Boolean aEvalue;
	private Adresse adresse;
	private ArrayList<Tag> tags;
	private AccessToken accesToken;


	public Etudiant() {
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Character getSexe() {
		return sexe;
	}

	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public GregorianCalendar getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(GregorianCalendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getRegistreNational() {
		return registreNational;
	}

	public void setRegistreNational(String registreNational) {
		this.registreNational = registreNational;
	}

	public GregorianCalendar getExpirationBadge() {
		return expirationBadge;
	}

	public void setExpirationBadge(GregorianCalendar expirationBadge) {
		this.expirationBadge = expirationBadge;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Boolean getaEvalue() {
		return aEvalue;
	}

	public void setaEvalue(Boolean aEvalue) {
		this.aEvalue = aEvalue;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccessToken getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(AccessToken accesToken) {
		this.accesToken = accesToken;
	}
}
