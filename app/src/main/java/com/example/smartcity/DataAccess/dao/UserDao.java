package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.UserService;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.UserEtudiant;

import java.io.IOException;

import retrofit2.Response;

public class UserDao implements UserDataAccess {


	@Override
	public Response<UserEtudiant> getMe() throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(UserService.class)
				.getMe()
				.execute();
	}

	@Override
	public Response<Void> inscription(Etudiant etudiant) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(UserService.class)
				.inscription(etudiant)
				.execute();
	}

	@Override
	public Response<Void> editMe(Etudiant etudiant) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(UserService.class)
				.editMe(etudiant)
				.execute();
	}
}
