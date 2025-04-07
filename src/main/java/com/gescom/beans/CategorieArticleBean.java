package com.gescom.beans;

import com.gescom.dao.CategorieArticleHome;
import com.gescom.model.CategorieArticle;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="categorieArticleBean")
@ViewScoped
public class CategorieArticleBean {
    
    private List<CategorieArticle> categories;
    private CategorieArticle selectedCategorie;
    private CategorieArticleHome categorieHome;

    public CategorieArticleBean() {
        try {
            this.categorieHome = (CategorieArticleHome) SpringUtils.getContext().getBean("categorieArticleHome");
            chargerCategories();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation: " + e.getMessage()));
        }
    }

    private void chargerCategories() {
        categories = categorieHome.findAll();
    }

    public void prepareCreate() {
        selectedCategorie = new CategorieArticle();
        selectedCategorie.setEtat("ACTIF");
    }

    public void prepareEdit(CategorieArticle categorie) {
        selectedCategorie = categorie;
    }

    public void saveCategorie() {
        try {
            if (selectedCategorie.getId() == null) {
                categorieHome.persist(selectedCategorie);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Catégorie créée avec succès"));
            } else {
                categorieHome.update(selectedCategorie);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Catégorie modifiée avec succès"));
            }
            chargerCategories();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de l'enregistrement: " + e.getMessage()));
        }
    }

    public void deleteCategorie(CategorieArticle categorie) {
        try {
            categorieHome.delete(categorie.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Catégorie supprimée avec succès"));
            chargerCategories();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de la suppression: " + e.getMessage()));
        }
    }

    // Getters/Setters
    public List<CategorieArticle> getCategories() { return categories; }
    public CategorieArticle getSelectedCategorie() { return selectedCategorie; }
    public void setSelectedCategorie(CategorieArticle selectedCategorie) { 
        this.selectedCategorie = selectedCategorie; 
    }
}