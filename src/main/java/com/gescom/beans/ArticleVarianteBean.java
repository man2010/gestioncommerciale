package com.gescom.beans;

import com.gescom.dao.ArticleVarianteHome;
import com.gescom.model.Article;
import com.gescom.model.ArticleVariante;
import com.gescom.model.Variante;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import java.util.List;

@ManagedBean(name="articleVarianteBean")
@ViewScoped
public class ArticleVarianteBean {
    
    private List<ArticleVariante> liens;
    private ArticleVariante selectedLien;
    private ArticleVarianteHome lienHome;
    private Article articleParent;

    public ArticleVarianteBean() {
        try {
            this.lienHome = (ArticleVarianteHome) SpringUtils.getContext().getBean("articleVarianteHome");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation"));
        }
    }

    public void chargerLiens() {
        if (articleParent != null) {
            liens = lienHome.findByArticle(articleParent.getId());
        }
    }

    public void prepareCreate() {
        selectedLien = new ArticleVariante();
        selectedLien.setArticle(articleParent);
    }

    public void saveLien(Variante variante) {
        try {
            ArticleVariante lien = new ArticleVariante();
            lien.setArticle(articleParent);
            lien.setVariante(variante);
            
            lienHome.persist(lien);
            chargerLiens();
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Variante associée avec succès"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de l'association"));
        }
    }

    public void deleteLien(ArticleVariante lien) {
        try {
            lienHome.delete(lien.getId());
            chargerLiens();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Association supprimée"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de la suppression"));
        }
    }

    // Getters/Setters
    public List<ArticleVariante> getLiens() { return liens; }
    public Article getArticleParent() { return articleParent; }
    public void setArticleParent(Article articleParent) { 
        this.articleParent = articleParent;
        chargerLiens();
    }
}