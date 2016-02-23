/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.dto.ActivityFundraisingResult;
import com.webapps2015.entity.*;
import java.io.Serializable;
import java.util.List;
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
public class FundraiserBean implements Serializable {

    @EJB
    WebAppFacade webAppFacade;

    private List<Fundraiser> fundraisers;
    private List<Activity> fundraiserActivities;
    private Activity activeActivity;
    private ActivityFundraisingResult activityFundraisingResult;
    private Fundraiser activeFundraiser;
    
    
    @PostConstruct
    public void init() {
        fundraisers = webAppFacade.getAllUsers();
        if (fundraisers.size() > 0) {
            loadFundriserActivities(fundraisers.get(0));
        }
    }

    private void loadFundriserActivities(Fundraiser fundraiser) {
        activeFundraiser = fundraiser;
        fundraiserActivities = webAppFacade.getAllActivitiesCreatedBy(fundraiser);
        if (fundraiserActivities.size() > 0) {
            displayActivitiyDetails(fundraiserActivities.get(0));
        }else
        {
            activeActivity = null;
            activityFundraisingResult=null;
        }

    }

    public void fundriserChanged(AjaxBehaviorEvent event) {
        Fundraiser fundraiser = (Fundraiser) ((HtmlSelectOneMenu) event.getSource()).getValue();
        loadFundriserActivities(fundraiser);

    }

    public List<Fundraiser> getFundraisers() {
        return fundraisers;
    }

    public void setFundraisers(List<Fundraiser> fundraisers) {
        this.fundraisers = fundraisers;
    }

    public List<Activity> getFundraiserActivities() {
        return fundraiserActivities;
    }

    public void setFundraiserActivities(List<Activity> fundraiserActivities) {
        this.fundraiserActivities = fundraiserActivities;
    }

    public Activity getActiveActivity() {
        return activeActivity;
    }

    public void setActiveActivity(Activity activeActivity) {
        this.activeActivity = activeActivity;
    }

    public void prepareActivityFundraisingResult() {
        if (activeActivity != null) {
            activityFundraisingResult = webAppFacade.getActivityFundraisingResult(activeActivity);
        }

    }

    public void displayActivitiyDetails(Activity activity) {
        activeActivity = activity;
        prepareActivityFundraisingResult();

    }

    public WebAppFacade getWebAppFacade() {
        return webAppFacade;
    }

    public void setWebAppFacade(WebAppFacade webAppFacade) {
        this.webAppFacade = webAppFacade;
    }

    public ActivityFundraisingResult getActivityFundraisingResult() {
        return activityFundraisingResult;
    }

    public void setActivityFundraisingResult(ActivityFundraisingResult activityFundraisingResult) {
        this.activityFundraisingResult = activityFundraisingResult;
    }
    
    public void activityChanged(AjaxBehaviorEvent event)
    {
            Activity activity = (Activity) ((HtmlSelectOneMenu) event.getSource()).getValue();
            displayActivitiyDetails(activity);

    }

    public Fundraiser getActiveFundraiser() {
        return activeFundraiser;
    }

    public void setActiveFundraiser(Fundraiser activeFundraiser) {
        this.activeFundraiser = activeFundraiser;
    }

  
    
    
    
}
