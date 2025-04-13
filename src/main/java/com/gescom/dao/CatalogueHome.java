package com.gescom.dao;

import com.gescom.model.Catalogue;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class CatalogueHome {
    
    public void persist(Catalogue catalogue) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(catalogue);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la création du catalogue", e);
        }
    }

    public void update(Catalogue catalogue) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(catalogue);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour du catalogue", e);
        }
    }

    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Catalogue catalogue = session.get(Catalogue.class, id);
            if (catalogue != null) {
                session.delete(catalogue);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression du catalogue", e);
        }
    }

    public Catalogue findById(int id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Catalogue.class, id);
        }
    }

    public List<Catalogue> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Catalogue> cq = cb.createQuery(Catalogue.class);
            cq.from(Catalogue.class);
            return session.createQuery(cq).getResultList();
        }
    }
    
    public List<Catalogue> findByEtat(String etat) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Catalogue> cq = cb.createQuery(Catalogue.class);
            Root<Catalogue> root = cq.from(Catalogue.class);
            cq.select(root).where(cb.equal(root.get("etat"), etat));
            return session.createQuery(cq).getResultList();
        }
    }
}