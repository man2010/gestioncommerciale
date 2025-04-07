package com.gescom.dao;

import com.gescom.model.Variante;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class VarianteHome {
    
    public void persist(Variante variante) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(variante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la persistance de la variante", e);
        }
    }

    public void update(Variante variante) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(variante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la mise à jour de la variante", e);
        }
    }

    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Variante variante = session.get(Variante.class, id);
            if (variante != null) {
                session.delete(variante);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la suppression de la variante", e);
        }
    }

    public List<Variante> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Variante> cq = cb.createQuery(Variante.class);
            Root<Variante> root = cq.from(Variante.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    public List<Variante> findByProduit(Integer produitId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Variante> cq = cb.createQuery(Variante.class);
            Root<Variante> root = cq.from(Variante.class);
            cq.select(root).where(cb.equal(root.get("produit").get("id"), produitId));
            return session.createQuery(cq).getResultList();
        }
    }
}