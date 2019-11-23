package com.example.smartcity.model;

import android.location.Address;

import java.util.Date;

public class Annonce {
    private String poste;
    private Date dateDebut;
    private Date dateFin;
    private int paie;
    private Entreprise entreprise;
    private Address address;

    public Annonce(String poste, Date dateDebut, Date dateFin, int paie, Entreprise entreprise, Address address) {
        this.poste = poste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.paie = paie;
        this.entreprise = entreprise;
        this.address = address;
    }

    public Annonce(String poste, Date dateDebut, Date dateFin) {
        this.poste = poste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
