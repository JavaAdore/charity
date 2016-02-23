/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.dto;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 *
 * @author dar27
 */
public class ActivityFundraisingResult implements Serializable {

    private String activityName;
    private double raisByActivity;
    private double totalFunds;

    public ActivityFundraisingResult() {
    }

    public ActivityFundraisingResult(String activityName, double raisByActivity, double totalFunds) {
        this.activityName = activityName;
        this.raisByActivity = raisByActivity;
        this.totalFunds = totalFunds;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public double getRaisByActivity() {
        return raisByActivity;
    }

    public void setRaisByActivity(double raisByActivity) {
        this.raisByActivity = raisByActivity;
    }

    public double getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(double totalFunds) {
        this.totalFunds = totalFunds;
    }

    public String increaseRatio() {

        if (totalFunds != 0 && raisByActivity != 0) {

            double ratio = (raisByActivity / totalFunds)*100;
            return new DecimalFormat("#.##").format(ratio) + " %";
        }
        return " 0 %";
    }

}
