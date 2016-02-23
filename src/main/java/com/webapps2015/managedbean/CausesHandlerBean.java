/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.Constant;
import com.webapps2015.util.Utils;
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
public class CausesHandlerBean implements Serializable {

    @EJB
    private WebAppFacade webAppFacade;

    Set<Cause> causes;
    Charity currentCharity;
    Cause activeCause;
    @PostConstruct
    public void init() {
 
        currentCharity= webAppFacade.getAllCharities().get(0);  
        causes = new HashSet(webAppFacade.loadAllCharityCauses(currentCharity));
        if(causes.size()>0)
        {
            activeCause = causes.iterator().next();
        }
    }

    public void initiateNewCause() { 
        activeCause = new Cause();
        activeCause.setCharity(currentCharity);
       
    }

    public void addOrUpdateCause() {
        try {
            causes.remove(activeCause);
           Cause cause =  webAppFacade.addOrUpdateCause(activeCause);
          
           causes.add(cause);   
           initiateNewCause();
        } catch (Exception ex) {
            causes.add(activeCause);
            ex.printStackTrace();
           //  display message 
            System.out.println();
        }
    }

    public WebAppFacade getWebAppFacade() {
        return webAppFacade;
    }

    public void setWebAppFacade(WebAppFacade webAppFacade) {
        this.webAppFacade = webAppFacade;
    }

    public Set<Cause> getCauses() {
        return causes;
    }

    public void setCauses(Set<Cause> causes) {
        this.causes = causes;
    }

    public Charity getCurrentCharity() {
        return currentCharity;
    }

    public void setCurrentCharity(Charity currentCharity) {
        this.currentCharity = currentCharity;
    }

    public Cause getActiveCause() {
        return activeCause;
       
    }

    public void setActiveCause(Cause activeCause) {
        this.activeCause = activeCause;
    }
    
   
    
    /**/
        
 
    
    public void activeCauseChanged(AjaxBehaviorEvent event)
    {
        activeCause = (Cause) ((HtmlSelectOneMenu)event.getSource()).getValue();
    
    }
}
