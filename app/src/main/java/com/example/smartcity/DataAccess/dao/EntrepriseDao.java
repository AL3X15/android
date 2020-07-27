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
	/*
	@Override
	public UserEntreprise getEntrepriseByAnnonce(String accessToken, Annonce annonce) throws Exception{
		URL url = new URL("https://smartcityjober.azurewebsites.net/entreprise/annonce/"+annonce.getId());
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer"+accessToken);
		connection.setRequestProperty("Content-Type","application/json");
		connection.setRequestProperty("Accept","application/json");
		switch (connection.getResponseCode()) {
			case 404: throw new AnnonceDontExist();
			case 500: throw new ApiAccessException();
		}
		BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String stringJSON, line;
		while((line = buffer.readLine())!=null)
			builder.append(line);
		buffer.close();
		stringJSON = builder.toString();
		return Utils.jsonToEntreprise(stringJSON);
	}*/
}
