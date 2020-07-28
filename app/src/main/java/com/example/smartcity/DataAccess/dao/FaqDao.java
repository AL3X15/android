package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.FaqService;
import com.example.smartcity.model.PageResultFaq;

import java.io.IOException;

import retrofit2.Response;

public class FaqDao implements FaqDataAccess {

	@Override
	public Response<PageResultFaq> getFaq(int page) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(FaqService.class)
				.getFaq(page)
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
