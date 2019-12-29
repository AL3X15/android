package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Faq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FaqDao implements FaqDataAccess{
    public ArrayList<Faq> getAllFaq(AccessToken accessToken)throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/faq");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken);
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
    }
}
