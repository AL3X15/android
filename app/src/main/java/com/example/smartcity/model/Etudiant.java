package com.example.smartcity.model;

import java.util.ArrayList;
import java.util.Date;

public class Etudiant {
    private int id;
    private String prenom;
    private Character sexe;
    private Date dateNaissance;
    private String registreNational;
    private Date expirationBadge;
    private Adresse adresse;
    private ArrayList<Tag> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getExpirationBadge() {
        return expirationBadge;
    }

    public void setExpirationBadge(Date expirationBadge) {
        this.expirationBadge = expirationBadge;
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
}
