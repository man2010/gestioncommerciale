package com.gescom.dao;

import com.gescom.model.Article;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArticleHome {
    
    public void persist(Article article) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(article);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la persistance", e);
        }
    }

    public void update(Article article) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(article);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la mise à jour", e);
        }
    }

    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Article article = session.get(Article.class, id);
            if (article != null) session.delete(article);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }

    public Article findById(Integer id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Article.class, id);
        }
    }

    public List<Article> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Article> cq = cb.createQuery(Article.class);
            Root<Article> root = cq.from(Article.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    public List<Article> findByProduit(Integer produitId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Article> cq = cb.createQuery(Article.class);
            Root<Article> root = cq.from(Article.class);
            cq.select(root).where(cb.equal(root.get("produit").get("id"), produitId));
            return session.createQuery(cq).getResultList();
        }
    }
}