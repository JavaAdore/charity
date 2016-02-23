/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.util;

import com.webapps2015.dao.PageNameHolder;
import com.webapps2015.entity.Admin;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUser;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dar27
 */
public class WebUtils {

    public static void fireErrorMessage(String message) {
        fireMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void fireInfoMessage(String message) {
        fireMessage(FacesMessage.SEVERITY_INFO, message);

    }

    public static void fireMessage(FacesMessage.Severity severity,
            String messageToDisplay) {
        FacesContext
                .getCurrentInstance()
                .getCurrentInstance()
                .addMessage("",
                        new FacesMessage(severity, messageToDisplay, ""));

    }

    public static void redirectTo(String homePage) {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(homePage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String perpareWelcomeMessage(String firstName) {

        return String.format("%s ,", "welcome", firstName);
    }

    public static void fireDetailsIntoMessage(String message, String details) {
        fireDetailedMessage(FacesMessage.SEVERITY_INFO, message,
                details);

    }

    private static void fireDetailedMessage(Severity severity, String title,
            String details) {
        FacesContext.getCurrentInstance().getCurrentInstance()
                .addMessage("", new FacesMessage(severity, title, details));

    }

    public static void invalidateSession() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        session.invalidate();

    }

    public static String getRealPath(String str) {
        String url = null;
        try {
            ExternalContext ext = FacesContext.getCurrentInstance()
                    .getExternalContext();
            String path = ext.getRequestContextPath();
            path += path.endsWith("/") ? str : "/" + str;
            url = ext.encodeResourceURL(path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return url;
    }

    public static void injectIntoSession(String message, Object objectToInject) {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put(message, objectToInject);
    }

    public static Object extractFromSession(String message) {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Object objectToReturn = sessionMap.get(message);
        return objectToReturn;

    }

    public static void fireExactInfoMessage(String message) {

        FacesContext
                .getCurrentInstance()
                .getCurrentInstance()
                .addMessage(
                        "",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                                message));

    }

    public static void fireExactInfoMessage(String name, String details) {
        FacesContext
                .getCurrentInstance()
                .getCurrentInstance()
                .addMessage(
                        "",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, name,
                                details));

    }

    public static Object extractFromRequest(String message) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        Object attributeValue = request.getAttribute(message);
        return attributeValue;
    }

    public static Charity getCurrentCharity() {

        return (Charity) getCurrentSystemUser();
    }

    public static Fundraiser getCurentFundraiser() {
        return (Fundraiser) getCurrentSystemUser();

    }

    public static Admin getCurrentAdmin() {
        return (Admin) getCurrentSystemUser();

    }

    public static HttpServletRequest getCurrentRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static Principal getPrincipal() {
        HttpServletRequest currentRequest = getCurrentRequest();
        if (currentRequest != null) {
            return currentRequest.getUserPrincipal();
        }
        return null;
    }

    public static void injectSystemUserIntoSession(SystemUser systemUser) {

        if (systemUser.getRole().equals(Roles.ADMINS)) {
            injectIntoSession(Constant.CURRENT_USER, systemUser);
            injectIntoSession("role", Roles.ADMINS.getName());
        } else if (systemUser.getRole().equals(Roles.FUNDRAISER)) {
            injectIntoSession(Constant.CURRENT_USER, systemUser);
            injectIntoSession("role", Roles.FUNDRAISER.getName());

        } else {
            injectIntoSession(Constant.CURRENT_USER, systemUser);
            injectIntoSession("role", Roles.CHARITIES.getName());

        }
    }

    public static void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
            redirectTo(Constant.CONTEXT_PATH + "/LoginHandler.xhtml");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }

    public static SystemUser getCurrentSystemUser() {

        return (SystemUser) extractFromSession(Constant.CURRENT_USER);
    }

    public static List<PageNameHolder> getCurrentUserMenu() {

        return Constant.rolesPagesMap.get(getCurrentSystemUser().getRole());

    }

    
   
}
