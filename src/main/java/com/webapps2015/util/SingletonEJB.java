/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.util;

import com.webapps2015.entity.Donation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author dar27
 */
@Singleton(mappedName = "singletonEJB", name = "singletonEJB")
public class SingletonEJB implements Serializable {

    private List<String> registeredCharities;
    private List<Donation> donations;

    @PostConstruct
    public void init() {
        donations = new ArrayList();
        registeredCharities = new ArrayList();
        registeredCharities.add("MSF");
        registeredCharities.add("ABC");
        registeredCharities.add("XYZ");
        registeredCharities.add("KLM");

    }
    
    
    public void registerNewDonation(Donation donation)
    {
        donations.add(donation);
    }

    public boolean isRegisteredCharity(String charityUniqueName)
    {
        return registeredCharities.contains(charityUniqueName);
    }
    
}
