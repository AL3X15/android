package com.example.smartcity.model;

public class Faq {
	private String question;
	private String reponse;

	public Faq(String question, String reponse) {
		this.question = question;
		this.reponse = reponse;
	}

	public Faq() {
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

}
