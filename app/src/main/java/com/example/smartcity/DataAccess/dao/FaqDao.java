package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.FaqService;
import com.example.smartcity.model.Faq;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public class FaqDao implements FaqDataAccess {

	@Override
	public Response<ArrayList<Faq>> getAllFaq(int id) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(FaqService.class)
				.getAllFaq(id)
				.execute();
	}

    /*
    public ArrayList<Faq> getAllFaq(AccessToken accessToken)throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/faq");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer "+accessToken);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("Accept","application/json");
        switch (connection.getResponseCode()) {
            case 500: throw new ApiAccessException();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON, line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();
        return Utils.jsonToFaqs(stringJSON);
    }*/
}
