package com.example.smartcity.model;

import androidx.annotation.Nullable;

public class Tag {
	private String nom;
	private String description;

	public Tag(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public Tag() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		if(		obj == null||
				!(obj instanceof Tag)||
				((Tag) obj).getNom().compareTo(this.getNom())!=0)
			return false;
		else
			return true;
	}
}
