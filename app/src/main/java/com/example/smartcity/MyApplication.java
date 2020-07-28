package com.example.smartcity;

import android.app.Application;

import com.example.smartcity.model.InfoEtudiant;

public class MyApplication extends Application {
	private InfoEtudiant infoEtudiant;
	private static MyApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public InfoEtudiant getInfoConnection() {
		return infoEtudiant;
	}

	public void setInfoEtudiant(InfoEtudiant infoEtudiant) {
		this.infoEtudiant = infoEtudiant;
	}

	public static MyApplication getInstance() {
		return instance;
	}
}
/*
1   l'email est déja utilisé
2   le numero de ligne doit être strictement positif
3   La localité n'existe pas
4   Email ou mot de passe incorrect
5   postulation déjà existante
6   Etudiant sans tag
7   le sexe n'existe pas
8   l'étudiant doit être majeur
9   l'événement ne peut pas être dans le passé
10  la date de début ne peut pas être après la date de fin
11  il n'y a que les entreprises premium qui peuvent poster des annonces urgentes
 */