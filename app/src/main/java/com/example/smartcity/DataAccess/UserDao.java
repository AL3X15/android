package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.Etudiant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UserDao implements UserDataAccess {
    Etudiant etudiant = new Etudiant("Davister", "Maxime");
    Etudiant etudiantCour = etudiant;


    @Override
    public Etudiant getMe(String mail, String motDePasse) throws EtudiantDontExist {
        /*
        try {
            URL url = new URL("");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String stringJSON = "", line;
            while ((line = buffer.readLine()) != null) {
                builder.append(line.toString());
            }
            buffer.close();
            stringJSON = builder.toString();
            return jsonToEtudiant(stringJSON).get(0);
        }catch (Exception e){}
        */
        if(etudiant.getMail().compareTo(mail) == 0 && etudiant.getPassword().compareTo(motDePasse)==0){
            return new Etudiant("Davister","Maxime");
        }
        throw new EtudiantDontExist();
    }

    @Override
    public void inscription(Etudiant etudiant) throws InscriptionInvalide {
        //etudiantToJson(etudiant);
    }

    @Override
    public void editMe(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public ArrayList<Etudiant> jsonToEtudiant(String json)throws Exception{
        ArrayList<Etudiant> persons = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i <jsonArray.length();i++){
            JSONObject jsonPerson = jsonArray.getJSONObject(i);
            Gson object = new GsonBuilder().create();
            Etudiant person = object.fromJson(jsonPerson.toString(),Etudiant.class);

        }
        return persons;
    }

    public String etudiantToJson(Etudiant etudiant)throws Exception{
        Gson object = new GsonBuilder().create();
        return object.toJson(etudiant);
    }


}
