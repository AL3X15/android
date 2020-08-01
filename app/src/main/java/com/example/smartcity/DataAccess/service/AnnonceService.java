package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.PageResultAnnonce;
import com.example.smartcity.model.PageResultPostulation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnnonceService {
	@GET("/postulation/mesPostulations/{ligne}")
	Call<PageResultPostulation> getAnnonceEtudiant(@Path("ligne")int ligne);

	@POST("/Annonce/page/{ligne}")
	Call<PageResultAnnonce> getResultatSerch(@Path("ligne")int ligne, @Body CritereRecherche critereRecherche);

	@POST("/Postulation/{id}")
	Call<Void> acceptAnnonce(@Path("id")int id);

	@DELETE("/Postulation/{id}")
	Call<Void> annulerPost(@Path("id")int id);
}
