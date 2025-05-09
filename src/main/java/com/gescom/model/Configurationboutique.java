package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Configurationboutique generated by hbm2java
 */
public class Configurationboutique implements java.io.Serializable {

	private int idconfiguration;
	private Boolean gestionstockavancee;
	private Boolean multiemplacement;
	private Boolean tracabilitelot;
	private Boolean gestionattributs;
	private Boolean multidevise;
	private Boolean multilangue;
	private Boolean locationproduits;
	private Boolean serviceapresvente;
	private Boolean programmeaffiliation;
	private Set boutiques = new HashSet(0);

	public Configurationboutique() {
	}

	public Configurationboutique(int idconfiguration) {
		this.idconfiguration = idconfiguration;
	}

	public Configurationboutique(int idconfiguration, Boolean gestionstockavancee, Boolean multiemplacement,
			Boolean tracabilitelot, Boolean gestionattributs, Boolean multidevise, Boolean multilangue,
			Boolean locationproduits, Boolean serviceapresvente, Boolean programmeaffiliation, Set boutiques) {
		this.idconfiguration = idconfiguration;
		this.gestionstockavancee = gestionstockavancee;
		this.multiemplacement = multiemplacement;
		this.tracabilitelot = tracabilitelot;
		this.gestionattributs = gestionattributs;
		this.multidevise = multidevise;
		this.multilangue = multilangue;
		this.locationproduits = locationproduits;
		this.serviceapresvente = serviceapresvente;
		this.programmeaffiliation = programmeaffiliation;
		this.boutiques = boutiques;
	}

	public int getIdconfiguration() {
		return this.idconfiguration;
	}

	public void setIdconfiguration(int idconfiguration) {
		this.idconfiguration = idconfiguration;
	}

	public Boolean getGestionstockavancee() {
		return this.gestionstockavancee;
	}

	public void setGestionstockavancee(Boolean gestionstockavancee) {
		this.gestionstockavancee = gestionstockavancee;
	}

	public Boolean getMultiemplacement() {
		return this.multiemplacement;
	}

	public void setMultiemplacement(Boolean multiemplacement) {
		this.multiemplacement = multiemplacement;
	}

	public Boolean getTracabilitelot() {
		return this.tracabilitelot;
	}

	public void setTracabilitelot(Boolean tracabilitelot) {
		this.tracabilitelot = tracabilitelot;
	}

	public Boolean getGestionattributs() {
		return this.gestionattributs;
	}

	public void setGestionattributs(Boolean gestionattributs) {
		this.gestionattributs = gestionattributs;
	}

	public Boolean getMultidevise() {
		return this.multidevise;
	}

	public void setMultidevise(Boolean multidevise) {
		this.multidevise = multidevise;
	}

	public Boolean getMultilangue() {
		return this.multilangue;
	}

	public void setMultilangue(Boolean multilangue) {
		this.multilangue = multilangue;
	}

	public Boolean getLocationproduits() {
		return this.locationproduits;
	}

	public void setLocationproduits(Boolean locationproduits) {
		this.locationproduits = locationproduits;
	}

	public Boolean getServiceapresvente() {
		return this.serviceapresvente;
	}

	public void setServiceapresvente(Boolean serviceapresvente) {
		this.serviceapresvente = serviceapresvente;
	}

	public Boolean getProgrammeaffiliation() {
		return this.programmeaffiliation;
	}

	public void setProgrammeaffiliation(Boolean programmeaffiliation) {
		this.programmeaffiliation = programmeaffiliation;
	}

	public Set getBoutiques() {
		return this.boutiques;
	}

	public void setBoutiques(Set boutiques) {
		this.boutiques = boutiques;
	}

}
