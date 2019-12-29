package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.UserEntreprise;
import com.example.smartcity.model.UserEtudiant;
import com.example.smartcity.model.Faq;
import com.example.smartcity.model.Tag;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Utils {
    public static String etudiantToJson(UserEtudiant userEtudiant){
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
       return gson.toJson(userEtudiant);
    }

    public static UserEtudiant jsonToEtudiant (String json) throws Exception{
        JSONObject jsonObject = new JSONObject(json);
        Gson object = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return object.fromJson(jsonObject.toString(), UserEtudiant.class);
    }

    public static UserEntreprise jsonToEntreprise (String json) {
        Gson object = new GsonBuilder().create();
        return object.fromJson(json, UserEntreprise.class);
    }

    public static ArrayList<Tag> jsonToTags (String json) throws Exception{
        ArrayList<Tag> tags = new ArrayList<Tag>();
        Tag tag;
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
        return "{\"annonceId\" : "+idAnnonce+",\n" +
                "\"etudiantId\" : "+idEtudiant+"}";
    }

    public static ArrayList<Annonce> jsonToAnnonces (String json) throws Exception{
        ArrayList<Annonce> annonces = new ArrayList<Annonce>();
        Annonce annonce;
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0 ; i < jsonArray.length();i++){
            JSONObject jsonAnnonce = jsonArray.getJSONObject(i);
            Gson object = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            annonce = object.fromJson(jsonAnnonce.toString(),Annonce.class);
            annonces.add(annonce);
        }
        return annonces;
    }

    public static ArrayList<Faq> jsonToFaqs(String json) throws Exception{
        ArrayList<Faq> faqs = new ArrayList<Faq>();
        Faq faq;
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
        java.util.Date dateDate = calendar.getTime();
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
