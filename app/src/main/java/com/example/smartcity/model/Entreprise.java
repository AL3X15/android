package com.example.smartcity.model;

import java.io.Serializable;

public class Entreprise implements Serializable {
    private String nom;

    public Entreprise(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
