package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Periode generated by hbm2java
 */
public class Periode implements java.io.Serializable {

	private int idperiode;
	private Date datedebut;
	private Date datefin;
	private Set fluctuationprixes = new HashSet(0);
	private Set promotions = new HashSet(0);

	public Periode() {
	}

	public Periode(int idperiode) {
		this.idperiode = idperiode;
	}

	public Periode(int idperiode, Date datedebut, Date datefin, Set fluctuationprixes, Set promotions) {
		this.idperiode = idperiode;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.fluctuationprixes = fluctuationprixes;
		this.promotions = promotions;
	}

	public int getIdperiode() {
		return this.idperiode;
	}

	public void setIdperiode(int idperiode) {
		this.idperiode = idperiode;
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

	public Set getFluctuationprixes() {
		return this.fluctuationprixes;
	}

	public void setFluctuationprixes(Set fluctuationprixes) {
		this.fluctuationprixes = fluctuationprixes;
	}

	public Set getPromotions() {
		return this.promotions;
	}

	public void setPromotions(Set promotions) {
		this.promotions = promotions;
	}

}
