package com.gescom.beans;

import com.gescom.dao.VarianteHome;
import com.gescom.model.Produit;
import com.gescom.model.Variante;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="varianteBean")
@ViewScoped
public class VarianteBean {
    
    private List<Variante> variantes;
    private Variante selectedVariante;
    private VarianteHome varianteHome;
    private Produit produitParent;

    public VarianteBean() {
        try {
            this.varianteHome = (VarianteHome) SpringUtils.getContext().getBean("varianteHome");
            chargerVariantes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation"));
        }
    }

    public void chargerVariantes() {
        if (produitParent != null) {
            variantes = varianteHome.findByProduit(produitParent.getId());
        } else {
            variantes = varianteHome.findAll();
        }
    }

    public void prepareCreate() {
        selectedVariante = new Variante();
        selectedVariante.setProduit(produitParent);
    }

    public void prepareEdit(Variante variante) {
        selectedVariante = variante;
    }

    public void saveVariante() {
        try {
            if (selectedVariante.getId() == null) {
                varianteHome.persist(selectedVariante);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Variante créée avec succès"));
            } else {
                varianteHome.update(selectedVariante);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Variante modifiée avec succès"));
            }
            chargerVariantes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de l'enregistrement"));
        }
    }

    public void deleteVariante(Variante variante) {
        try {
            varianteHome.delete(variante.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Variante supprimée avec succès"));
            chargerVariantes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de la suppression"));
        }
    }

    // Getters/Setters
    public List<Variante> getVariantes() { return variantes; }
    public Variante getSelectedVariante() { return selectedVariante; }
    public void setSelectedVariante(Variante selectedVariante) { this.selectedVariante = selectedVariante; }
    public Produit getProduitParent() { return produitParent; }
    public void setProduitParent(Produit produitParent) { 
        this.produitParent = produitParent;
        chargerVariantes();
    }
}