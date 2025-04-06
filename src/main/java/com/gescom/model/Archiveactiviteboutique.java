package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Archiveactiviteboutique generated by hbm2java
 */
public class Archiveactiviteboutique implements java.io.Serializable {

	private int idlog;
	private Boutique boutique;
	private Date dateenregisrement;
	private String actioneffectuer;
	private String description;
	private Set rapports = new HashSet(0);

	public Archiveactiviteboutique() {
	}

	public Archiveactiviteboutique(int idlog) {
		this.idlog = idlog;
	}

	public Archiveactiviteboutique(int idlog, Boutique boutique, Date dateenregisrement, String actioneffectuer,
			String description, Set rapports) {
		this.idlog = idlog;
		this.boutique = boutique;
		this.dateenregisrement = dateenregisrement;
		this.actioneffectuer = actioneffectuer;
		this.description = description;
		this.rapports = rapports;
	}

	public int getIdlog() {
		return this.idlog;
	}

	public void setIdlog(int idlog) {
		this.idlog = idlog;
	}

	public Boutique getBoutique() {
		return this.boutique;
	}

	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}

	public Date getDateenregisrement() {
		return this.dateenregisrement;
	}

	public void setDateenregisrement(Date dateenregisrement) {
		this.dateenregisrement = dateenregisrement;
	}

	public String getActioneffectuer() {
		return this.actioneffectuer;
	}

	public void setActioneffectuer(String actioneffectuer) {
		this.actioneffectuer = actioneffectuer;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getRapports() {
		return this.rapports;
	}

	public void setRapports(Set rapports) {
		this.rapports = rapports;
	}

}
