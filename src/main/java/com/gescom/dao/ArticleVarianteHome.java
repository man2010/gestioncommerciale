package com.gescom.dao;

import com.gescom.model.ArticleVariante;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArticleVarianteHome {
    
    public void persist(ArticleVariante lien) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(lien);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur persistance lien article-variante", e);
        }
    }

    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ArticleVariante lien = session.get(ArticleVariante.class, id);
            if (lien != null) session.delete(lien);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur suppression lien article-variante", e);
        }
    }

    public List<ArticleVariante> findByArticle(Integer articleId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ArticleVariante> cq = cb.createQuery(ArticleVariante.class);
            Root<ArticleVariante> root = cq.from(ArticleVariante.class);
            cq.select(root).where(cb.equal(root.get("article").get("id"), articleId));
            return session.createQuery(cq).getResultList();
        }
    }
}