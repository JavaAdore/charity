/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.dao;

import com.webapps2015.dao.AbstractDAO;
import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Donation;
import com.webapps2015.entity.SystemUser;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author dar27
 */
@Stateful
@RolesAllowed(value = {"Fundraiser","Admins"})
public class DonationDAOImpl extends AbstractDAO implements DonationDAO {

    @Override
    public Donation AddDonation(Donation donation) {
        return (Donation)super.add(donation);
    }

     @Override
    public List<Donation> getActivityDonations(SystemUser fundraiser, Activity activity) {
        Query query = entityManager.createQuery("SELECT o from Donation o where o.fundraiser =:fundraiser and o.activity=:activity");
        query.setParameter("fundraiser", fundraiser);
        query.setParameter("activity", activity);
        return query.getResultList();
    }
   

}
