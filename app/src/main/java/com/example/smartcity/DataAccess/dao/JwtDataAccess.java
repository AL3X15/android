package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;

import java.io.IOException;

import retrofit2.Response;

public interface JwtDataAccess {

	Response<AccessToken> getAccessToken(InfoConnection infoConnection) throws IOException;
}
