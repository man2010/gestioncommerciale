package com.gescom.beans;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class AdminBean {
    
    public String getPageTitle() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        
        if (viewId.equals("/admin/pageadmin.xhtml")) {
            return "Tableau de bord";
        } else if (viewId.equals("/admin/gestionuser.xhtml")) {
            return "Gestion des utilisateurs";
        } else if (viewId.equals("/admin/gestionrole.xhtml")) {
            return "Gestion des rôles";
        } else if (viewId.equals("/admin/gestionfamilleproduit.xhtml")) {
            return "Gestion des familles produits";
        }
        return "Administration"; // default title
    }
}