package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.PageResultAnnonce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnnonceService {
	//TODO faire dans l'api
	@GET("/postulation/etudiant/")
	Call<PageResultAnnonce> getAnnonceEtudiant(@Path("id")int id);

	@GET("/https://smartcityjober.azurewebsites.net/annonce/page/")
	Call<PageResultAnnonce> getResultatSerch(@Path("ligne")int ligne, @Body CritereRecherche critereRecherche);

	@POST("/Postulation/")
	Call<Void> acceptAnnonce(@Path("id")int id);
}
