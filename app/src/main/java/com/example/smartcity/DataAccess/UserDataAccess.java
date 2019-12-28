package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Etudiant;

public interface UserDataAccess {
    public void inscription(Etudiant etudiant) throws Exception;
    public Etudiant getMe(String mail,String motDePasse) throws Exception;
    public void editMe(AccessToken accessToken, Etudiant etudiant)throws Exception;
}
