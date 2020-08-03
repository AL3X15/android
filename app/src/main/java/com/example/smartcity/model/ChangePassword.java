package com.example.smartcity.model;

import java.io.Serializable;

public class ChangePassword implements Serializable {
	private String ancienPassword;
	private String password;
	private String confirmationPassword;
	private String RowVersion;

	public String getRowVersion() {
		return RowVersion;
	}

	public void setRowVersion(String rowVersion) {
		RowVersion = rowVersion;
	}

	public ChangePassword() {
	}

	public String getAncienPassword() {
		return ancienPassword;
	}

	public void setAncienPassword(String ancienPassword) {
		this.ancienPassword = ancienPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
}
