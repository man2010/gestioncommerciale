package com.gescom.beans;

import com.gescom.dao.UtilisateurHome;
import com.gescom.model.Utilisateur;
import com.gescom.utils.SpringUtils;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="utilisateurBean")
@SessionScoped
public class UtilisateurBean {
	
	/**/
	
	private List<Utilisateur> utilisateurs;
    private Utilisateur selectedUser;
	
	/**/
    
    private Utilisateur utilisateur;
    private String confirmPassword;
    private Integer selectedRoleId;
    private UtilisateurHome utilisateurHome;
    
   
    public UtilisateurBean() {
        try {
        	
            this.utilisateur = (Utilisateur) SpringUtils.getContext().getBean("utilisateur");
            this.utilisateurHome = (UtilisateurHome) SpringUtils.getContext().getBean("utilisateurhome");
            /**/
            utilisateurs = utilisateurHome.findAll();
            /**/
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur d'initialisation", "Impossible de charger les dépendances"));
            // Initialisation par défaut
            this.utilisateur = new Utilisateur();
        }
    }
    public String register() {
        String result = utilisateurHome.register(utilisateur, confirmPassword, selectedRoleId);
        if (result == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Inscription réussie!"));
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", result));
            return null;
        }
    }
    
    public String connexion() {
        Utilisateur user = utilisateurHome.connexion(
            utilisateur.getEmail(), 
            utilisateur.getMotpass()
        );
        
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("utilisateur", user);
            
            // Redirection selon le rôle
            String nomRole = user.getRole().getNomrole().toLowerCase();
            switch(nomRole) {
                case "admin":
                    return "pageadmin?faces-redirect=true";
                case "vendeur":
                    return "pagevendeur?faces-redirect=true";
                case "client":
                    return "pageclient?faces-redirect=true";
                default:
                    return "dashboard?faces-redirect=true";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erreur", "Email ou mot de passe incorrect"));
            return null;
        }
    }
    /**/
    
    
    public void prepareCreate() {
        selectedUser = new Utilisateur();
        selectedUser.setActif(true);
        selectedUser.setDatecreation(new java.sql.Date(System.currentTimeMillis()));
    }
    
    public void prepareEdit(Utilisateur user) {
        selectedUser = user;
    }
    
    public void saveUser() {
        if (selectedUser.getIdutilisateur() == 0) {
            utilisateurHome.persist(selectedUser);
        } else {
            utilisateurHome.update(selectedUser);
        }
        utilisateurs = utilisateurHome.findAll();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Utilisateur enregistré avec succès"));
    }
    
    public void deleteUser() {
        utilisateurHome.delete(selectedUser.getIdutilisateur());
        utilisateurs = utilisateurHome.findAll();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Utilisateur supprimé avec succès"));
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    
    public int getCountUtilisateurs() {
        return utilisateurs != null ? utilisateurs.size() : 0;
    }
    
    // Getters/Setters supplémentaires
    public List<Utilisateur> getUtilisateurs() { return utilisateurs; }
    public Utilisateur getSelectedUser() { return selectedUser; }
    public void setSelectedUser(Utilisateur selectedUser) { this.selectedUser = selectedUser; }

    
    
    /**/
    // Getters et Setters
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    public Integer getSelectedRoleId() { return selectedRoleId; }
    public void setSelectedRoleId(Integer selectedRoleId) { this.selectedRoleId = selectedRoleId; }
    public UtilisateurHome getUtilisateurHome() { return utilisateurHome; }
    public void setUtilisateurHome(UtilisateurHome utilisateurHome) { this.utilisateurHome = utilisateurHome; }
}