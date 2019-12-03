package com.example.smartcity;

import android.app.Application;

import com.example.smartcity.model.Etudiant;

public class MyApplication extends Application {
    private Etudiant etudiant;

    public Etudiant getEtudiant(){
        return etudiant;
    }

    public void setEtudiant (Etudiant etudiant){
        this.etudiant = etudiant;
    }
}
