package com.example.smartcity.model;

import java.util.GregorianCalendar;

public class AccessToken {
	private GregorianCalendar dateExpiration;
	private String accessToken;

	public GregorianCalendar getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(GregorianCalendar dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
