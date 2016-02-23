/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.webapps2015.dto.CauseFundRaisingResult;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author dar27
 */
@ManagedBean
@ViewScoped
public class CharitiesBean implements Serializable {

    @EJB
    WebAppFacade webAppFacade;

    private List<Charity> charities;

    private CauseFundRaisingResult causeFundriasingResult;

    private List<Cause> causes;

    private Charity activeCharity;

    private Cause activeCause;

    @PostConstruct
    public void init() {
        charities = webAppFacade.getAllCharities();
        if (charities.size() > 0) {
            activeCharity = charities.get(0);
            loadCharityCauses(charities.get(0));
        }
    }

    private void loadCharityCauses(Charity charity) {
        causes = webAppFacade.loadAllCharityCauses(charity);
        if (causes.size() > 0) {
            activeCause = causes.get(0);
            loadCharityCauseResult(causes.get(0));
        } else {
            activeCause = null;
        }

    }

    private void loadCharityCauseResult(Cause cause) {
        causeFundriasingResult = webAppFacade.getCauseFundringResult(cause);
    }

    public void charityChanged(AjaxBehaviorEvent event) {
        Charity charity = (Charity) ((HtmlSelectOneMenu) event.getSource()).getValue();

        loadCharityCauses(charity);
    }

    public void causeChanged(AjaxBehaviorEvent event) {
        Cause cause = (Cause) ((HtmlSelectOneMenu) event.getSource()).getValue();
        loadCharityCauseResult(cause);

    }

    public List<Charity> getCharities() {
        return charities;
    }

    public void setCharities(List<Charity> charities) {
        this.charities = charities;
    }

    public CauseFundRaisingResult getCauseFundriasingResult() {
        return causeFundriasingResult;
    }

    public void setCauseFundriasingResult(CauseFundRaisingResult causeFundriasingResult) {
        this.causeFundriasingResult = causeFundriasingResult;
    }


    
    

    public List<Cause> getCauses() {
        return causes;
    }

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public Charity getActiveCharity() {
        return activeCharity;
    }

    public void setActiveCharity(Charity activeCharity) {
        this.activeCharity = activeCharity;
    }

    public Cause getActiveCause() {
        return activeCause;
    }

    public void setActiveCause(Cause activeCause) {
        this.activeCause = activeCause;
    }
    
    
}
