package com.gescom.dao;

import com.gescom.model.Role;
import com.gescom.utils.HibernateUtils;
import com.gescom.utils.SessionFactoryProvider;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class RoleHome {

    @SuppressWarnings({ "deprecation", "unchecked" })
	public List<Role> findAllVisibleRoles() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createCriteria(Role.class)
                         .add(Restrictions.not(Restrictions.eq("nomrole", "admin")))
                         .list();
        }
    }
    
    public Role findById(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Role.class, id);
        }
    }
    
    
    /**/
    
    public List<Role> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.createQuery("FROM Role", Role.class).getResultList();
        }
    }
    
    public void persist(Role role) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la persistance", e);
        }
    }
    
    public void update(Role role) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la mise à jour", e);
        }
    }
    
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Role role = session.get(Role.class, id);
            if (role != null) {
                session.delete(role);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }
    
    /**/
}