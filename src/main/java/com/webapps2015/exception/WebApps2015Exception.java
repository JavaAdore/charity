/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.exception;

/**
 *
 * @author dar27
 */
public class WebApps2015Exception extends Exception {

    private String customMessage;

    public WebApps2015Exception() {
    }

    public WebApps2015Exception(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

}
