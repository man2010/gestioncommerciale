package com.gescom.beans;

import com.gescom.dao.FournisseurHome;
import com.gescom.model.Fournisseur;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="fournisseurBean")
@ViewScoped
public class FournisseurBean {
    
    private List<Fournisseur> fournisseurs;
    private Fournisseur selectedFournisseur;
    private FournisseurHome fournisseurHome;

    public FournisseurBean() {
        try {
            this.fournisseurHome = (FournisseurHome) SpringUtils.getContext().getBean("fournisseurHome");
            chargerFournisseurs();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation"));
        }
    }

    private void chargerFournisseurs() {
        fournisseurs = fournisseurHome.findAll();
    }

    public void prepareCreate() {
        selectedFournisseur = new Fournisseur();
        selectedFournisseur.setEtat("ACTIF");
    }

    public void prepareEdit(Fournisseur fournisseur) {
        selectedFournisseur = fournisseur;
    }

    public void saveFournisseur() {
        try {
            if (selectedFournisseur.getId() == null) {
                fournisseurHome.persist(selectedFournisseur);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Fournisseur créé"));
            } else {
                fournisseurHome.update(selectedFournisseur);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Fournisseur modifié"));
            }
            chargerFournisseurs();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de l'enregistrement"));
        }
    }

    public void deleteFournisseur(Fournisseur fournisseur) {
        try {
            fournisseurHome.delete(fournisseur.getId());
            chargerFournisseurs();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Fournisseur supprimé"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de la suppression"));
        }
    }

    // Getters/Setters
    public List<Fournisseur> getFournisseurs() { return fournisseurs; }
    public Fournisseur getSelectedFournisseur() { return selectedFournisseur; }
    public void setSelectedFournisseur(Fournisseur selectedFournisseur) {
        this.selectedFournisseur = selectedFournisseur;
    }
}