package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AnnonceDataAccess {
    public ArrayList<Annonce> getAnnonceEtudiant(Etudiant etudiant);
    public ArrayList<Annonce> getResultatSerch(GregorianCalendar dateDebut, GregorianCalendar dateFin, ArrayList<Tag> tags);
}
