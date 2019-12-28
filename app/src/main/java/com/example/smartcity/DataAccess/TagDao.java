package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.AnnonceDontExist;
import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.NoTag;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class TagDao implements TagDataAccess {

    public ArrayList<Tag> getAllTag(AccessToken accessToken)throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/tag");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken);
        switch (connection.getResponseCode()) {
            case 500: throw new ApiAccessException();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();
        return Utils.jsonToTags(stringJSON);
    }
    public ArrayList<Tag> getTagsAnnonce(AccessToken accessToken, Annonce annonce)throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/tag/annonce/"+annonce.getId());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken);
        switch (connection.getResponseCode()) {
            case 404: throw new AnnonceDontExist();
            case 500: throw new ApiAccessException();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();
        return Utils.jsonToTags(stringJSON);
    }
    public ArrayList<Tag>getTagsEtudiant(AccessToken accessToken, Etudiant etudiant)throws Exception{
        URL url = new URL("https://smartcityjober.azurewebsites.net/tag/etudiant/"+etudiant.getId());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Bearer"+accessToken);
        switch (connection.getResponseCode()) {
            case 400: throw new NoTag();
            case 500: throw new ApiAccessException();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();
        return Utils.jsonToTags(stringJSON);
    }


}
