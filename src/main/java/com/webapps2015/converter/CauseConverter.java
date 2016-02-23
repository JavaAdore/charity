/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.converter;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Roles;
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
public class CauseConverter implements Converter {

    
    @EJB
    WebAppFacade webAppFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
          Cause systemUserGroup  = null;
        if(Utils.isNotEmpty(value)&&Utils.isNumber(value))
        {
             systemUserGroup  = (Cause)webAppFacade.getCauseById(Long.valueOf(value));
        }
        
        return systemUserGroup;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

		if(value !=null && value instanceof Cause )
                {
                 Cause objectToConvert =    (Cause) value;
                    if( objectToConvert.getId() !=null )
                    {
                        return objectToConvert.getId().toString();
                    }
                }
                return "";
    }

}
