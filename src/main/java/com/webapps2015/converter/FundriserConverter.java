/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.converter;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.util.Utils;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dar27
 */
@ManagedBean
@ApplicationScoped
public class FundriserConverter implements Converter {

    
    @EJB
    WebAppFacade webAppFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
          Fundraiser fundraiser  = null;
        if(Utils.isNotEmpty(value)&&Utils.isNumber(value))
        {
             fundraiser  = (Fundraiser)webAppFacade.getFudraiser(Long.valueOf(value));
        }
        
        return fundraiser;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

		if(value !=null && value instanceof Fundraiser )
                {
                 Fundraiser objectToConvert =    (Fundraiser) value;
                    if( objectToConvert.getId() !=null )
                    {
                        return objectToConvert.getId().toString();
                    }
                }
                return "";
    }

}
