package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.Etudiant;

import java.io.IOException;

import retrofit2.Response;

public interface UserDataAccess {

	Response<Etudiant> getMe() throws IOException;
	Response<Void> inscription(Etudiant etudiant) throws IOException;
	Response<Void> editMe(Etudiant etudiant) throws IOException;
}
