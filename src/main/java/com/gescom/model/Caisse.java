package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Caisse generated by hbm2java
 */
public class Caisse implements java.io.Serializable {

	private CaisseId id;
	private Boutique boutique;
	private Employe employe;
	private Boolean actif;
	private Date dateactivation;
	private String typecaisse;
	private Byte montantcaisse;
	private Set sessioncaisses = new HashSet(0);

	public Caisse() {
	}

	public Caisse(CaisseId id) {
		this.id = id;
	}

	public Caisse(CaisseId id, Boutique boutique, Employe employe, Boolean actif, Date dateactivation,
			String typecaisse, Byte montantcaisse, Set sessioncaisses) {
		this.id = id;
		this.boutique = boutique;
		this.employe = employe;
		this.actif = actif;
		this.dateactivation = dateactivation;
		this.typecaisse = typecaisse;
		this.montantcaisse = montantcaisse;
		this.sessioncaisses = sessioncaisses;
	}

	public CaisseId getId() {
		return this.id;
	}

	public void setId(CaisseId id) {
		this.id = id;
	}

	public Boutique getBoutique() {
		return this.boutique;
	}

	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}

	public Employe getEmploye() {
		return this.employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Boolean getActif() {
		return this.actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Date getDateactivation() {
		return this.dateactivation;
	}

	public void setDateactivation(Date dateactivation) {
		this.dateactivation = dateactivation;
	}

	public String getTypecaisse() {
		return this.typecaisse;
	}

	public void setTypecaisse(String typecaisse) {
		this.typecaisse = typecaisse;
	}

	public Byte getMontantcaisse() {
		return this.montantcaisse;
	}

	public void setMontantcaisse(Byte montantcaisse) {
		this.montantcaisse = montantcaisse;
	}

	public Set getSessioncaisses() {
		return this.sessioncaisses;
	}

	public void setSessioncaisses(Set sessioncaisses) {
		this.sessioncaisses = sessioncaisses;
	}

}
