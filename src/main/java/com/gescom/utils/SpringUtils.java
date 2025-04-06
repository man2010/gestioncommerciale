package com.gescom.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

    private static final ApplicationContext context = buildApplicationContext();

    private static ApplicationContext buildApplicationContext() {
        try {
            // Charger le fichier de configuration SpringBeans.xml
            return new ClassPathXmlApplicationContext("SpringBeans.xml");
        } catch (Exception e) {
            System.out.println("Échec de la création du contexte d'application");
            throw e;
        }
    }

    public static ApplicationContext getContext() {
        return context;
    }
}