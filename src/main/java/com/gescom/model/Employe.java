package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employe generated by hbm2java
 */
public class Employe implements java.io.Serializable {

	private int idemploye;
	private Contrat contrat;
	private Patronboutique patronboutique;
	private Poste poste;
	private String poste_1;
	private Date dateembauche;
	private String contrat_1;
	private Byte salaire;
	private String statutemploye;
	private Set caisses = new HashSet(0);
	private Set stocks = new HashSet(0);
	private Set conges = new HashSet(0);
	private Set approvisionnements = new HashSet(0);
	private Set ventes = new HashSet(0);
	private Set horairetravails = new HashSet(0);

	public Employe() {
	}

	public Employe(int idemploye) {
		this.idemploye = idemploye;
	}

	public Employe(int idemploye, Contrat contrat, Patronboutique patronboutique, Poste poste, String poste_1,
			Date dateembauche, String contrat_1, Byte salaire, String statutemploye, Set caisses, Set stocks,
			Set conges, Set approvisionnements, Set ventes, Set horairetravails) {
		this.idemploye = idemploye;
		this.contrat = contrat;
		this.patronboutique = patronboutique;
		this.poste = poste;
		this.poste_1 = poste_1;
		this.dateembauche = dateembauche;
		this.contrat_1 = contrat_1;
		this.salaire = salaire;
		this.statutemploye = statutemploye;
		this.caisses = caisses;
		this.stocks = stocks;
		this.conges = conges;
		this.approvisionnements = approvisionnements;
		this.ventes = ventes;
		this.horairetravails = horairetravails;
	}

	public int getIdemploye() {
		return this.idemploye;
	}

	public void setIdemploye(int idemploye) {
		this.idemploye = idemploye;
	}

	public Contrat getContrat() {
		return this.contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Patronboutique getPatronboutique() {
		return this.patronboutique;
	}

	public void setPatronboutique(Patronboutique patronboutique) {
		this.patronboutique = patronboutique;
	}

	public Poste getPoste() {
		return this.poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public String getPoste_1() {
		return this.poste_1;
	}

	public void setPoste_1(String poste_1) {
		this.poste_1 = poste_1;
	}

	public Date getDateembauche() {
		return this.dateembauche;
	}

	public void setDateembauche(Date dateembauche) {
		this.dateembauche = dateembauche;
	}

	public String getContrat_1() {
		return this.contrat_1;
	}

	public void setContrat_1(String contrat_1) {
		this.contrat_1 = contrat_1;
	}

	public Byte getSalaire() {
		return this.salaire;
	}

	public void setSalaire(Byte salaire) {
		this.salaire = salaire;
	}

	public String getStatutemploye() {
		return this.statutemploye;
	}

	public void setStatutemploye(String statutemploye) {
		this.statutemploye = statutemploye;
	}

	public Set getCaisses() {
		return this.caisses;
	}

	public void setCaisses(Set caisses) {
		this.caisses = caisses;
	}

	public Set getStocks() {
		return this.stocks;
	}

	public void setStocks(Set stocks) {
		this.stocks = stocks;
	}

	public Set getConges() {
		return this.conges;
	}

	public void setConges(Set conges) {
		this.conges = conges;
	}

	public Set getApprovisionnements() {
		return this.approvisionnements;
	}

	public void setApprovisionnements(Set approvisionnements) {
		this.approvisionnements = approvisionnements;
	}

	public Set getVentes() {
		return this.ventes;
	}

	public void setVentes(Set ventes) {
		this.ventes = ventes;
	}

	public Set getHorairetravails() {
		return this.horairetravails;
	}

	public void setHorairetravails(Set horairetravails) {
		this.horairetravails = horairetravails;
	}

}
