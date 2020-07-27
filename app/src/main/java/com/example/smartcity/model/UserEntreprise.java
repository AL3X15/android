package com.example.smartcity.model;

import java.io.Serializable;

public class UserEntreprise implements Serializable {
	private String nom;
	private String email;
	private String phoneNumber;

	public UserEntreprise() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
