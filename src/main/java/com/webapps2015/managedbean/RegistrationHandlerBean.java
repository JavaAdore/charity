/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.entity.Roles;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.WebUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author dar27
 */
@ManagedBean
@ViewScoped
public class RegistrationHandlerBean implements Serializable {

    @EJB
    WebAppFacade webAppFacade;

    private Charity charity;

    private Fundraiser fundraiser;

    private SystemUser systemUser;

    @ManagedProperty(value = "#{applicationScopeHandlerBean}")
    private ApplicationScopeHandlerBean applicationScopeHandlerBean;

    @PostConstruct
    public void init() {

        systemUser = new SystemUser();
        systemUser.setRole(Roles.FUNDRAISER);
        initiateNewFundraiser();

    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    private void initiateNewFundraiser() {
        if (fundraiser == null) {
            fundraiser = new Fundraiser();
        }
        systemUser.setRole(Roles.FUNDRAISER);

    }

    private void initiateNewCharity() {
        if (charity == null) {
            charity = new Charity();
        }
        systemUser.setRole(Roles.CHARITIES);
    }

    public void groupChanges(AjaxBehaviorEvent event) {
        Object object = ((HtmlSelectOneMenu) event.getSource()).getValue();
        if (object.equals(Roles.FUNDRAISER)) {
            initiateNewFundraiser();

        } else {
            initiateNewCharity();
        }

    }

    public String register() {
        try {
            if (systemUser.getRole().equals(Roles.FUNDRAISER)) {
                fundraiser.loadSystemUserInfo(systemUser);
                webAppFacade.addOrUpdateFundriser(fundraiser);
                return "fundriserHome";
            } else {
                charity.loadSystemUserInfo(systemUser);
                webAppFacade.registerCharity(charity);
                return "charityHome";

            }
        } catch (WebApps2015Exception ex) {
            WebUtils.fireErrorMessage(ex.getCustomMessage());
        }
        return null;

    }

    public void checkEmail() {
        applicationScopeHandlerBean.checkEmail(systemUser.getEmail());
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
