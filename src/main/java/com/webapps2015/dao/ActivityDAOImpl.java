/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.dao;

import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author dar27
 */
@Stateless
public class ActivityDAOImpl extends AbstractDAO implements ActivityDAO {

    @Override
    public Activity getActivity(Fundraiser creator, Cause cause) {
        Query query = entityManager.createQuery("SELECT o from Activity o where o.cause=:cause and o.creator =:creator");
        query.setParameter("cause", cause);
        query.setParameter("creator", creator);
        List result = query.getResultList();
        if (result.size() == 0) {
            return null;
        }
        return (Activity) result.get(0);
    }

    @Override
    public Activity addActivity(Activity activity) {
        return (Activity) super.add(activity);
    }

    @Override
    public List<Activity> loadAllActivitiesCreatedBy(Fundraiser currentFundraiser) {
        Query query = entityManager.createQuery("SELECT o from Activity o where o.creator=:creator");
        query.setParameter("creator", currentFundraiser);
        return query.getResultList();
    }

    @Override
    public List<Activity> getAllActivitiesExceptOfWhichCreatedBy(Fundraiser fundraiser) {
        Query query = entityManager.createQuery("SELECT o from Activity o where o.creator<>:creator");
        query.setParameter("creator", fundraiser);
        return query.getResultList();
    }

     @Override
    public Activity getactivityById(Long id) {
        return (Activity) super.find(Activity.class, id);
    }

}
