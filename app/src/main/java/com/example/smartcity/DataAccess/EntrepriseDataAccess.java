package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Entreprise;

import java.util.ArrayList;

public interface EntrepriseDataAccess {
    public Entreprise getEntrepriseByAnnonce(String accessToken,Annonce annonce)throws Exception;
}
