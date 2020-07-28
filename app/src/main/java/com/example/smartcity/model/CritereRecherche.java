package com.example.smartcity.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class CritereRecherche implements Serializable {
	private GregorianCalendar dateDebut;
	private GregorianCalendar dateFin;


	public CritereRecherche() {
	}

	public GregorianCalendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(GregorianCalendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public GregorianCalendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(GregorianCalendar dateFin) {
		this.dateFin = dateFin;
	}

}
