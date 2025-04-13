package com.gescom.admin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@ManagedBean
@SessionScoped
public class AdminDashboardController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currentView = "overview";
    private String theme = "saga-blue";
    private User currentUser;
    private int unreadNotifications = 3;
    private int unreadMessages = 5;
    private Map<String, String> themeColors = new HashMap<>();
    
   
    public void init() {
        currentUser = loadCurrentUser();
        initializeThemeColors();
    }
    
    private User loadCurrentUser() {
        // Simuler le chargement de l'utilisateur
        User user = new User();
        user.setId(1L);
        user.setFullName("Admin Super");
        user.setEmail("admin@multiboutique.com");
        user.setAvatar("default-avatar.png");
        user.setRole("Administrateur Principal");
        return user;
    }
    
    private void initializeThemeColors() {
        themeColors.put("arya-blue", "#1e1e1e");
        themeColors.put("saga-blue", "#ffffff");
        themeColors.put("vela-blue", "#1e1e1e");
        themeColors.put("nova-colored", "#ffffff");
        themeColors.put("luna-amber", "#ffffff");
        themeColors.put("luna-green", "#ffffff");
    }
    
    // Navigation methods
    public String showOverview() {
        currentView = "overview";
        return null;
    }
    
    public String showStatistics() {
        currentView = "statistics";
        return null;
    }
    
    public String initMonths() {
        currentView = "initMonths";
        return null;
    }
    
    public String initCountries() {
        currentView = "initCountries";
        return null;
    }
    
    public String showUserManagement() {
        currentView = "userManagement";
        return null;
    }
    
    public String showShopManagement() {
        currentView = "shopManagement";
        return null;
    }
    
    public String showThemeSettings() {
        currentView = "themeSettings";
        return null;
    }
    
    // Theme management
    public void changeTheme(String theme) {
        this.theme = theme;
    }
    
    public List<String> getAvailableThemes() {
        return new ArrayList<>(themeColors.keySet());
    }
    
    public String getThemeColor(String theme) {
        return themeColors.get(theme);
    }
    
    // Getters and Setters
    public String getCurrentView() {
        return currentView;
    }
    
    public String getTheme() {
        return theme;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public String getUserAvatar() {
        return "/resources/images/" + currentUser.getAvatar();
    }
    
    public int getUnreadNotifications() {
        return unreadNotifications;
    }
    
    public int getUnreadMessages() {
        return unreadMessages;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    
    public void openSettings() {
        PrimeFaces.current().executeScript("PF('settingsDialog').show();");
    }
}

class User {
    private Long id;
    private String fullName;
    private String email;
    private String avatar;
    private String role;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}