package com.example.smartcity.model;

import java.io.Serializable;
import java.util.Date;

public class CritereRecherche implements Serializable {
	private Date dateDebut;
	private Date dateFin;


	public CritereRecherche() {
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
