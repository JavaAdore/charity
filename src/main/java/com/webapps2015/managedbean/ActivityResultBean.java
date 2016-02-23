/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.dto.*;
import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.util.WebUtils;
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
public class ActivityResultBean implements Serializable {
 
    @EJB
    WebAppFacade webAppFacade;

    List<Activity> usersActivities;
    ActivityFundraisingResult activityFundraisingResult;

    @PostConstruct
    public void init() {
        Fundraiser fundraiser =WebUtils.getCurentFundraiser();
        usersActivities = webAppFacade.getAllActivitiesCreatedBy(fundraiser);
        if (usersActivities.size()>0) {
            activityFundraisingResult = webAppFacade.getActivityFundraisingResult(usersActivities.get(0));
        }
    }

    public List<Activity> getUsersActivities() {
        return usersActivities;
    }

    public void setUsersActivities(List<Activity> usersActivities) {
        this.usersActivities = usersActivities;
    }

    public ActivityFundraisingResult getActivityFundraisingResult() {
        return activityFundraisingResult;
    }

    public void setActivityFundraisingResult(ActivityFundraisingResult activityFundraisingResult) {
        this.activityFundraisingResult = activityFundraisingResult;
    }

    public void activityChanged(AjaxBehaviorEvent ajaxBehaviorEvent) {
        Activity activity = (Activity) ((HtmlSelectOneMenu) ajaxBehaviorEvent.getSource()).getValue();
        activityFundraisingResult = webAppFacade.getActivityFundraisingResult(activity);

    }
}
