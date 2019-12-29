package com.example.smartcity.model;

import androidx.annotation.NonNull;

public class Localite {
    private Integer codePostal;
    private String localite;

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public Localite(Integer codePostal, String localite) {
        this.codePostal = codePostal;
        this.localite = localite;
    }

    public Localite() {
    }

    @NonNull
    @Override
    public String toString() {
        return getLocalite();
    }
}
