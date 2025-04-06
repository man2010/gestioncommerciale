package com.gescom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "famille")
public class Famille implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Famille parent;
    
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    @Column(name = "code", length = 10)
    private String code;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation")
    private Date dateCreation;
    
    @Column(name = "etat", length = 20)
    private String etat = "ACTIF";
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Famille> sousFamilles = new HashSet<>();

    public Famille() {
        this.dateCreation = new Date();
    }

    // Getters et Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Famille getParent() { return parent; }
    public void setParent(Famille parent) { this.parent = parent; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }  
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
    public Set<Famille> getSousFamilles() { return sousFamilles; }
    public void setSousFamilles(Set<Famille> sousFamilles) { this.sousFamilles = sousFamilles; }
}