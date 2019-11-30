package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.Etudiant;

public class UserDao implements UserDataAccess {
    Etudiant etudiant = new Etudiant("Davister", "Maxime");
    Etudiant etudiantCour = etudiant;

    @Override
    public Etudiant getMe(String mail, String motDePasse) throws EtudiantDontExist {
        if(etudiant.getMail().compareTo(mail) == 0 && etudiant.getPassword().compareTo(motDePasse)==0){
            return new Etudiant("Davister","Maxime");
    }
        throw new EtudiantDontExist();
    }

    @Override
    public void inscription(Etudiant etudiant) throws InscriptionInvalide {

    }

    @Override
    public void editMe(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
