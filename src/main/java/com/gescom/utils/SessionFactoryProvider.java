package com.gescom.utils;
import org.hibernate.SessionFactory;
public final class SessionFactoryProvider {
private static final SessionFactory sessionFactory;
static {
sessionFactory =HibernateUtils.getSessionFactory();
}
public static SessionFactory getSessionFactory() {
return sessionFactory;
}
private SessionFactoryProvider() {
}
}