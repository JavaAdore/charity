/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.dao;

import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Admin;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUserGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dar27
 */
@Local 
public interface SystemUserDAO  {
    
    public Fundraiser addOrUpdateFundriser(Fundraiser user);    

    public List<Roles> loadAllRoles();

    public Roles getRole(Long id);

    public SystemUser getUserByEmail(String email);

    public void insertRole(Roles FUNDRAISER);

    public List<Charity> getAllCharities();

    public Charity getCharityById(Long id);

    public List<Fundraiser> getAllUsers();

    public Fundraiser getUserById(Long id);

    public Charity addCharity(Charity charity);

    

    public Double getTotalAmountOfActivity(Activity activity);

    public Double getTotalAmountOfCause(Cause cause);
    
    public SystemUserGroup addUserGroup(SystemUserGroup systemUserGroup);

    public Fundraiser getFundraiser(Long id);

    public Admin addOrUpdateAdmin(Admin admin);
}
