package com.example.smartcity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class UserEtudiant implements Serializable {

	private Etudiant etudiant;
	private String nom;
	private String password;
	private String confirmationPassword;
	private String phoneNumber;
	private String email;
	private int nbSignalement;

	public UserEtudiant() {
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public int getNbSignalement() {
		return nbSignalement;
	}

	public void setNbSignalement(int nbSignalement) {
		this.nbSignalement = nbSignalement;
	}
}
