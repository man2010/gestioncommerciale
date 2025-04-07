package com.gescom.beans;

import com.gescom.dao.UtilisateurHome;
import com.gescom.model.Role;
import com.gescom.model.Utilisateur;
import com.gescom.utils.SpringUtils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="utilisateurBean")
@ViewScoped
public class UtilisateurBean {
	
	/**/
	
	private List<Utilisateur> utilisateurs;
    private Utilisateur selectedUser;
    private boolean rememberMe;
	/**/
    
    private Utilisateur utilisateur;
    private String confirmPassword;
    private Integer selectedRoleId;
    private UtilisateurHome utilisateurHome;
    
   
    public UtilisateurBean() {
        try {
        	
            this.utilisateur = (Utilisateur) SpringUtils.getContext().getBean("utilisateur");
            this.utilisateurHome = (UtilisateurHome) SpringUtils.getContext().getBean("utilisateurhome");
            this.selectedUser = (Utilisateur) SpringUtils.getContext().getBean("utilisateur");
            /**/
            utilisateurs = utilisateurHome.findAll();
            
         // Initialisation du rôle si nécessaire
            if(selectedUser.getRole() == null) {
                selectedUser.setRole(new Role());
            }
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
                    return "/admin/pageadmin?faces-redirect=true";
                case "gerant":
                    return "/admin/pageadmin?faces-redirect=true";
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
    	selectedUser = new Utilisateur(); // Nouvelle instance
        selectedUser.setActif(true);
        selectedUser.setDatecreation(new java.sql.Date(System.currentTimeMillis()));
        selectedUser.setRole(new Role()); 
    }
    
    public void prepareEdit(Utilisateur user) {
    	 selectedUser = user;
//         if(selectedUser.getRole() == null) {
//             selectedUser.setRole(new Role());
//         }
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
   /* public void toggleStatus(Utilisateur user) {
        user.setActif(!user.getActif());
        utilisateurHome.update(user);
        utilisateurs = utilisateurHome.findAll();
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Statut modifié", 
            "Le statut de " + user.getPrenom() + " a été mis à jour");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }*/
    
    public void deleteUser(Utilisateur user) {
        utilisateurHome.delete(user.getIdutilisateur());
        utilisateurs = utilisateurHome.findAll();
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Suppression réussie", 
            "L'utilisateur " + user.getPrenom() + " a été supprimé");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    // Getters/Setters supplémentaires
    public List<Utilisateur> getUtilisateurs() { return utilisateurs; }
    public Utilisateur getSelectedUser() { return selectedUser; }
    public void setSelectedUser(Utilisateur selectedUser) { this.selectedUser = selectedUser; }
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
   
    
    public void savedUser() {
        try {
            if (selectedUser.getIdutilisateur() == 0) {
                // Nouvel utilisateur
            	//Date date = new Date(selectedRoleId, selectedRoleId, selectedRoleId);
              
                utilisateurHome.persist(selectedUser);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Succès", "Utilisateur créé avec succès");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                // Modification
                utilisateurHome.update(selectedUser);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Succès", "Utilisateur modifié avec succès");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            utilisateurs = utilisateurHome.findAll();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erreur", "Une erreur est survenue: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void toggleStatus(Utilisateur user) {
        try {
            user.setActif(!user.getActif());
            utilisateurHome.update(user);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Statut modifié avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors du changement de statut: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
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