package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Preference generated by hbm2java
 */
public class Preference implements java.io.Serializable {

	private int idpreference;
	private String cle;
	private String valeur;
	private String type;
	private Set clients = new HashSet(0);

	public Preference() {
	}

	public Preference(int idpreference) {
		this.idpreference = idpreference;
	}

	public Preference(int idpreference, String cle, String valeur, String type, Set clients) {
		this.idpreference = idpreference;
		this.cle = cle;
		this.valeur = valeur;
		this.type = type;
		this.clients = clients;
	}

	public int getIdpreference() {
		return this.idpreference;
	}

	public void setIdpreference(int idpreference) {
		this.idpreference = idpreference;
	}

	public String getCle() {
		return this.cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getClients() {
		return this.clients;
	}

	public void setClients(Set clients) {
		this.clients = clients;
	}

}
