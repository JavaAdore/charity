/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.business;

import com.webapps2015.entity.Admin;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUserGroup;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;

/** 
 *
 * @author dar27   
 */
@Local
public interface SystemUserService {

    public void test();

    public List<Roles> loadAllRoles();

    public Roles getRole(Long id);

    public boolean isEmailExist(String email);
    
        public SystemUser getUserByEmail(String email) ;

    public void insertLookUps();

    public Fundraiser addOrUpdateFundriser(Fundraiser fundraiser);

    public List<Charity> getAllCharities();

    public Charity getCharityById(Long id);

    public List<Fundraiser> getAllUsers();
    
    public Charity addCharity(Charity charity);
    
    public SystemUserGroup addUserGroup(SystemUserGroup systemUserGroup);

    public Fundraiser getFundraiser(Long id);

    public Admin addOrUpdateAdmin(Admin admin);
}
