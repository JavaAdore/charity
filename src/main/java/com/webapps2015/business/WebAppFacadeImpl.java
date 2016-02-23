/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.business;

import com.webapps2015.dto.ActivityFundraisingResult;
import com.webapps2015.dto.CauseFundRaisingResult;
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
import com.webapps2015.util.Constant;
import com.webapps2015.util.ParamType;
import com.webapps2015.util.Utils;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author dar27
 */
@Stateless
public class WebAppFacadeImpl implements WebAppFacade {

    @EJB
    private SystemUserService systemUserService;

    @EJB
    private CauseService causeService;

    @EJB
    private ActivityService activityService;

    @Override
    public void test() {
        systemUserService.test();
    }

    @Override
    public List<Roles> loadAllRoles() {
        return systemUserService.loadAllRoles();
    }

    @Override
    public Roles getRole(Long id) {
        return systemUserService.getRole(id);
    }

    @Override
    public void isEmailExist(String email) throws WebApps2015Exception {
        if (systemUserService.isEmailExist(email)) {
            throw new WebApps2015Exception("Email already exists");
        }

    }

    @Override
    public void insertLookUps() {
        systemUserService.insertLookUps();
        try {
            if (getSystemUserByEmail("admin@app.com") == null) {
                Admin admin = new Admin();
                admin.setEmail("admin@app.com");
                admin.setPassword("P@ssW0rd");
                admin.setConfirmPassword("P@ssW0rd");
                admin.setRole(Roles.ADMINS);
                addAdmin(admin);
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public SystemUser addOrUpdateFundriser(com.webapps2015.entity.Fundraiser systemUser) throws WebApps2015Exception {
        validateSystemUser(systemUser);
        Fundraiser fr = systemUserService.addOrUpdateFundriser(systemUser);
        systemUserService.addUserGroup(new SystemUserGroup(systemUser));

        return fr;
    }

    private void validatePasswords(SystemUser systemUser) throws WebApps2015Exception {
        if (systemUser.getPassword().equals(systemUser.getConfirmPassword()) == false) {
            throw new WebApps2015Exception("Passwords do not match");
        }
    }

    private void hashPassowrd(SystemUser systemUser) throws WebApps2015Exception {
        try {
            systemUser.setPassword(Utils.hash(systemUser.getPassword()));
        } catch (Exception ex) {
            throw new WebApps2015Exception("Error Related To Password");
        }
    }

    @Override
    public Charity registerCharity(Charity charity) throws WebApps2015Exception {
        validateSystemUser(charity);
        isCharityRegisterd(charity);
        Charity ch = (Charity) systemUserService.addCharity(charity);
        systemUserService.addUserGroup(new SystemUserGroup(charity));
        return ch;
    }

    private void validateSystemUser(SystemUser systemUser) throws WebApps2015Exception {
        isEmailExist(systemUser.getEmail());
        validatePasswords(systemUser);
        hashPassowrd(systemUser);

    }

    private void isCharityRegisterd(Charity charity) throws WebApps2015Exception {
        try {

            Map params = new HashMap();
            params.put("first", charity.getUniqueAbbriviation());
            String result = (String) Utils.consumeGetWebService(Constant.getApplicationWebServicesPath() + "charity", params, ParamType.PATH_PARAM, String.class);
            boolean r = result.equalsIgnoreCase("true");
            if (!r) {
                throw new WebApps2015Exception("Not verified Charity " + charity.getUniqueAbbriviation());
            }
        } catch (WebApps2015Exception ex) {
            throw ex;
        } catch (Exception ex) {
            throw new WebApps2015Exception("Error while verifying charity .., ");
        }
    }

    @Override
    public List<Cause> loadAllCharityCauses(Charity currentCharity) {
        return causeService.getCharityCauses(currentCharity);
    }

    @Override
    public Cause addOrUpdateCause(Cause activeCause) {
        return causeService.addOrUpdateCause(activeCause);
    }

    @Override
    public List<Charity> getAllCharities() {
        return systemUserService.getAllCharities();
    }

    @Override
    public Cause getCauseById(Long id) {
        return causeService.getCauseById(id);
    }

    @Override
    public Charity getCharityById(Long id) {
        return systemUserService.getCharityById(id);
    }

    @Override
    public Activity addActivity(Activity activity) throws WebApps2015Exception {
        checkActivityDuplication(activity);
        return activityService.addActivity(activity);

    }

    private void checkActivityDuplication(Activity activity) throws WebApps2015Exception {
        activity = getActivity(activity.getCreator(), activity.getCause());
        if (activity != null) {
            throw new WebApps2015Exception("Same activity already exists under name " + activity.getActivityName());
        }
    }

    private Activity getActivity(Fundraiser creator, Cause cause) {
        return activityService.getActivity(creator, cause);
    }

    @Override
    public List<Activity> loadAllActivitiesCreatedBy(Fundraiser currentFundraiser) {
        return activityService.loadAllActivitiesCreatedBy(currentFundraiser);
    }

    @Override
    public List<Fundraiser> getAllUsers() {
        return systemUserService.getAllUsers();
    }

    @Override
    public List<Activity> getAllActivitiesExceptOfWhichCreatedBy(Fundraiser fundraiser) {
        return activityService.getAllActivitiesExceptOfWhichCreatedBy(fundraiser);
    }

    @Override
    public String donate(Donation donation) throws WebApps2015Exception {
        return activityService.donate(donation);
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityService.getActivityById(id);
    }

    @Override
    public SystemUserGroup addUserGroup(SystemUserGroup systemUserGroup) {
        return systemUserService.addUserGroup(systemUserGroup);
    }

    @Override
    public List<Activity> getAllActivitiesCreatedBy(Fundraiser fundraiser) {
        return activityService.loadAllActivitiesCreatedBy(fundraiser);
    }

    @Override
    public ActivityFundraisingResult getActivityFundraisingResult(Activity activity) {
        return activityService.getActivityFundraisingResult(activity);
    }

    @Override
    public CauseFundRaisingResult getCauseFundringResult(Cause cause) {

        return causeService.getCauseFundringResult(cause);
    }

    @Override
    public Fundraiser getFudraiser(Long id) {

        return systemUserService.getFundraiser(id);
    }

    @Override
    public Admin addAdmin(Admin admin) throws WebApps2015Exception {
        validateSystemUser(admin);
        admin = systemUserService.addOrUpdateAdmin(admin);
        systemUserService.addUserGroup(new SystemUserGroup(admin));
        return admin;

    }

    @Override
    public SystemUser simulateAuthentication(String userName, String password) {
        SystemUser systemUser = systemUserService.getUserByEmail(userName);
        if (systemUser != null) {
            try {
                if (systemUser.getPassword().equals(Utils.hash(password))) {
                    return systemUser;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebAppFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebAppFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public SystemUser getSystemUserByEmail(String email) {
        return systemUserService.getUserByEmail(email);
    }

}
