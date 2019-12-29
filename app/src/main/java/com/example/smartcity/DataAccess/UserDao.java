package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;
import com.example.smartcity.model.UserEtudiant;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
        /*URL url = new URL("https://smartcityjober.azurewebsites.net/etudiant/");
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
        UserEtudiant userEtudiant =Utils.jsonToEtudiant(stringJSON);*/
        InfoConnection infoConnection = new InfoConnection();
        UserEtudiant userEtudiant =Utils.jsonToEtudiant("{\n" +
                "    \"userEtudiant\": {\n" +
                "        \"id\": 11,\n" +
                "        \"prenom\": \"maxime\",\n" +
                "        \"sexe\": \"M\",\n" +
                "        \"dateNaissance\": \"2000-01-11T00:00:00Z\",\n" +
                "        \"registreNational\": \"00.01.11.123\",\n" +
                "        \"expirationBadge\": \"0001-01-01T00:00:00\",\n" +
                "        \"adresse\": {\n" +
                "            \"id\": 13,\n" +
                "            \"rue\": \"rue de lonzee 206\",\n" +
                "            \"numero\": \"206\",\n" +
                "            \"localite\": {\n" +
                "                \"id\": 1,\n" +
                "                \"codePostal\": \"5030\",\n" +
                "                \"nom\": \"GEMBLOUX\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"tags\": []\n" +
                "    },\n" +
                "    \"id\": \"2562261d-3c20-4e7f-b038-6b2dd4c13351\",\n" +
                "    \"nom\": \"Davister\",\n" +
                "    \"nbSignalement\": 0,\n" +
                "    \"email\": \"billy@mail.com\",\n" +
                "    \"password\": null,\n" +
                "    \"confimrationPassword\": null,\n" +
                "    \"phoneNumber\": \"0476046664\"\n" +
                "}");
        infoConnection.setAccessToken(accessToken);
        infoConnection.setUserEtudiant(userEtudiant);
        return infoConnection;
    }

    @Override
    public void inscription(UserEtudiant userEtudiant) throws Exception {
        String jsonString = Utils.etudiantToJson(userEtudiant);
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
    public void editMe(AccessToken accessToken, UserEtudiant userEtudiant) throws Exception{
        String jsonString = Utils.etudiantToJson(userEtudiant);
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
