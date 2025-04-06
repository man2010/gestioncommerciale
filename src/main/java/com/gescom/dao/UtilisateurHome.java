package com.gescom.dao;

import com.gescom.model.Role;
import com.gescom.model.Utilisateur;
import com.gescom.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UtilisateurHome {
    
    public void persist(Utilisateur utilisateur) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            utilisateur.setMotpass(hashPassword(utilisateur.getMotpass()));
            session.persist(utilisateur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la persistance", e);
        }
    }

    public void update(Utilisateur utilisateur) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (utilisateur.getMotpass() != null && !utilisateur.getMotpass().isEmpty()) {
                utilisateur.setMotpass(hashPassword(utilisateur.getMotpass()));
            }
            session.update(utilisateur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour", e);
        }
    }

    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Utilisateur utilisateur = session.get(Utilisateur.class, id);
            if (utilisateur != null) {
                session.delete(utilisateur);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }

    public Utilisateur findById(int id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Utilisateur.class, id);
        }
    }

    public List<Utilisateur> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);
            Root<Utilisateur> root = cq.from(Utilisateur.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    public boolean isValidUser(String email, String motDePasse) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Utilisateur utilisateur = findByEmail(email);
            return utilisateur != null && 
                   checkPassword(motDePasse, utilisateur.getMotpass());
        }
    }

    public Utilisateur findByEmail(String email) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);
            Root<Utilisateur> root = cq.from(Utilisateur.class);
            cq.select(root).where(cb.equal(root.get("email"), email));
            return session.createQuery(cq).uniqueResult();
        }
    }

    public String register(Utilisateur utilisateur, String confirmationMotDePasse, Integer roleId) {
        if (!utilisateur.getMotpass().equals(confirmationMotDePasse)) {
            return "Les mots de passe ne correspondent pas";
        }
        
        if (findByEmail(utilisateur.getEmail()) != null) {
            return "Un utilisateur avec cet email existe déjà";
        }
        
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            
            // Associer le rôle
            Role role = session.get(Role.class, roleId);
            if (role == null) {
                return "Rôle invalide";
            }
            utilisateur.setRole(role);
            
            // Chiffrement mot de passe
            utilisateur.setMotpass(hashPassword(utilisateur.getMotpass()));
            
            // Valeurs par défaut
            utilisateur.setDatecreation(new java.sql.Date(System.currentTimeMillis()));
            utilisateur.setActif(true);
            
            session.persist(utilisateur);
            tx.commit();
            return null; // Succès
        } catch (Exception e) {
            return "Erreur technique lors de l'inscription";
        }
    }

    public Utilisateur connexion(String email, String motDePasse) {
        if (isValidUser(email, motDePasse)) {
            return findByEmail(email);
        }
        return null;
    }
    
    // Méthodes pour le hachage et vérification BCrypt
//    private String hashPassword(String plainPassword) {
//        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
//    }
    
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // Coût de 12
    }
    
    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}