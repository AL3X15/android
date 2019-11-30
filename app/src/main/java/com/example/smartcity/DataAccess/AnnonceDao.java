package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnnonceDao implements AnnonceDataAccess {
    ArrayList<Annonce> annonces;

    public AnnonceDao(){
        annonces = new ArrayList<>();
        annonces.add(new Annonce("poste",new GregorianCalendar(),new GregorianCalendar()));
        annonces.add(new Annonce("poste2",new GregorianCalendar(),new GregorianCalendar()));
    }

    @Override
    public ArrayList<Annonce> getAnnonceEtudiant(Etudiant etudiant) {
        return annonces;
    }

    @Override
    public ArrayList<Annonce> getResultatSerch(GregorianCalendar dateDebut, GregorianCalendar dateFin, ArrayList<Tag> tags) {
        return annonces;
    }

    @Override
    public void acceptAnnonce(Annonce annonce, Etudiant etudiant) {

    }
}
