/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.webservice;

import com.webapps2015.entity.Donation;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.util.SingletonEJB;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author dar27
 */
@Path("/services")
public class WebServiceController {

    SingletonEJB singletonEJB;

    public WebServiceController() throws NamingException {
        Context context = new InitialContext();
        System.out.println(context.getEnvironment());
        singletonEJB = (SingletonEJB) context.lookup("java:global/webapps2015/singletonEJB");
    }

    @GET
    @Path("/test")
    public String test() {
        return "Congratulations";
    }

    @POST
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Fundraiser test1(Fundraiser testEntity) {
        return new Fundraiser();
    }

    @GET
    @Path("/charity/{charityUniqueName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String alreadyRegisteredCharity(@PathParam("charityUniqueName") String charityUniqueName) {

        return singletonEJB.isRegisteredCharity(charityUniqueName) ? "true" : "false";
    }

    @POST
    @Path("/charity/donate")
    @Consumes(MediaType.APPLICATION_JSON) 
    public String registerDonation(Donation donation) {
        donation.setDonationDate(new Date());
        singletonEJB.registerNewDonation(donation);
        return "true";
    }

}
