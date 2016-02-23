/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dar27
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")

public class Charity extends SystemUser implements Serializable {

    @Column(name = "charity_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "unique_abbreviation")
    private String uniqueAbbriviation;

    @OneToMany(mappedBy = "charity")
    List<Cause> causes;

   

    @Column(name = "amount_of_credit")
    private Double credit;
    
    private String desciption;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public List<Cause> getCauses() {
        return causes;
    }

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public String getUniqueAbbriviation() {
        return uniqueAbbriviation;
    }

    public void setUniqueAbbriviation(String uniqueAbbriviation) {
        this.uniqueAbbriviation = uniqueAbbriviation;
    }

  
    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    
}
