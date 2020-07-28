package com.example.smartcity.model;

public class InfoEtudiant {
	private UserEtudiant userEtudiant;
	private AccessToken accessToken;

	public UserEtudiant getUserEtudiant() {
		return userEtudiant;
	}

	public void setUserEtudiant(UserEtudiant userEtudiant) {
		this.userEtudiant = userEtudiant;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
}
