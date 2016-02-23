/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author dar27
 */
@Entity
public class Admin extends Fundraiser implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "PARENT_ADMIN")
    private Admin creator;

    public Admin getCreator() {
        return creator;
    }

    public void setCreator(Admin creator) {
        this.creator = creator;
    }
    
    
    
    
}
