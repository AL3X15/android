package com.example.smartcity.model;

public class Preference {
    private String email;
    private AccessToken accessToken;

    public Preference(String email,AccessToken accessToken) {
        setEmail(email);
        setAccessToken(accessToken);
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDefaultMail(){
        return email.compareTo("mailDefault") == 0;
    }
}
