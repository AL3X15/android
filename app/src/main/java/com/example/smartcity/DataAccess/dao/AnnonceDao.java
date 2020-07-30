package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.AnnonceService;
import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.PageResultAnnonce;
import com.example.smartcity.model.PageResultPostulation;

import java.io.IOException;

import retrofit2.Response;

public class AnnonceDao implements AnnonceDataAccess {

	@Override
	public Response<PageResultPostulation> getAnnonceEtudiant(int ligne) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(AnnonceService.class)
				.getAnnonceEtudiant(ligne)
				.execute();
	}

	@Override
	public Response<PageResultAnnonce> getResultatSerch(int ligne, CritereRecherche critereRecherche) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(AnnonceService.class)
				.getResultatSerch(ligne, critereRecherche)
				.execute();
	}

	@Override
	public Response<Void> acceptAnnonce(int ligne) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(AnnonceService.class)
				.acceptAnnonce(ligne)
				.execute();
	}
}
