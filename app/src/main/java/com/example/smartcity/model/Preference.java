package com.example.smartcity.model;

public class Preference {
    private String email;
    private String password;

    public Preference(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDefaultPassword(){

        //todo demander a la prof comment obtenir les strings default
        return password.compareTo("defaultPassword") ==0;
    }
    public boolean isDefaultMail(){
        return email.compareTo("mailDefault") == 0;
    }
}
