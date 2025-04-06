package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

/**
 * Typecollaborateur generated by hbm2java
 */
public class Typecollaborateur implements java.io.Serializable {

	private int idtc;
	private Collaborateur collaborateur;
	private String nom;
	private String valeur;
	private String description;

	public Typecollaborateur() {
	}

	public Typecollaborateur(int idtc) {
		this.idtc = idtc;
	}

	public Typecollaborateur(int idtc, Collaborateur collaborateur, String nom, String valeur, String description) {
		this.idtc = idtc;
		this.collaborateur = collaborateur;
		this.nom = nom;
		this.valeur = valeur;
		this.description = description;
	}

	public int getIdtc() {
		return this.idtc;
	}

	public void setIdtc(int idtc) {
		this.idtc = idtc;
	}

	public Collaborateur getCollaborateur() {
		return this.collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
