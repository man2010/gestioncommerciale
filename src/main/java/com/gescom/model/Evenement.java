package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.sql.Date;

/**
 * Evenement generated by hbm2java
 */
public class Evenement implements java.io.Serializable {

	private int idevenement;
	private Boutique boutique;
	private String titre;
	private String description;
	private Date datedebut;
	private Date datefin;
	private String type;
	private String statut;

	public Evenement() {
	}

	public Evenement(int idevenement) {
		this.idevenement = idevenement;
	}

	public Evenement(int idevenement, Boutique boutique, String titre, String description, Date datedebut, Date datefin,
			String type, String statut) {
		this.idevenement = idevenement;
		this.boutique = boutique;
		this.titre = titre;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.type = type;
		this.statut = statut;
	}

	public int getIdevenement() {
		return this.idevenement;
	}

	public void setIdevenement(int idevenement) {
		this.idevenement = idevenement;
	}

	public Boutique getBoutique() {
		return this.boutique;
	}

	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatedebut() {
		return this.datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return this.datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}
