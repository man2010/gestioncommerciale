package com.gescom.dao;

import com.gescom.model.Fournisseur;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FournisseurHome {
    
    public void persist(Fournisseur fournisseur) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(fournisseur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur persistance fournisseur", e);
        }
    }

    public void update(Fournisseur fournisseur) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(fournisseur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur mise à jour fournisseur", e);
        }
    }

    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Fournisseur fournisseur = session.get(Fournisseur.class, id);
            if (fournisseur != null) session.delete(fournisseur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur suppression fournisseur", e);
        }
    }

    public List<Fournisseur> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Fournisseur> cq = cb.createQuery(Fournisseur.class);
            Root<Fournisseur> root = cq.from(Fournisseur.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }
}