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
import com.webapps2015.util.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author dar27
 */
@Stateless
public class SystemUserDAOImpl extends AbstractDAO implements SystemUserDAO {

    @Override
    public Fundraiser addOrUpdateFundriser(Fundraiser user) {
        return super.entityManager.merge(user);
    }

    @Override
    public List<Roles> loadAllRoles() {
        Query query = entityManager.createQuery("SELECT o FROM  Roles o where o  <> :admin ");
        query.setParameter("admin", Roles.ADMINS);
        List result = query.getResultList();
        return result;
    }

    @Override
    public Roles getRole(Long id) {
        return (Roles) find(Roles.class, id);
    }

    @Override
    public SystemUser getUserByEmail(String email) {
        Query query = entityManager.createQuery("SELECT o FROM  SystemUser o where o.email=:email");
        query.setParameter("email", email);
        List result = query.getResultList();
        if (Utils.isEmpty(result)) {
            return null;
        }
        return (SystemUser) result.get(0);

    }

    @Override
    public void insertRole(Roles role) {
        super.add(role);
    }

    @Override
    public List<Charity> getAllCharities() {
        return super.getAll(Charity.class);
    }

    @Override
    public Charity getCharityById(Long id) {
        return (Charity) super.find(Charity.class, id);
    }

    @Override
    public List<Fundraiser> getAllUsers() {
        Query query = entityManager.createQuery("SELECT o from Fundraiser o where o.role<>:adminRole");
        query.setParameter("adminRole", Roles.ADMINS);
        return query.getResultList();
        
    }

    @Override
    public Fundraiser getUserById(Long id) {

        return (Fundraiser) super.find(Fundraiser.class, id);
    }

    @Override
    public Charity addCharity(Charity charity) {
        return (Charity) super.add(charity);
    }

    @Override
    public SystemUserGroup addUserGroup(SystemUserGroup systemUserGroup) {
        return (SystemUserGroup) super.entityManager.merge(systemUserGroup);
    }

    @Override
    public Double getTotalAmountOfActivity(Activity activity) {
        Query query = entityManager.createQuery("SELECT sum (o.amout) FROM  Donation o where o.activity =:activity");
        query.setParameter("activity", activity);
        return (query.getResultList().get(0) != null) ? (Double) query.getResultList().get(0) : 0;

    }

    @Override
    public Double getTotalAmountOfCause(Cause cause) {
        Query query = entityManager.createQuery("SELECT sum (o.amout) FROM  Donation o where o.activity.cause =:cause");
        query.setParameter("cause", cause);
        return (query.getResultList().get(0) != null) ? (Double) query.getResultList().get(0) : 0;
    }

    @Override
    public Fundraiser getFundraiser(Long id) {

        return (Fundraiser) super.find(Fundraiser.class, id);
    }

    @Override
    public Admin addOrUpdateAdmin(Admin admin) {
        return (Admin) super.add(admin);
    }

}
