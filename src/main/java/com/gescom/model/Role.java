package com.gescom.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole")
    private int idrole;

    @Column(name = "nomrole", nullable = false, length = 50)
    private String nomrole;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "datecreation")
    private Date datecreation;

//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Permission> permissions = new HashSet<>(0);

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Utilisateur> utilisateurs = new HashSet<>(0);

    public Role() {
    }

    public Role(int idrole) {
        this.idrole = idrole;
    }

    public Role(int idrole, String nomrole, String description, Date datecreation, Set<Utilisateur> utilisateurs) {
        this.idrole = idrole;
        this.nomrole = nomrole;
        this.description = description;
        this.datecreation = datecreation;
       
        this.utilisateurs = utilisateurs;
    }

    // Getters
    public int getIdrole() {
        return idrole;
    }

    public String getNomrole() {
        return nomrole;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    
    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    // Setters
    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public void setNomrole(String nomrole) {
        this.nomrole = nomrole;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

//    public void addPermission(Permission permission) {
//        this.permissions.add(permission);
//        permission.setRole(this);
//    }
//
//    public void removePermission(Permission permission) {
//        this.permissions.remove(permission);
//        permission.setRole(null);
//    }

    public void addUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
        utilisateur.setRole(this);
    }

    public void removeUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        utilisateur.setRole(null);
    }

    @Override
    public String toString() {
        return "Role [idRole=" + idrole + ", nomRole=" + nomrole + ", description=" + description + "]";
    }
}