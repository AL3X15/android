package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Entreprise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EntrepriseDao implements EntrepriseDataAccess {
    @Override
    public Entreprise getEntrepriseByAnnonce(String accessToken, Annonce annonce) throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/annonce/"+annonce.getId());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();
        return Utils.jsonToEntreprise(stringJSON);
    }
}
