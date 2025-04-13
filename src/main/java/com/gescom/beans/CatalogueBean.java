package com.gescom.beans;

import com.gescom.dao.CatalogueHome;
import com.gescom.model.Catalogue;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ManagedBean(name = "catalogueBean")
@ViewScoped
public class CatalogueBean {
    
    private List<Catalogue> catalogues;
    private Catalogue selectedCatalogue;
    private CatalogueHome catalogueHome;
    
    public CatalogueBean() {
        try {
            this.selectedCatalogue = (Catalogue) SpringUtils.getContext().getBean("catalogue");
            this.catalogueHome = (CatalogueHome) SpringUtils.getContext().getBean("catalogueHome");
            catalogues = catalogueHome.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur d'initialisation", "Impossible de charger les dépendances"));
            this.selectedCatalogue = new Catalogue();
        }
    }
    
    public void prepareCreate() {
        selectedCatalogue = new Catalogue();
        selectedCatalogue.setDatecreation(Date.valueOf(LocalDate.now()));
        selectedCatalogue.setEtat("ACTIF");
    }
    
    public void prepareEdit(Catalogue catalogue) {
        selectedCatalogue = catalogue;
    }
    public void prepareDelete(Catalogue catalogue) {
        selectedCatalogue = catalogue;
    }
    
    public void saveCatalogue() {
        try {
            if (selectedCatalogue.getIdcatalogue() == 0) {
                catalogueHome.persist(selectedCatalogue);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Catalogue créé avec succès"));
            } else {
                catalogueHome.update(selectedCatalogue);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Catalogue modifié avec succès"));
            }
            catalogues = catalogueHome.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de l'enregistrement: " + e.getMessage()));
        }
    }
    
    public void deleteCatalogue() {
        try {
            catalogueHome.delete(selectedCatalogue.getIdcatalogue());
            catalogues = catalogueHome.findAll();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Catalogue supprimé avec succès"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de la suppression: " + e.getMessage()));
        }
    }
    
    // Getters et Setters
    public List<Catalogue> getCatalogues() {
        return catalogues;
    }
    
    public Catalogue getSelectedCatalogue() {
        return selectedCatalogue;
    }
    
    public void setSelectedCatalogue(Catalogue selectedCatalogue) {
        this.selectedCatalogue = selectedCatalogue;
    }
    
    public int getCountCatalogues() {
        return catalogues != null ? catalogues.size() : 0;
    }
}