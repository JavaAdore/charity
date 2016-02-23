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
@Table(name = "ROLES")
public class Roles implements Serializable {

    public final static Roles FUNDRAISER = new Roles(1L, "Fundraiser");
    public final static Roles CHARITIES = new Roles(2L, "Charity");
    public final static Roles ADMINS = new Roles(3L, "Admin");
    public final static Roles COMMON = new Roles(3L, "Common");
 
    @Id
    private Long id;

    @Column(name = "ROLENAME")
    private String name;

    public Roles() {
    }

    public Roles(String name) {
        this.name = name;
    }

    public Roles(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Roles other = (Roles) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
