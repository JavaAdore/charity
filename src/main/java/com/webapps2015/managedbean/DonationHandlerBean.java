/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Donation;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.WebUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author dar27
 */
@ManagedBean
@ViewScoped
public class DonationHandlerBean implements Serializable {

    private List<Activity> allDonatableActivities;
    private Activity activeActivity;
    private Donation donation;

    @EJB
    private WebAppFacade WebAppFacade;
    Fundraiser fundriaiser;   
    @PostConstruct
    public void init() {
        fundriaiser = WebUtils.getCurentFundraiser();
        allDonatableActivities = WebAppFacade.getAllActivitiesExceptOfWhichCreatedBy(fundriaiser);
     
        initiateNewDonationObject();

    }

    private void initiateNewDonationObject() {
        donation = new Donation();
        donation.setFundraiser(fundriaiser);
        setCurrentActivity();

    }

    private void setCurrentActivity() {
        if (allDonatableActivities.size() > 0 && activeActivity == null) {
            donation.setActivity(allDonatableActivities.get(0));
        }
    }

    public void donate() {
        try {
            String donationFeedbackMessage = WebAppFacade.donate(donation);
            WebUtils.fireInfoMessage(donationFeedbackMessage);
        } catch (WebApps2015Exception ex) {
            WebUtils.fireInfoMessage(ex.getCustomMessage());
        }
    }

    public void activityChanged(AjaxBehaviorEvent ajaxBehaviorEvent) {
        donation.setActivity((Activity) ((HtmlSelectOneMenu) ajaxBehaviorEvent.getSource()).getValue());
    }

    public List<Activity> getAllDonatableActivities() {
        return allDonatableActivities;
    }

    public void setAllDonatableActivities(List<Activity> allDonatableActivities) {
        this.allDonatableActivities = allDonatableActivities;
    }

    public Activity getActiveActivity() {
        return activeActivity;
    }

    public void setActiveActivity(Activity activeActivity) {
        this.activeActivity = activeActivity;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public WebAppFacade getWebAppFacade() {
        return WebAppFacade;
    }

    public void setWebAppFacade(WebAppFacade WebAppFacade) {
        this.WebAppFacade = WebAppFacade;
    }

    public Fundraiser getFundriaiser() {
        return fundriaiser;
    }

    public void setFundriaiser(Fundraiser fundriaiser) {
        this.fundriaiser = fundriaiser;
    }

    
    
    

}
