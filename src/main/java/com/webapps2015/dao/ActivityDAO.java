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
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dar27
 */
@Local
public interface ActivityDAO {

    public Activity getActivity(Fundraiser creator, Cause cause);

    public Activity addActivity(Activity activity);

    public List<Activity> loadAllActivitiesCreatedBy(Fundraiser curentFundraiser);

    public List<Activity> getAllActivitiesExceptOfWhichCreatedBy (Fundraiser fundraiser);

    public Activity getactivityById(Long id);



}
