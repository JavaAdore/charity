    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dar27
 */
@Entity
public class Cause implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "cause")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "creator_charity", nullable = false)
    private Charity charity;

    @Column(name = "total_amount_of_donation")
    private Double totalAmount = 0D;
    
    @Column(name = "creation_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date causeCreationDate = new Date();

    private String description;

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

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Cause other = (Cause) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    public Date getCauseCreationDate() {
        return causeCreationDate;
    }

    public void setCauseCreationDate(Date causeCreationDate) {
        this.causeCreationDate = causeCreationDate;
    }

}
