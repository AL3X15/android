package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.UserEtudiant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

	@GET("/Etudiant/")
	Call<UserEtudiant> getMe();

	@POST("/Etudiant/")
	Call<Void> inscription(@Body UserEtudiant userEtudiant);

	@PUT()
	Call<Void> editMe(@Body UserEtudiant userEtudiant);

}
