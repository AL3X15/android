package com.example.smartcity.model;

public class InfoConnection {
		private String mail;
		private String password;

	public InfoConnection(String mail, String password) {
		setMail(mail);
		setPassword(password);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
