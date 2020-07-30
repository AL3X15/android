package com.example.smartcity.model;

import java.io.Serializable;

public class Entreprise implements Serializable {
	private String nomResponsable;
	private UserEntreprise user;

	public Entreprise() {
	}

	public UserEntreprise getUser() {
		return user;
	}

	public void setUser(UserEntreprise user) {
		this.user = user;
	}
	public String getNomResponsable() {
		return nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}



}
