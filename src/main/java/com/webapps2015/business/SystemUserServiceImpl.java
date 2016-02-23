/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.webapps2015.business;

import com.webapps2015.dao.SystemUserDAO;
import com.webapps2015.entity.Admin;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.entity.Roles;
import com.webapps2015.entity.SystemUserGroup;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * 
 * @author dar27
 */
@Stateless
public class SystemUserServiceImpl implements SystemUserService {

    @EJB
    SystemUserDAO systemUserDAO;

    @Override
    public void test() {
    }

    @Override
    public List<Roles> loadAllRoles() {
        return systemUserDAO.loadAllRoles();
    }

    @Override
    public Roles getRole(Long id) {
        return systemUserDAO.getRole(id);
    }

    @Override
    public boolean isEmailExist(String email) {
        SystemUser systemUser = getUserByEmail(email);
        return systemUser != null;
    }

    @Override
    public SystemUser getUserByEmail(String email) {

        return systemUserDAO.getUserByEmail(email);
    }

    @Override
    public void insertLookUps() {

        systemUserDAO.insertRole(Roles.FUNDRAISER);
        systemUserDAO.insertRole(Roles.CHARITIES);
        systemUserDAO.insertRole(Roles.ADMINS);
      
        

    }

    @Override
    public Fundraiser addOrUpdateFundriser(Fundraiser fundraiser) {
        return systemUserDAO.addOrUpdateFundriser(fundraiser);
    }

    @Override
    public List<Charity> getAllCharities() {
        return systemUserDAO.getAllCharities();
    }

    @Override
    public Charity getCharityById(Long id) {
        return systemUserDAO.getCharityById(id);
    }

    @Override
    public List<Fundraiser> getAllUsers() {
        return systemUserDAO.getAllUsers();
    }

    @Override
    public Charity addCharity(Charity charity) {
        return systemUserDAO.addCharity(charity);
    }

    @Override
    public SystemUserGroup addUserGroup(SystemUserGroup systemUserGroup) {
        systemUserDAO.addUserGroup(new SystemUserGroup(systemUserGroup.getUsername(), Roles.COMMON.getName()));
       return  systemUserDAO.addUserGroup(systemUserGroup);  
    }

    @Override
    public Fundraiser getFundraiser(Long id) {
        return systemUserDAO.getFundraiser(id);
    }

    @Override
    public Admin addOrUpdateAdmin(Admin admin) {
        return systemUserDAO.addOrUpdateAdmin(admin);
    }

}
