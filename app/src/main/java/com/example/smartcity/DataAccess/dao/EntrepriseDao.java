package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.EntrepriseService;
import com.example.smartcity.model.UserEntreprise;

import java.io.IOException;

import retrofit2.Response;

public class EntrepriseDao implements EntrepriseDataAccess {

	@Override
	public Response<UserEntreprise> getEntrepriseByAnnonce(int id) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(EntrepriseService.class)
				.getEntrepriseByAnnonce(id)
				.execute();
	}
}
