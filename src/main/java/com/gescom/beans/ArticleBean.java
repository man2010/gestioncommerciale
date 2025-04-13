package com.gescom.beans;

import com.gescom.dao.ArticleHome;
import com.gescom.dao.VarianteHome;
import com.gescom.model.Article;
import com.gescom.model.Produit;
import com.gescom.model.Variante;
import com.gescom.model.ArticleVariante;
import com.gescom.model.VarianteSelection;
import com.gescom.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name="articleBean")
@ViewScoped
public class ArticleBean {
    
    private List<Article> articles;
    private Article selectedArticle;
    private ArticleHome articleHome;
    private VarianteHome varianteHome;
    private List<Produit> produits;
    private List<VarianteSelection> selectedVariantes;

    public ArticleBean() {
        try {
            this.articleHome = (ArticleHome) SpringUtils.getContext().getBean("articleHome");
            this.varianteHome = (VarianteHome) SpringUtils.getContext().getBean("varianteHome"); 
            chargerArticles();
            this.selectedArticle = (Article) SpringUtils.getContext().getBean("article");
            this.selectedVariantes = new ArrayList<>();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erreur", "Erreur d'initialisation: " + e.getMessage()));
        }
    }

    private void chargerArticles() {
        articles = articleHome.findAll();
    }

    public void prepareCreate() {
        selectedArticle = new Article();
        selectedArticle.setEtat("ACTIF");
        selectedVariantes = new ArrayList<>();
    }

    public void prepareEdit(Article article) {
        selectedArticle = article;
        selectedVariantes = article.getVariantes().stream()
            .map(av -> new VarianteSelection(av.getVariante().getType(), av.getVariante().getValeur()))
            .collect(Collectors.toList());
    }

    public void saveArticle() {
        try {
            // Gestion des associations Article-Variante
            manageArticleVariantes();

            if (selectedArticle.getId() == null) {
                articleHome.persist(selectedArticle);
                showSuccessMessage("Article créé avec succès");
            } else {
                articleHome.update(selectedArticle);
                showSuccessMessage("Article modifié avec succès");
            }
            chargerArticles();
        } catch (Exception e) {
            showErrorMessage("Erreur lors de l'enregistrement: " + e.getMessage());
        }
    }

    private void manageArticleVariantes() {
        // Suppression des anciennes associations
        if (selectedArticle.getId() != null) {
            List<ArticleVariante> toRemove = new ArrayList<>(selectedArticle.getVariantes());
            toRemove.forEach(av -> selectedArticle.getVariantes().remove(av));
        }

        // Création des nouvelles associations
        selectedVariantes.forEach(vs -> {
            Variante variante = varianteHome.findByTypeAndValeur(vs.getType(), vs.getValeur());
            if (variante != null && !varianteAlreadyAssociated(variante)) {
                ArticleVariante av = new ArticleVariante();
                av.setArticle(selectedArticle);
                av.setVariante(variante);
                selectedArticle.getVariantes().add(av);
            }
        });
    }

    private boolean varianteAlreadyAssociated(Variante variante) {
        return selectedArticle.getVariantes().stream()
            .anyMatch(av -> av.getVariante().getId().equals(variante.getId()));
    }

    public void toggleStatus(Article article) {
        try {
            String newStatus = article.getEtat().equals("ACTIF") ? "INACTIF" : "ACTIF";
            article.setEtat(newStatus);
            articleHome.update(article);
            showSuccessMessage("Statut modifié avec succès");
        } catch (Exception e) {
            showErrorMessage("Échec de la modification du statut: " + e.getMessage());
        }
    }

    public List<VarianteSelection> getAvailableVariantes() {
        if (selectedArticle != null && selectedArticle.getProduit() != null) {
            return varianteHome.findByProduit(selectedArticle.getProduit().getId())
                .stream()
                .map(v -> new VarianteSelection(v.getType(), v.getValeur()))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private void showSuccessMessage(String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", detail));
    }

    private void showErrorMessage(String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", detail));
    }

    // Getters and Setters
    public List<Article> getArticles() {
        return articles;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<VarianteSelection> getSelectedVariantes() {
        return selectedVariantes;
    }

    public void setSelectedVariantes(List<VarianteSelection> selectedVariantes) {
        this.selectedVariantes = selectedVariantes;
    }
}