/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dar27
 */
@Entity
@Table(name = "SYSTEMUSERGROUP")
public class SystemUserGroup implements Serializable{
    
    @Id
    @Column(name="USER_NAME")
    private String username;
    
    @Id
    @Column(name="GROUPNAME")
    private String groupName;

    public SystemUserGroup() {
    }

    public SystemUserGroup(SystemUser systemUser){
        this.username = systemUser.getEmail();
        this.groupName = systemUser.getRole().getName();
    }
    
    public SystemUserGroup(String username, String groupName) {
        this.username = username;
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserGroup other = (SystemUserGroup) obj;
        if (this.username != other.username && (this.username == null || !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }
    
}
