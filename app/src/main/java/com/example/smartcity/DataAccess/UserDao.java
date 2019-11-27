package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.Etudiant;

public class UserDao implements UserDataAccess {
    @Override
    public Etudiant getMe(String mail, String motDePasse) throws EtudiantDontExist {
        if(mail.compareTo("maximedavister25@gmail.com") == 0){
            return new Etudiant("Davister","Maxime");
        }
        return null;
    }

    @Override
    public void inscription(Etudiant etudiant) throws InscriptionInvalide {

    }

    @Override
    public void editMe(Etudiant etudiant) {

    }
}
