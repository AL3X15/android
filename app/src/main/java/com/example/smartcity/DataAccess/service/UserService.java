package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.ChangePassword;
import com.example.smartcity.model.Etudiant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

	@GET("/Etudiant/")
	Call<Etudiant> getMe();

	@POST("/Etudiant/")
	Call<Void> inscription(@Body Etudiant etudiant);

	@PUT("/Etudiant/")
	Call<Void> editMe(@Body Etudiant etudiant);

	@POST("/Password/")
	Call<Void> editPassword(@Body ChangePassword changePassword);

	@DELETE("/Etudiant/")
	Call<Void> deleteMe();

}
