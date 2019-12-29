package com.example.smartcity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Annonce implements Serializable {
    private int id;
    private String poste;
    private Date dateDebut;
    private Date dateFin;
    private int paie;
    private ArrayList<Tag> tags;
    private UserEntreprise entreprise;

    public Annonce(String poste, Date dateDebut, Date dateFin, int paie, UserEntreprise userEntreprise, Adresse address) {
        this.poste = poste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.paie = paie;
        tags = new ArrayList<>();
    }

    public Annonce(){
        tags = new ArrayList<>();
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getPaie() {
        return paie;
    }

    public void setPaie(int paie) {
        this.paie = paie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public UserEntreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(UserEntreprise entreprise) {
        this.entreprise = entreprise;
    }
}
