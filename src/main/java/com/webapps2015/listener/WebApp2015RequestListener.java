/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.listener;

import com.webapps2015.util.Constant;
import javax.naming.InitialContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author dar27
 */
public class WebApp2015RequestListener implements  ServletRequestListener{

    boolean flag = false;
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        if(flag==false)
        {
          String protocol =   sre.getServletRequest().getScheme();
          String serverName=   sre.getServletRequest().getServerName();
          Integer port = sre.getServletRequest().getServerPort();
          String contextPath = sre.getServletContext().getContextPath();
          Constant.setContextPath(protocol,serverName,port,contextPath);
          flag = true;
        }
    
    }
    
}
