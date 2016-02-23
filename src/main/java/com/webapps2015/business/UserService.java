package com.webapps2015.business;

import com.webapps2015.entity.SystemUser;
import com.webapps2015.entity.Roles;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author parisis
 */
@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public UserService() {
    }

    public void registerUser(String username, String userpassword, String name, String surname) {
        try {
            SystemUser sys_user;
            Roles sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);

            // apart from the default constructor which is required by JPA
            // you need to also implement a constructor that will make the following code succeed
            
//            sys_user = new SystemUser(username, paswdToStoreInDB, name, surname);
//            sys_user_group = new Roles(username, "users");

//            em.persist(sys_user);
//            em.persist(sys_user_group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
