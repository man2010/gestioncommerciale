package com.gescom.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilisateur")
    private int idutilisateur;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idtypeutilisateur")
//    private Typeutilisateur typeutilisateur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idrole", nullable = false)
    private Role role;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "motpass", nullable = false, length = 255)
    private String motpass;

    @Column(name = "datecreation")
    private Date datecreation;

    @Column(name = "derniereconnexion")
    private Date derniereconnexion;

    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "codepostale", length = 10)
    private String codepostale;

    @Column(name = "actif")
    private Boolean actif = true;

    @Column(name = "photo", length = 255)
    private String photo;

//    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Compteutilisateur> compteutilisateurs = new HashSet<>(0);
//
//    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Pays> payses = new HashSet<>(0);
//
//    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Collaborateur> collaborateurs = new HashSet<>(0);
//
//    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Notifications> notificationses = new HashSet<>(0);
//
//    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Session> sessions = new HashSet<>(0);
//
//    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Villes> villeses = new HashSet<>(0);

    public Utilisateur() {
    }

    public Utilisateur(int idutilisateur, Role role) {
        this.idutilisateur = idutilisateur;
        this.role = role;
    }

    // Getters
    public int getIdutilisateur() {
        return idutilisateur;
    }

//    public Typeutilisateur getTypeutilisateur() {
//        return typeutilisateur;
//    }

    public Role getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMotpass() {
        return motpass;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public Date getDerniereconnexion() {
        return derniereconnexion;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodepostale() {
        return codepostale;
    }

    public Boolean getActif() {
        return actif;
    }

    public String getPhoto() {
        return photo;
    }

//    public Set<Compteutilisateur> getCompteutilisateurs() {
//        return compteutilisateurs;
//    }
//
//    public Set<Pays> getPayses() {
//        return payses;
//    }
//
//    public Set<Collaborateur> getCollaborateurs() {
//        return collaborateurs;
//    }
//
//    public Set<Notifications> getNotificationses() {
//        return notificationses;
//    }
//
//    public Set<Session> getSessions() {
//        return sessions;
//    }
//
//    public Set<Villes> getVilleses() {
//        return villeses;
//    }

    // Setters
    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

//    public void setTypeutilisateur(Typeutilisateur typeutilisateur) {
//        this.typeutilisateur = typeutilisateur;
//    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMotpass(String motpass) {
        this.motpass = motpass;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public void setDerniereconnexion(Date derniereconnexion) {
        this.derniereconnexion = derniereconnexion;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodepostale(String codepostale) {
        this.codepostale = codepostale;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

//    public void setCompteutilisateurs(Set<Compteutilisateur> compteutilisateurs) {
//        this.compteutilisateurs = compteutilisateurs;
//    }
//
//    public void setPayses(Set<Pays> payses) {
//        this.payses = payses;
//    }
//
//    public void setCollaborateurs(Set<Collaborateur> collaborateurs) {
//        this.collaborateurs = collaborateurs;
//    }
//
//    public void setNotificationses(Set<Notifications> notificationses) {
//        this.notificationses = notificationses;
//    }
//
//    public void setSessions(Set<Session> sessions) {
//        this.sessions = sessions;
//    }
//
//    public void setVilleses(Set<Villes> villeses) {
//        this.villeses = villeses;
//    }

//    // Méthodes utilitaires pour les relations
//    public void addCompteutilisateur(Compteutilisateur compteutilisateur) {
//        this.compteutilisateurs.add(compteutilisateur);
//        compteutilisateur.setUtilisateur(this);
//    }
//
//    public void removeCompteutilisateur(Compteutilisateur compteutilisateur) {
//        this.compteutilisateurs.remove(compteutilisateur);
//        compteutilisateur.setUtilisateur(null);
//    }

    // Méthodes similaires pour les autres relations...

    @Override
    public String toString() {
        return "Utilisateur [idUtilisateur=" + idutilisateur + ", idRole=" + role + ", nom=" + nom 
               + ", prenom=" + prenom + ", email=" + email + "]";
    }
}