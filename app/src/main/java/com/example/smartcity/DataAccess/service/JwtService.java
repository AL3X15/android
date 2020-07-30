package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JwtService {

	@POST("/Jwt/")
	Call<AccessToken> getAccessToken(@Body InfoConnection infoConnection);
}
