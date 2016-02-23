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
public class CauseFundRaisingResult implements Serializable{
    
    private double causeRaising;
    private double totalCausesRaising;

    public CauseFundRaisingResult() {
    }

    public CauseFundRaisingResult(double causeRaising, double totalCausesRaising) {
        this.causeRaising = causeRaising;
        this.totalCausesRaising = totalCausesRaising;
    }
    
    
    
    

    public double getCauseRaising() {
        return causeRaising;
    }

    public void setCauseRaising(double causeRaising) {
        this.causeRaising = causeRaising;
    }

    public double getTotalCausesRaising() {
        return totalCausesRaising;
    }

    public void setTotalCausesRaising(double totalCausesRaising) {
        this.totalCausesRaising = totalCausesRaising;
    }
    
      public String increaseRatio() {

        if ( totalCausesRaising != 0 && causeRaising != 0) {

            double ratio = (causeRaising / totalCausesRaising)*100;
            return new DecimalFormat("#.##").format(ratio) + " %";
        }
        return " 0 %";
    }
    
    
}
