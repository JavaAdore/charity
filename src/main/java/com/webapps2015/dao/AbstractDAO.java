/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.dao;

import com.webapps2015.entity.Fundraiser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dar27
 */
@Stateless
public class AbstractDAO<T> {

    @PersistenceContext(unitName = "myUnit")
    protected EntityManager entityManager;

    public Object add(Object obj) {
        return entityManager.merge(obj); 
    }

    public List getAll(Class cls) {
        try { 
            Query query = entityManager.createQuery("SELECT o FROM " + cls.getSimpleName() + " o");

            return query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Object find( Class clazz , Long id) {
        return entityManager.find(clazz, id);
    }

}
