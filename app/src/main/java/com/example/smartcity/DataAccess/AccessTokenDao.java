package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AccessTokenDao {
    public AccessToken getAccessToken(String username, String password) throws Exception{
        String jsonString = Utils.loginToJson(username,password);
        URL url = new URL("https://smartcityjober.azurewebsites.net/jwt");
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

        BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();
        return Utils.jsonToAccessToken(stringJSON);
    }
}
