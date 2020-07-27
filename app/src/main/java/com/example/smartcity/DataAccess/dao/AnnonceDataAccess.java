package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.CritereRecherche;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public interface AnnonceDataAccess {

    Response<ArrayList<Annonce>> getAnnonceEtudiant(int id) throws IOException;
    Response<ArrayList<Annonce>> getResultatSerch(CritereRecherche critereRecherche) throws IOException;
    Response<Void> acceptAnnonce(int id) throws IOException;

    /*
    public ArrayList<Annonce> getAnnonceEtudiant(AccessToken accessToken, UserEtudiant userEtudiant)throws Exception;
    public ArrayList<Annonce> getResultatSerch(AccessToken accessToken, GregorianCalendar dateDebut, GregorianCalendar dateFin, ArrayList<Tag> tags)throws Exception;
    public void acceptAnnonce(AccessToken accessToken, Annonce annonce, UserEtudiant userEtudiant)throws Exception;
    */
}
