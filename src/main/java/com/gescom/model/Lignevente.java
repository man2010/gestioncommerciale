package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Lignevente generated by hbm2java
 */
public class Lignevente implements java.io.Serializable {

	private int id;
	private Integer quantite;
	private Byte prixu;
	private Byte montant;
	private Set ventes = new HashSet(0);
	private Set articles = new HashSet(0);
	private Set produits = new HashSet(0);

	public Lignevente() {
	}

	public Lignevente(int id) {
		this.id = id;
	}

	public Lignevente(int id, Integer quantite, Byte prixu, Byte montant, Set ventes, Set articles, Set produits) {
		this.id = id;
		this.quantite = quantite;
		this.prixu = prixu;
		this.montant = montant;
		this.ventes = ventes;
		this.articles = articles;
		this.produits = produits;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Byte getPrixu() {
		return this.prixu;
	}

	public void setPrixu(Byte prixu) {
		this.prixu = prixu;
	}

	public Byte getMontant() {
		return this.montant;
	}

	public void setMontant(Byte montant) {
		this.montant = montant;
	}

	public Set getVentes() {
		return this.ventes;
	}

	public void setVentes(Set ventes) {
		this.ventes = ventes;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public Set getProduits() {
		return this.produits;
	}

	public void setProduits(Set produits) {
		this.produits = produits;
	}

}
