/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.util;

import com.webapps2015.dao.PageNameHolder;
import com.webapps2015.entity.Roles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dar27
 */
public class Constant {

    private static String WEB_SERVICE_LOCATION;
   
    public final static String CURRENT_USER = "CURRENT_USER";
    public final static double MAX_ALLOWED_BATCH_DONATION_AMOUNT = 500D;
    public static String CONTEXT_PATH = null;

    public final static List<PageNameHolder> CHARITIES_PAGES = new ArrayList();
    public final static List<PageNameHolder> ADMINS_PAGES = new ArrayList();
    public final static List<PageNameHolder> FUNDRISERS_PAGES = new ArrayList();
    public final static Map<Roles, List<PageNameHolder>> rolesPagesMap = new HashMap();

    static {
        CHARITIES_PAGES.add(new PageNameHolder("View Charity Causes", "Home.xhtml"));
        CHARITIES_PAGES.add(new PageNameHolder("Add\\Edit Cause", "causes.xhtml"));
    }

    static {

        ADMINS_PAGES.add(new PageNameHolder("Fundraisers", "Fundraisers.xhtml"));
        ADMINS_PAGES.add(new PageNameHolder("Charities", "Charities.xhtml"));
        ADMINS_PAGES.add(new PageNameHolder("Add Admin", "AddAdmin.xhtml"));

    }

    static {

        FUNDRISERS_PAGES.add(new PageNameHolder("Activities", "activity.xhtml"));
        FUNDRISERS_PAGES.add(new PageNameHolder("Donate", "donation.xhtml"));
        FUNDRISERS_PAGES.add(new PageNameHolder("View My Activities", "UserActivitiesResult.xhtml"));

    }

    static {
        rolesPagesMap.put(Roles.ADMINS, ADMINS_PAGES);
        rolesPagesMap.put(Roles.FUNDRAISER, FUNDRISERS_PAGES);
        rolesPagesMap.put(Roles.CHARITIES, CHARITIES_PAGES);

    }

    public static void setContextPath(String webContentRoot) {
        if (WEB_SERVICE_LOCATION == null) {
            WEB_SERVICE_LOCATION = String.format("%s/ws/services/", webContentRoot);
        }
    }

    public static String getApplicationWebServicesPath() {
        return WEB_SERVICE_LOCATION;
    }

    public static String getCharityValidationWebServiceURL() {
        return WEB_SERVICE_LOCATION + "/charity";
    }

    public static void setContextPath(String protocol, String serverName, Integer port, String contextPath) {
        if (WEB_SERVICE_LOCATION == null) {
            CONTEXT_PATH = contextPath;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(protocol + "://");
            stringBuilder.append(serverName);
            if (port != null && port != 0) {
                stringBuilder.append(":" + port);
            }
            stringBuilder.append(contextPath);
            stringBuilder.append("/ws/services/");
            WEB_SERVICE_LOCATION = stringBuilder.toString();
        }
    }
}
