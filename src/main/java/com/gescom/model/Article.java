package com.gescom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(name = "code_barre", length = 50)
    private String codeBarre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id")
    private CategorieArticle categorie;

    @Column(name = "stock_disponible")
    private Integer stockDisponible = 0;

    @Column(name = "stock_alerte")
    private Integer stockAlerte = 5;

    @Column(name = "prix_achat", precision = 10, scale = 2)
    private BigDecimal prixAchat;

    @Column(name = "prix_vente_ht", precision = 10, scale = 2)
    private BigDecimal prixVenteHT;

    @Column(name = "taux_tva", precision = 5, scale = 2)
    private BigDecimal tauxTVA = new BigDecimal("20.00");

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation")
    private Date dateCreation = new Date();

    @Column(length = 20)
    private String etat = "ACTIF";

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleVariante> variantes = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public CategorieArticle getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieArticle categorie) {
		this.categorie = categorie;
	}

	public Integer getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(Integer stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	public Integer getStockAlerte() {
		return stockAlerte;
	}

	public void setStockAlerte(Integer stockAlerte) {
		this.stockAlerte = stockAlerte;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public BigDecimal getPrixVenteHT() {
		return prixVenteHT;
	}

	public void setPrixVenteHT(BigDecimal prixVenteHT) {
		this.prixVenteHT = prixVenteHT;
	}

	public BigDecimal getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(BigDecimal tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public List<ArticleVariante> getVariantes() {
		return variantes;
	}

	public void setVariantes(List<ArticleVariante> variantes) {
		this.variantes = variantes;
	}

    // Getters, setters, constructeurs...
}