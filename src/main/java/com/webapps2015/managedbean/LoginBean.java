/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.managedbean;

import com.webapps2015.util.ParamType;
import com.webapps2015.util.Utils;
import com.webapps2015.util.WebUtils;
import com.webapps2015.business.WebAppFacade;
import com.webapps2015.dao.AbstractDAO;
import com.webapps2015.entity.Fundraiser;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author dar27
 */
@ManagedBean 
@ViewScoped 
public class LoginBean implements Serializable {

    @EJB
    WebAppFacade webAppFacade;

    public String login() {
 
       webAppFacade.test();
       WebUtils.fireInfoMessage("error");
       return "wrongUserNameOrPassword"; 
    }

}
