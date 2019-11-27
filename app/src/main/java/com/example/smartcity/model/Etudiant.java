package com.example.smartcity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Etudiant implements Serializable {
	private String nom;
	private String prenom;
	private String password;
	private Character sexe;
	private Integer numTel;
	private String mail;
	private GregorianCalendar dateNaissance;
	private String registreNational;
	private Date expirationBadge;
	private String cv;
	private Boolean aEvalue;
	private Adresse adresse;
	private ArrayList<Tag> tags;


	public Etudiant() {
	}
	public Etudiant(String nom, String prenom){
		setNom(nom);
		setPrenom(prenom);
		setAdresse(new Adresse("qjsdlkh","ldsqjkl",0,"skhklqfjh"));
		setMail("djkshfqklh");
		setNumTel(0);
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

	public Integer getNumTel() {
		return numTel;
	}

	public void setNumTel(Integer numTel) {
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

	public Date getExpirationBadge() {
		return expirationBadge;
	}

	public void setExpirationBadge(Date expirationBadge) {
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
}
