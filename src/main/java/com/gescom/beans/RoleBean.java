package com.gescom.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.gescom.dao.RoleHome;
import com.gescom.model.Role;
import java.util.List;
import com.gescom.utils.SpringUtils; // Assurez-vous d'avoir cette classe

@ManagedBean(name="roleBean")
@SessionScoped
public class RoleBean {
    
    private List<Role> visibleRoles;
    private RoleHome roleHome;
    
    public RoleBean() {
        // Récupération des beans depuis le contexte Spring
        this.roleHome = (RoleHome) SpringUtils.getContext().getBean("rolehome");
        loadVisibleRoles();
    }
    
    private void loadVisibleRoles() {
        this.visibleRoles = roleHome.findAllVisibleRoles();
    }
    
    public List<Role> getVisibleRoles() {
        return visibleRoles;
    }
}