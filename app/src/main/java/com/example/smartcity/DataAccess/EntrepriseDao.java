package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.AnnonceDontExist;
import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.UserEntreprise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EntrepriseDao implements EntrepriseDataAccess {
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
    }
}
