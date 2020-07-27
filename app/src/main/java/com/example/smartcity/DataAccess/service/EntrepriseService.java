package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.UserEntreprise;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EntrepriseService {

	@GET("/entreprise/")
	Call<UserEntreprise> getEntrepriseByAnnonce(@Path("id")int id);
}
