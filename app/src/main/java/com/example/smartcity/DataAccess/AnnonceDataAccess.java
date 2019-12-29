package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.UserEtudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AnnonceDataAccess {
    public ArrayList<Annonce> getAnnonceEtudiant(AccessToken accessToken, UserEtudiant userEtudiant)throws Exception;
    public ArrayList<Annonce> getResultatSerch(AccessToken accessToken, GregorianCalendar dateDebut, GregorianCalendar dateFin, ArrayList<Tag> tags)throws Exception;
    public void acceptAnnonce(AccessToken accessToken, Annonce annonce, UserEtudiant userEtudiant)throws Exception;
}
