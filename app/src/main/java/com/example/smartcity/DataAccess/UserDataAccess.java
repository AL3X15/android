package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.Etudiant;

public interface UserDataAccess {
    public void inscription(Etudiant etudiant) throws InscriptionInvalide;
    public Etudiant getMe(String mail,String motDePasse) throws EtudiantDontExist;
    public void editMe(Etudiant etudiant);
}
