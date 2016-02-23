/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.dao.PageNameHolder;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.Constant;
import com.webapps2015.util.WebUtils;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author dar27
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationScopeHandlerBean {

    private List<Roles> groups;

    @EJB
    private WebAppFacade webAppFacade;

    @PostConstruct
    public void init() {

        insertLookUps();

        groups = webAppFacade.loadAllRoles();
    }

    public List<Roles> getGroups() {
        return groups;
    }

    private void insertLookUps() {
        webAppFacade.insertLookUps();
    }

    private void initializeContextBasedConstatnts() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        String webContentRoot = servletContext.getContextPath();
        Constant.setContextPath(webContentRoot);

    }

    public void checkEmail(String emailToCheck) {
        try {
            webAppFacade.isEmailExist(emailToCheck);
            WebUtils.fireInfoMessage("Accepted Email address");
        } catch (Exception ex) {
            WebUtils.fireErrorMessage("Email already exist");
        }
    }

    public void logout() {
        WebUtils.logout();
    }

    public SystemUser getCurrentSystemUser() {
        return WebUtils.getCurrentSystemUser();
    }

    public List<PageNameHolder> getCurrentUserMenu() {
        if (WebUtils.getCurrentSystemUser() == null) {
            injectLoginUserIntoSession();
        }
        return WebUtils.getCurrentUserMenu();
    }

    void injectLoginUserIntoSession() {
        Principal principal = WebUtils.getPrincipal();
        if (principal != null && principal.getName() != null) {
            SystemUser systemUser = webAppFacade.getSystemUserByEmail(principal.getName());
            if (systemUser != null && systemUser.getRole() != null) {
                WebUtils.injectSystemUserIntoSession(systemUser);
            }
        }
    }
    
  
    public String echo(String logicalName)
    {
        return logicalName;
    }
    
}
