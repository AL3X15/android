package com.example.smartcity.model;

import java.util.GregorianCalendar;

public class AccessToken {
	private GregorianCalendar dateExpiration;
	private String access_token;

	public GregorianCalendar getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(GregorianCalendar dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getAccessToken() {
		return access_token;
	}

	public void setAccessToken(String accessToken) {
		this.access_token = accessToken;
	}
}
