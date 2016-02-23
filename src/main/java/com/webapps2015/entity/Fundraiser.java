 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author dar27
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Fundraiser extends Person implements Serializable {

    public final static Double INITIAL_FUNDARISER = 10000d;
    @Column(name = "amount_of_credit")
    private Double credit = INITIAL_FUNDARISER;

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

}
