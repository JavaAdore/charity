/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.business;

import com.webapps2015.dto.*;
import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Admin;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Donation;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUserGroup;
import com.webapps2015.exception.WebApps2015Exception;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dar27
 */
@Local
public interface WebAppFacade {

    void test();

    public List<Roles> loadAllRoles();

    public Roles getRole(Long id);

    public void isEmailExist(String email) throws WebApps2015Exception;

    public void insertLookUps();

    public SystemUser addOrUpdateFundriser(com.webapps2015.entity.Fundraiser systemUser) throws WebApps2015Exception;

    public Charity registerCharity(Charity charity) throws WebApps2015Exception;

    public List<Cause> loadAllCharityCauses(Charity currentCharity);

    public Cause addOrUpdateCause(Cause activeCause);

    public List<Charity> getAllCharities();
    
    public Cause getCauseById(Long id);
    
    public Charity getCharityById(Long id);

    public Activity addActivity(Activity activity)throws WebApps2015Exception;

    public List<Activity> loadAllActivitiesCreatedBy(Fundraiser currentFundraiser);

    public List<Fundraiser> getAllUsers();
    
    public List<Activity> getAllActivitiesExceptOfWhichCreatedBy(Fundraiser fundraiser);
    
    public String donate(Donation donation)throws WebApps2015Exception;
    
    public Activity getActivityById(Long id);
    
    public SystemUserGroup addUserGroup(SystemUserGroup systemUserGroup);

    public List<Activity> getAllActivitiesCreatedBy(Fundraiser fundraiser);

    public ActivityFundraisingResult getActivityFundraisingResult(Activity activity);
    
    public CauseFundRaisingResult getCauseFundringResult(Cause cause);
    
    public Fundraiser getFudraiser(Long id);

    public Admin addAdmin(Admin admin) throws WebApps2015Exception;

    public SystemUser simulateAuthentication(String userName, String password);
    
    public SystemUser getSystemUserByEmail(String email);
    
}
