package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.UserEtudiant;

import java.io.IOException;

import retrofit2.Response;

public interface UserDataAccess {

	Response<UserEtudiant> getMe() throws IOException;
	Response<Void> inscription(UserEtudiant userEtudiant) throws IOException;
	Response<Void> editMe(UserEtudiant userEtudiant) throws IOException;

	/*
	public void inscription(UserEtudiant userEtudiant) throws Exception;
	public InfoConnection getMe(String mail, String motDePasse) throws Exception;
	public InfoConnection getMe(String mail, AccessToken accessToken) throws Exception;
	public void editMe(AccessToken accessToken, UserEtudiant userEtudiant) throws Exception;
	 */
}
