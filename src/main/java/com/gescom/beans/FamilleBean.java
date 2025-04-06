package com.gescom.beans;

import com.gescom.dao.FamilleHome;
import com.gescom.model.Famille;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class FamilleBean {
    private Famille currentFamille;
    private Famille newFamille;
    private List<Famille> familles;
    private List<Famille> parentsDisponibles;
    private final FamilleHome familleHome;

    public FamilleBean() {
        this.familleHome = (FamilleHome) SpringUtils.getContext().getBean("famillehome");
        init();
    }

    private void init() {
        this.newFamille = new Famille();
        chargerFamilles();
        chargerParentsDisponibles();
    }

    private void chargerFamilles() {
        this.familles = familleHome.findAll();
    }

    private void chargerParentsDisponibles() {
        this.parentsDisponibles = familleHome.findRootFamilies();
    }

    public void preparerNouvelleFamille() {
        this.newFamille = new Famille();
    }

    public void ajouterFamille() {
        try {
            if (!familleHome.isNomUnique(newFamille.getNom(), null)) {
                afficherMessage("Erreur", "Ce nom de famille existe déjà", FacesMessage.SEVERITY_ERROR);
                return;
            }

            familleHome.persist(newFamille);
            init();
            afficherMessage("Succès", "Famille ajoutée avec succès", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            afficherMessage("Erreur", "Erreur lors de l'ajout: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void modifierFamille() {
        try {
            if (!familleHome.isNomUnique(currentFamille.getNom(), currentFamille.getId())) {
                afficherMessage("Erreur", "Ce nom de famille existe déjà", FacesMessage.SEVERITY_ERROR);
                return;
            }

            familleHome.update(currentFamille);
            init();
            afficherMessage("Succès", "Famille modifiée avec succès", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            afficherMessage("Erreur", "Erreur lors de la modification: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void supprimerFamille(Famille famille) {
        try {
            familleHome.delete(famille);
            familles.remove(famille);
            afficherMessage("Succès", "Famille supprimée avec succès", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            afficherMessage("Erreur", "Erreur lors de la suppression: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    private void afficherMessage(String titre, String detail, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titre, detail));
    }

    // Getters et Setters
    public Famille getCurrentFamille() { return currentFamille; }
    public void setCurrentFamille(Famille currentFamille) { this.currentFamille = currentFamille; }
    public Famille getNewFamille() { return newFamille; }
    public void setNewFamille(Famille newFamille) { this.newFamille = newFamille; }
    public List<Famille> getFamilles() { return familles; }
    public List<Famille> getParentsDisponibles() { return parentsDisponibles; }
}