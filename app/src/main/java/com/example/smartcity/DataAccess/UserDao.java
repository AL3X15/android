package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;
import com.example.smartcity.model.UserEtudiant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UserDao implements UserDataAccess {

    @Override
    public InfoConnection getMe(String mail, String motDePasse) throws Exception{
        AccessTokenDao accessTokenDao = new AccessTokenDao();
        AccessToken accessToken = accessTokenDao.getAccessToken(mail,motDePasse);
        return getMe(mail,accessToken);
    }
    public InfoConnection getMe(String mail, AccessToken accessToken) throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant/"+mail);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken.getAccessToken());connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("Accept","application/json");
        switch (connection.getResponseCode()) {
            case 400: throw new EtudiantDontExist();
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
        UserEtudiant userEtudiant =Utils.jsonToEtudiant(stringJSON);
        InfoConnection infoConnection = new InfoConnection();
        infoConnection.setAccessToken(accessToken);
        infoConnection.setUserEtudiant(userEtudiant);
        return infoConnection;
    }

    @Override
    public void inscription(UserEtudiant userEtudiant) throws Exception {
        String jsonString = Utils.etudiantToJson(userEtudiant);
        URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        writer.write(jsonString);

        writer.flush();
        writer.close();
        out.close();
        int reponse = urlConnection.getResponseCode();
        switch (urlConnection.getResponseCode()) {
            case 400: throw new InscriptionInvalide();
            case 500: throw new ApiAccessException();
        }
        urlConnection.disconnect();

    }

    @Override
    public void editMe(AccessToken accessToken, UserEtudiant userEtudiant) throws Exception{
        String jsonString = Utils.etudiantToJson(userEtudiant);
        URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Authorization","Bearer "+accessToken.getAccessToken());
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setRequestMethod("PUT");
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        writer.write(jsonString);

        writer.flush();
        writer.close();
        out.close();
        String requet =urlConnection.getRequestMethod();
        int reponse = urlConnection.getResponseCode();
        switch (urlConnection.getResponseCode()) {
            case 404: throw new EtudiantDontExist();
            case 500: throw new ApiAccessException();
        }
        urlConnection.disconnect();

    }


}
