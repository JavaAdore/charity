/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.dto.CauseFundRaisingResult;
import com.webapps2015.entity.Cause;
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
public class CharityHomeBean implements Serializable {

    @EJB
    WebAppFacade webAppFacade;

    private List<Cause> charityCauses;
    private CauseFundRaisingResult causeFundriasingResult;

    @PostConstruct
    public void init() {
        charityCauses = webAppFacade.loadAllCharityCauses(WebUtils.getCurrentCharity());
        if (charityCauses.size() > 0) {
            prepareCauseFundrisingResult(charityCauses.get(0));
        }
    }

    private void prepareCauseFundrisingResult(Cause cause) {
        causeFundriasingResult = webAppFacade.getCauseFundringResult(cause);
    }

    public List<Cause> getCharityCauses() {
        return charityCauses;
    }

    public void setCharityCauses(List<Cause> charityCauses) {
        this.charityCauses = charityCauses;
    }

    public CauseFundRaisingResult getCauseFundriasingResult() {
        return causeFundriasingResult;
    }

    public void setCauseFundriasingResult(CauseFundRaisingResult causeFundriasingResult) {
        this.causeFundriasingResult = causeFundriasingResult;
    }

  
    
    
      public void causeChanged(AjaxBehaviorEvent event) {
          
        Cause cause = (Cause) ((HtmlSelectOneMenu) event.getSource()).getValue();
        
          prepareCauseFundrisingResult(cause);
    }

}
