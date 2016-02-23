/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.util.WebUtils;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dar27
 */
@ManagedBean
@ViewScoped
public class AfterLoginHandlerBean implements Serializable{

    @EJB
    WebAppFacade webAppFacade;
    SystemUser systemUser;
    
    @ManagedProperty(value="#{applicationScopeHandlerBean}")
    ApplicationScopeHandlerBean applicationScopeHandlerBean;

    @PostConstruct
    public void init() {

        applicationScopeHandlerBean.injectLoginUserIntoSession();
        
        
    }

    public void redirectToApproperatePage() {
        String role = (String) WebUtils.extractFromSession("role");
        if (role.equals(Roles.ADMINS.getName())) {
            WebUtils.redirectTo("admin/Fundraisers.xhtml");
        } else if (role.equals(Roles.FUNDRAISER.getName())) {
            WebUtils.redirectTo("fundraiser/donation.xhtml");

        } else {
            WebUtils.redirectTo("charity/Home.xhtml");

        }
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public ApplicationScopeHandlerBean getApplicationScopeHandlerBean() {
        return applicationScopeHandlerBean;
    }

    public void setApplicationScopeHandlerBean(ApplicationScopeHandlerBean applicationScopeHandlerBean) {
        this.applicationScopeHandlerBean = applicationScopeHandlerBean;
    }
    
    
}
