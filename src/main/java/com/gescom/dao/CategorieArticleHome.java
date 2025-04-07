package com.gescom.dao;

import com.gescom.model.CategorieArticle;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategorieArticleHome {
    
    public void persist(CategorieArticle categorie) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(categorie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la persistance de la catégorie", e);
        }
    }

    public void update(CategorieArticle categorie) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(categorie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la mise à jour de la catégorie", e);
        }
    }

    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CategorieArticle categorie = session.get(CategorieArticle.class, id);
            if (categorie != null) {
                session.delete(categorie);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la suppression de la catégorie", e);
        }
    }

    public List<CategorieArticle> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CategorieArticle> cq = cb.createQuery(CategorieArticle.class);
            Root<CategorieArticle> root = cq.from(CategorieArticle.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    public CategorieArticle findById(Integer id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(CategorieArticle.class, id);
        }
    }
}