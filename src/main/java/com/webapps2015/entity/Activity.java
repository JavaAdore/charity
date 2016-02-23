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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dar27
 */
@Entity
public class Activity implements Serializable {

    @Expose
    @GeneratedValue
    @Id
    private Long id;

    private String activityName;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Fundraiser creator;

    @ManyToOne
    @JoinColumn(name = "cause")
    private Cause cause;

    @Column(name = "Activity_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityCreationData;

    @Column(name = "activityCreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityCreationDate = new Date();

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Fundraiser getCreator() {
        return creator;
    }

    public void setCreator(Fundraiser creator) {
        this.creator = creator;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getActivityCreationData() {
        return activityCreationData;
    }

    public void setActivityCreationData(Date activityCreationData) {
        this.activityCreationData = activityCreationData;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Activity other = (Activity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Date getActivityCreationDate() {
        return activityCreationDate;
    }

    public void setActivityCreationDate(Date activityCreationDate) {
        this.activityCreationDate = activityCreationDate;
    }

}
