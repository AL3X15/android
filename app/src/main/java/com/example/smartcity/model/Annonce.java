package com.example.smartcity.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Annonce implements Serializable {
    private String poste;
    private GregorianCalendar dateDebut;
    private GregorianCalendar dateFin;
    private int paie;
    private Entreprise entreprise;
    private Adresse address;

    public Annonce(String poste, GregorianCalendar dateDebut, GregorianCalendar dateFin, int paie, Entreprise entreprise, Adresse address) {
        this.poste = poste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.paie = paie;
        this.entreprise = entreprise;
        this.address = address;
    }

    public Annonce(String poste, GregorianCalendar dateDebut, GregorianCalendar dateFin) {
        this.poste = poste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.address = new Adresse("qjsdlkh","ldsqjkl",0,"skhklqfjh");
        this.entreprise = new Entreprise("Bob");
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public GregorianCalendar getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(GregorianCalendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    public GregorianCalendar getDateFin() {
        return dateFin;
    }

    public void setDateFin(GregorianCalendar dateFin) {
        this.dateFin = dateFin;
    }

    public int getPaie() {
        return paie;
    }

    public void setPaie(int paie) {
        this.paie = paie;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Adresse getAddress() {
        return address;
    }

    public void setAddress(Adresse address) {
        this.address = address;
    }
}
