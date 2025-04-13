package com.gescom.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "catalogue")
public class Catalogue implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcatalogue")
    private int idcatalogue;
    
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    @Column(name = "code", unique = true, length = 50)
    private String code;
    
    @Column(name = "datecreation")
    private Date datecreation;
    
    @Column(name = "etat", length = 20)
    private String etat;
    
    @Column(name = "description", length = 500)
    private String description;
    
//    @OneToMany(mappedBy = "catalogue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Produit> produits = new HashSet<>(0);

    // Constructeurs
    public Catalogue() {
    }

    public Catalogue(String nom, String code, Date datecreation, String etat, String description) {
        this.nom = nom;
        this.code = code;
        this.datecreation = datecreation;
        this.etat = etat;
        this.description = description;
    }

    // Getters et Setters
    // ... (identique à l'original mais avec annotations JPA déjà présentes)
    public int getIdcatalogue() {
        return this.idcatalogue;
    }

    public void setIdcatalogue(int idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDatecreation() {
        return this.datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//
//    public Set<Produit> getProduits() {
//        return this.produits;
//    }
//
//    public void setProduits(Set<Produit> produits) {
//        this.produits = produits;
//    }
}