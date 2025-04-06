package com.gescom.dao;

import com.gescom.model.Role;
import com.gescom.utils.HibernateUtils;
import org.hibernate.Session;
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
}