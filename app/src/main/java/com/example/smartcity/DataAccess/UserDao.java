package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Etudiant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class UserDao implements UserDataAccess {

    @Override
    public Etudiant getMe(String mail, String motDePasse) throws Exception{
        AccessTokenDao accessTokenDao = new AccessTokenDao();
        AccessToken accessToken = accessTokenDao.getAccessToken(mail,motDePasse);/*
        URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken.getAccessToken());
        switch (connection.getResponseCode()) {
            case 404: throw new EtudiantDontExist();
            case 500: throw new ApiAccessException();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;
        while ((line = buffer.readLine()) != null) {
            builder.append(line.toString());
        }
        buffer.close();
        stringJSON = builder.toString();
        Etudiant etudiant =Utils.jsonToEtudiant(stringJSON);*/
        Etudiant etudiant= new Etudiant();
        etudiant.setAccesToken(accessToken);
        return etudiant;
    }

    @Override
    public void inscription(Etudiant etudiant) throws Exception {
        String jsonString = Utils.etudiantToJson(etudiant);
        URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        writer.write(jsonString);

        writer.flush();
        writer.close();
        out.close();
        urlConnection.disconnect();
        switch (urlConnection.getResponseCode()) {
            case 400: throw new InscriptionInvalide();
            case 500: throw new ApiAccessException();
        }
    }

    @Override
    public void editMe(AccessToken accessToken, Etudiant etudiant) throws Exception{
        String jsonString = Utils.etudiantToJson(etudiant);
        URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Authorization","Bearer"+accessToken.getAccessToken());
        urlConnection.setRequestMethod("PUT");
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        writer.write(jsonString);

        writer.flush();
        writer.close();
        out.close();
        urlConnection.disconnect();
        switch (urlConnection.getResponseCode()) {
            case 404: throw new EtudiantDontExist();
            case 500: throw new ApiAccessException();
        }
    }


}
