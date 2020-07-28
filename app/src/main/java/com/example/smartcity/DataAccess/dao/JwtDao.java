package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.JwtService;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;

import java.io.IOException;

import retrofit2.Response;

public class JwtDao implements JwtDataAccess{

	@Override
	public Response<AccessToken> getAccessToken(InfoConnection infoConnection) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(JwtService.class)
				.getAccessToken(infoConnection)
				.execute();
	}
}
