package com.example.smartcity.model;

public class Preference {
    private String email;

    public Preference(String email) {
        setEmail(email);
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
