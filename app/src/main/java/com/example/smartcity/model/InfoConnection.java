package com.example.smartcity.model;

public class InfoConnection {
		private String username;
		private String password;

	public InfoConnection(String mail, String password) {
		setUsername(mail);
		setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String mail) {
		this.username = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
