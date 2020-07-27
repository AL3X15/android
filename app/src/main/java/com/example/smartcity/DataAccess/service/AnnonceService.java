package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.CritereRecherche;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnnonceService {

	@GET("/postulation/etudiant/")
	Call<ArrayList<Annonce>> getAnnonceEtudiant(@Path("id")int id);

	@GET("/https://smartcityjober.azurewebsites.net/annonce/page/")
	Call<ArrayList<Annonce>> getResultatSerch(@Body CritereRecherche critereRecherche);

	@POST("/Postulation/")
	Call<Void> acceptAnnonce(@Path("id")int id);
}
