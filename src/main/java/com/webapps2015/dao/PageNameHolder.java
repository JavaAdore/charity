/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.dao;

import java.io.Serializable;

/**
 *
 * @author dar27
 */
public class PageNameHolder implements Serializable{
    
    private String name;
    private String URL;

    public PageNameHolder() {
    }

    public PageNameHolder(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
    
}
