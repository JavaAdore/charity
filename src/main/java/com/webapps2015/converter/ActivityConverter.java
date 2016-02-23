/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.converter;

import com.webapps2015.business.WebAppFacade;
import com.webapps2015.entity.Activity;
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
public class ActivityConverter implements Converter {

    
    @EJB
    WebAppFacade webAppFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
          Activity activity  = null;
        if(Utils.isNotEmpty(value)&&Utils.isNumber(value))
        {
             activity  = (Activity)webAppFacade.getActivityById(Long.valueOf(value));
        }
        
        return activity;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

		if(value !=null && value instanceof Activity )
                {
                 Activity objectToConvert =    (Activity) value;
                    if( objectToConvert.getId() !=null )
                    {
                        return objectToConvert.getId().toString();
                    }
                }
                return "";
    }

}
