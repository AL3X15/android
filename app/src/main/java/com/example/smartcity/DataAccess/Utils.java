package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Entreprise;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Faq;
import com.example.smartcity.model.Tag;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {
    public static String etudiantToJson(Etudiant etudiant){
        return "";
    }

    public static Etudiant jsonToEtudiant (String json) throws Exception{
        Gson object = new GsonBuilder().create();
        return object.fromJson(json,Etudiant.class);
    }

    public static Entreprise jsonToEntreprise (String json) throws Exception{
        Gson object = new GsonBuilder().create();
        return object.fromJson(json,Entreprise.class);
    }

    public static ArrayList<Tag> jsonToTags (String json) throws Exception{
        ArrayList<Tag> tags = new ArrayList<Tag>();
        Tag tag= null;
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0 ; i < jsonArray.length();i++){
            JSONObject jsonTag = jsonArray.getJSONObject(i);
            Gson object = new GsonBuilder().create();
            tag = object.fromJson(jsonTag.toString(),Tag.class);
            tags.add(tag);
        }
        return tags;
    }

    public static String postulationToJson (int idAnnonce, int idEtudiant){
        return "";
    }

    public static ArrayList<Annonce> jsonToAnnonces (String json) throws Exception{
        ArrayList<Annonce> annonces = new ArrayList<Annonce>();
        Annonce annonce= null;
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0 ; i < jsonArray.length();i++){
            JSONObject jsonAnnonce = jsonArray.getJSONObject(i);
            Gson object = new GsonBuilder().create();
            annonce = object.fromJson(jsonAnnonce.toString(),Annonce.class);
            annonces.add(annonce);
        }
        return annonces;
    }

    public static ArrayList<Faq> jsonToFaqs(String json) throws Exception{
        ArrayList<Faq> faqs = new ArrayList<Faq>();
        Faq faq= null;
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0 ; i < jsonArray.length();i++){
            JSONObject jsonFaq = jsonArray.getJSONObject(i);
            Gson object = new GsonBuilder().create();
            faq = object.fromJson(jsonFaq.toString(),Faq.class);
            faqs.add(faq);
        }
        return faqs;
    }

    public static String CalendarToString(GregorianCalendar calendar){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDate = calendar.getGregorianChange();
        return dateFormat.format(dateDate);
    }

    public static String loginToJson(String username, String password){
        return "{\n" +
                "\t\"username\":\""+username+"\",\n" +
                "\t\"Password\":\""+password+"\"\n" +
                "}";
    }
    public static AccessToken jsonToAccessToken(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(jsonObject.getString("access_token"));
        GregorianCalendar expiration = new GregorianCalendar();
        expiration.add(Calendar.SECOND,jsonObject.getInt("expires_in"));
        accessToken.setDateExpiration(expiration);
        return accessToken;
    }
}
