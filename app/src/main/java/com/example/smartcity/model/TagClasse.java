package com.example.smartcity.model;

import java.util.ArrayList;

public class TagClasse {
	private String nom;
	private ArrayList<Tag> tags;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
}
