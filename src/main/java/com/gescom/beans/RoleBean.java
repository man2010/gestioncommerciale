package com.gescom.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.gescom.dao.RoleHome;
import com.gescom.model.Role;
import java.util.List;
import com.gescom.utils.SpringUtils; // Assurez-vous d'avoir cette classe

@ManagedBean(name="roleBean")
@SessionScoped
public class RoleBean {
	
	/**/
	
	private List<Role> roles;
    private Role selectedRole;
	
	/**/
    
    private List<Role> visibleRoles;
    private RoleHome roleHome;
    
    public RoleBean() {
        // Récupération des beans depuis le contexte Spring
        this.roleHome = (RoleHome) SpringUtils.getContext().getBean("rolehome");
        /**/
        roles = roleHome.findAll();
        /**/
        loadVisibleRoles();
    }
    
    private void loadVisibleRoles() {
        this.visibleRoles = roleHome.findAllVisibleRoles();
    }
    
    public List<Role> getVisibleRoles() {
        return visibleRoles;
    }
    
    
    public Role getRoleById(int idRole) {
        try {
            // Solution avec RoleHome
            return roleHome.findById(idRole);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erreur", "Impossible de charger le rôle"));
            return null;
        }
    }
    
    public Role findRoleById(Integer idRole) {
        if (idRole == null) {
            return null;
        }
        return visibleRoles.stream()
                          .filter(r -> r.getIdrole() == idRole) // Comparaison avec == pour les primitifs
                          .findFirst()
                          .orElse(null);
    }
    /**/
    
    
    public void prepareCreate() {
        selectedRole = new Role();
    }
    
    public void prepareEdit(Role role) {
        selectedRole = role;
    }
    
    public void saveRole() {
        if (selectedRole.getIdrole() == 0) {
            roleHome.persist(selectedRole);
        } else {
            roleHome.update(selectedRole);
        }
        roles = roleHome.findAll();
        visibleRoles = roleHome.findAllVisibleRoles();
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Rôle enregistré avec succès"));
    }
    
    public void deleteRole() {
        roleHome.delete(selectedRole.getIdrole());
        roles = roleHome.findAll();
        visibleRoles = roleHome.findAllVisibleRoles();
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Rôle enregistré avec succès"));
    }
    
    public int getCountRoles() {
        return roles != null ? roles.size() : 0;
    }
    
    // Getters/Setters supplémentaires
    public List<Role> getRoles() { return roles; }
    public Role getSelectedRole() { return selectedRole; }
    public void setSelectedRole(Role selectedRole) { this.selectedRole = selectedRole; }

	
    
    
    /**/
}
