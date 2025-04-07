package com.gescom.beans;

import com.gescom.dao.ArticleHome;
import com.gescom.model.Article;
import com.gescom.model.Produit;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="articleBean")
@ViewScoped
public class ArticleBean {
    
    private List<Article> articles;
    private Article selectedArticle;
    private ArticleHome articleHome;
    private List<Produit> produits;

    public ArticleBean() {
        try {
            this.articleHome = (ArticleHome) SpringUtils.getContext().getBean("articleHome");
            this.selectedArticle = new Article();
            chargerArticles();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation"));
        }
    }

    private void chargerArticles() {
        articles = articleHome.findAll();
    }

    public void prepareCreate() {
        selectedArticle = new Article();
        selectedArticle.setEtat("ACTIF");
    }

    public void prepareEdit(Article article) {
        selectedArticle = article;
    }

    public void saveArticle() {
        try {
            if (selectedArticle.getId() == null) {
                articleHome.persist(selectedArticle);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Article créé avec succès");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                articleHome.update(selectedArticle);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succès", "Article modifié avec succès");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            chargerArticles();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur lors de l'enregistrement");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
    public void toggleStatus(Article article) {
        try {
            article.setEtat(article.getEtat().equals("ACTIF") ? "INACTIF" : "ACTIF");
            articleHome.update(article);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succès", "Statut modifié"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Échec de la modification"));
        }
    }

   

    // Getters/Setters
    public List<Article> getArticles() { return articles; }
    public Article getSelectedArticle() { return selectedArticle; }
    public void setSelectedArticle(Article selectedArticle) { this.selectedArticle = selectedArticle; }
    public List<Produit> getProduits() { return produits; }
}