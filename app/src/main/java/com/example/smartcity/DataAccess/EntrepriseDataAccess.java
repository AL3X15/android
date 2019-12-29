package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.UserEntreprise;


public interface EntrepriseDataAccess {
    public UserEntreprise getEntrepriseByAnnonce(String accessToken, Annonce annonce)throws Exception;
}
