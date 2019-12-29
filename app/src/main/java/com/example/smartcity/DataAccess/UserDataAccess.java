package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;
import com.example.smartcity.model.UserEtudiant;

public interface UserDataAccess {
    public void inscription(UserEtudiant userEtudiant) throws Exception;
    public InfoConnection getMe(String mail, String motDePasse) throws Exception;
    public InfoConnection getMe(String mail, AccessToken accessToken) throws Exception;
    public void editMe(AccessToken accessToken, UserEtudiant userEtudiant)throws Exception;
}
