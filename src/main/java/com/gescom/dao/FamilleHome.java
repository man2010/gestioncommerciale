package com.gescom.dao;

import com.gescom.model.Famille;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.*;
import java.util.List;

public class FamilleHome {
    
    public Integer persist(Famille famille) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(famille);
            transaction.commit();
            return id;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la persistance", e);
        }
    }

    public void update(Famille famille) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(famille);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la mise à jour", e);
        }
    }

    public void delete(Famille famille) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(famille);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }

    public Famille findById(Integer id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Famille.class, id);
        }
    }

    public List<Famille> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Famille> cq = cb.createQuery(Famille.class);
            Root<Famille> root = cq.from(Famille.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    public List<Famille> findRootFamilies() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Famille> cq = cb.createQuery(Famille.class);
            Root<Famille> root = cq.from(Famille.class);
            cq.select(root).where(cb.isNull(root.get("parent")));
            return session.createQuery(cq).getResultList();
        }
    }

    public boolean isNomUnique(String nom, Integer excludeId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Famille> root = cq.from(Famille.class);
            
            cq.select(cb.count(root));
            if (excludeId == null) {
                cq.where(cb.equal(root.get("nom"), nom));
            } else {
                cq.where(cb.and(
                    cb.equal(root.get("nom"), nom),
                    cb.notEqual(root.get("id"), excludeId)
                ));
            }
            
            return session.createQuery(cq).getSingleResult() == 0;
        }
    }
}