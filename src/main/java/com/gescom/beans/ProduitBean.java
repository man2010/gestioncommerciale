package com.gescom.beans;

import com.gescom.dao.ProduitHome;
import com.gescom.model.Famille;
import com.gescom.model.Produit;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="produitBean")
@ViewScoped
public class ProduitBean {
    
    private List<Produit> produits;
    private Produit selectedProduit;
    private ProduitHome produitHome;
    private List<Famille> familles;

    public ProduitBean() {
        try {
            this.produitHome = (ProduitHome) SpringUtils.getContext().getBean("produitHome");
            this.selectedProduit = new Produit();
            chargerProduits();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation"));
        }
    }

    private void chargerProduits() {
        produits = produitHome.findAll();
    }

    public void prepareCreate() {
        selectedProduit = new Produit();
        selectedProduit.setEtat("ACTIF");
    }

    public void prepareEdit(Produit produit) {
        selectedProduit = produit;
    }

    public void saveProduit() {
        try {
            if (selectedProduit.getId() == null) {
                produitHome.persist(selectedProduit);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Produit créé avec succès");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                produitHome.update(selectedProduit);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Produit modifié avec succès");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            chargerProduits();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de l'enregistrement");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteProduit(Produit produit) {
        try {
            produitHome.delete(produit.getId());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Produit supprimé avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
            chargerProduits();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de la suppression");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Getters/Setters
    public List<Produit> getProduits() { return produits; }
    public Produit getSelectedProduit() { return selectedProduit; }
    public void setSelectedProduit(Produit selectedProduit) { this.selectedProduit = selectedProduit; }
    public List<Famille> getFamilles() { return familles; }
}