/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author dar27
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SYSTEMUSER")
public class SystemUser implements Serializable {

    @Expose
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "USER_NAME")
    private String email;

    @Column(name = "USERPASSWORD")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Roles role;

    @Column(name = "creation_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    public SystemUser() {
    }

    public SystemUser(String username, String paswdToStoreInDB, Roles group) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles group) {
        this.role = group;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void loadSystemUserInfo(SystemUser systemUser) {
        if (systemUser != null) {
            email = systemUser.getEmail();
            password = systemUser.getPassword();
            role = systemUser.getRole();
            confirmPassword = systemUser.getConfirmPassword();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof SystemUser == false) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    
}
