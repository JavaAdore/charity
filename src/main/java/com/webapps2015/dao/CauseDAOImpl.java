/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.dao;

import com.webapps2015.dao.AbstractDAO;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author dar27
 */
@Stateful
public class CauseDAOImpl extends AbstractDAO implements CauseDAO {

    @Override
    public Cause addOrUpdateCause(Cause cause) {
        return (Cause) super.add(cause);
    }

    @Override
    public List<Cause> getCharityCauses(Charity charity, boolean activeOnly) {
        String queryString = "SELECT o from Cause o where o.charity =:charity ";
        if (activeOnly) {
            queryString += " o.status =1";
        }
        Query query = entityManager.createQuery(queryString);
        query.setParameter("charity", charity);
        return query.getResultList();

    }

    @Override
    public Cause getCauseById(Long id) {
        return (Cause) super.find(Cause.class, id);
    }

    @Override
    public Double getCharityTotalCausesFundraising(Charity charity) {

        Query query = entityManager.createQuery("SELECT sum (o.amout) FROM  Donation o where o.activity.cause in (SELECT x from Cause x where x.charity=:charity)");
        query.setParameter("charity", charity);
        return (query.getResultList().get(0) != null) ? (Double) query.getResultList().get(0) : 0;

    }

    @Override
    public Double getCauseFunding(Cause cause) {

        Query query = entityManager.createQuery("SELECT sum (o.amout) FROM  Donation o where o.activity.cause =:cause");
        query.setParameter("cause", cause);
        return (query.getResultList().get(0) != null) ? (Double) query.getResultList().get(0) : 0;
    }

}
