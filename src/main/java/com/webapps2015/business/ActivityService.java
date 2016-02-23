/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.business;

import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Donation;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.exception.WebApps2015Exception;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import com.webapps2015.dto.ActivityFundraisingResult;

/**
 *
 * @author dar27
 */
@Local
public interface ActivityService {

    public Activity getActivity(Fundraiser creator, Cause cause);
    public Activity addActivity(Activity activity);

    public  List<Activity> loadAllActivitiesCreatedBy(Fundraiser currentFundraiser);

    public List<Activity> getAllActivitiesExceptOfWhichCreatedBy(Fundraiser fundraiser);

    public String donate(Donation donation) throws WebApps2015Exception;

    public Activity getActivityById(Long id);
    
    public ActivityFundraisingResult getActivityFundraisingResult(Activity activity);
    
    
}
