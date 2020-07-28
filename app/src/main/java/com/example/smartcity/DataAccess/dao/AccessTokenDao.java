package com.example.smartcity.DataAccess.dao;

public class AccessTokenDao {
    /*
    public AccessToken getAccessToken(String username, String password) throws Exception{

        String jsonString = Utils.loginToJson(username,password);
        URL url = new URL("https://smartcityjober.azurewebsites.net/jwt");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        writer.write(jsonString);

        writer.flush();
        writer.close();
        out.close();

        switch (urlConnection.getResponseCode()) {
            case 400: throw new LoginException();
            case 500: throw new ApiAccessException();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON, line;
        while((line = buffer.readLine())!=null)
            builder.append(line);
        buffer.close();
        stringJSON = builder.toString();

        urlConnection.disconnect();

        return Utils.jsonToAccessToken(stringJSON);
    }
*/

}
