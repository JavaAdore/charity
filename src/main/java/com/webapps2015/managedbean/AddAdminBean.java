/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Admin;
import com.webapps2015.entity.Roles;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.WebUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dar27
 */
@ManagedBean
@ViewScoped
public class AddAdminBean implements Serializable {
    
    @EJB
    private WebAppFacade webAppFacade;
    private Admin admin;
    
    @ManagedProperty(value = "#{applicationScopeHandlerBean}")
    private ApplicationScopeHandlerBean applicationScopeHandlerBean;

    @PostConstruct
    public void init() {
        initiateNewAdmin();
    }
    
    public void initiateNewAdmin() {
        admin = new Admin();
        admin.setCreator(WebUtils.getCurrentAdmin());
        admin.setRole(Roles.ADMINS);
    }
    
    public Admin getAdmin() {
        return admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    public void addNewAdmin() {
        
        try
        {
        webAppFacade.addAdmin(admin);
                    WebUtils.fireInfoMessage("Admin added successfully");

        }catch(WebApps2015Exception ex)
        {
            WebUtils.fireErrorMessage(ex.getCustomMessage());
        }
        
    }
    
    public void checkEmail() {
        applicationScopeHandlerBean.checkEmail(admin.getEmail());
    }

    public ApplicationScopeHandlerBean getApplicationScopeHandlerBean() {
        return applicationScopeHandlerBean;
    }

    public void setApplicationScopeHandlerBean(ApplicationScopeHandlerBean applicationScopeHandlerBean) {
        this.applicationScopeHandlerBean = applicationScopeHandlerBean;
    }
    
    
}
