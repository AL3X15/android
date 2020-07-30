package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.UserEntreprise;

import java.io.IOException;

import retrofit2.Response;

public interface EntrepriseDataAccess {

	Response<UserEntreprise> getEntrepriseByAnnonce(int id) throws IOException;
}
