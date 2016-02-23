/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.WebUtils;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class ActivityBeanHandler implements Serializable {

    @EJB
    WebAppFacade webAppFacade;

    private Activity activity;
    private Set<Activity> userActivities;
    private List<Charity> charities;
    private List<Cause> causes;

    @PostConstruct
    public void init() {
        
        loadAllCharitiesAndItsCauses();
        loadAllActivitiesWhichCreatedByUser();
        initializeNewActivityToBePersisted();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Set<Activity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(Set<Activity> userActivities) {
        this.userActivities = userActivities;
    }

    public List<Charity> getCharities() {
        return charities;
    }

    public void setCharities(List<Charity> charities) {
        this.charities = charities;
    }

    public List<Cause> getCauses() {
        return causes;
    }

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public void charityChanged(AjaxBehaviorEvent event) {
        Charity charity = (Charity) ((HtmlSelectOneMenu) event.getSource()).getValue();
        if (charity != null) {
            causes = webAppFacade.loadAllCharityCauses(charity);
        }
    }

    public void addActivity() { 
        try {
           
          activity =   webAppFacade.addActivity(activity);
          userActivities.add(activity);
            initializeNewActivityToBePersisted();
            WebUtils.fireInfoMessage("Activity Added");
        } catch (WebApps2015Exception ex) { 
            WebUtils.fireErrorMessage(ex.getCustomMessage());
        }

    }

    private void initializeNewActivityToBePersisted() {
        activity = new Activity();
        activity.setCreator(WebUtils.getCurentFundraiser());
    }

    private void loadAllCharitiesAndItsCauses() {
        charities = webAppFacade.getAllCharities();
        if (charities.size() > 0) {
            causes = webAppFacade.loadAllCharityCauses(charities.get(0));
        }
    }

    private void loadAllActivitiesWhichCreatedByUser() {
        userActivities = new HashSet(webAppFacade.loadAllActivitiesCreatedBy(webAppFacade.getAllUsers().get(0)));
    }

}
