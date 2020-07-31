package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.PageResultAnnonce;
import com.example.smartcity.model.PageResultPostulation;

import java.io.IOException;

import retrofit2.Response;

public interface AnnonceDataAccess {

    Response<PageResultPostulation> getAnnonceEtudiant(int ligne) throws IOException;
    Response<PageResultAnnonce> getResultatSerch(int ligne, CritereRecherche critereRecherche) throws IOException;
    Response<Void> acceptAnnonce(int id) throws IOException;
    Response<Void> annulerPost(int id) throws  IOException;

}
