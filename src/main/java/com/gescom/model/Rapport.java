package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.sql.Date;

/**
 * Rapport generated by hbm2java
 */
public class Rapport implements java.io.Serializable {

	private int idrapport;
	private Archiveactiviteboutique archiveactiviteboutique;
	private String titre;
	private String type;
	private Date datecreation;
	private String contenu;
	private String format;

	public Rapport() {
	}

	public Rapport(int idrapport) {
		this.idrapport = idrapport;
	}

	public Rapport(int idrapport, Archiveactiviteboutique archiveactiviteboutique, String titre, String type,
			Date datecreation, String contenu, String format) {
		this.idrapport = idrapport;
		this.archiveactiviteboutique = archiveactiviteboutique;
		this.titre = titre;
		this.type = type;
		this.datecreation = datecreation;
		this.contenu = contenu;
		this.format = format;
	}

	public int getIdrapport() {
		return this.idrapport;
	}

	public void setIdrapport(int idrapport) {
		this.idrapport = idrapport;
	}

	public Archiveactiviteboutique getArchiveactiviteboutique() {
		return this.archiveactiviteboutique;
	}

	public void setArchiveactiviteboutique(Archiveactiviteboutique archiveactiviteboutique) {
		this.archiveactiviteboutique = archiveactiviteboutique;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDatecreation() {
		return this.datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
