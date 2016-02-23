/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.business;

import com.webapps2015.dao.ActivityDAO;
import com.webapps2015.dao.DonationDAO;
import com.webapps2015.dao.SystemUserDAO;
import com.webapps2015.dto.ActivityFundraisingResult;
import com.webapps2015.entity.Activity;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import com.webapps2015.entity.Donation;
import com.webapps2015.entity.Fundraiser;
import com.webapps2015.entity.SystemUser;
import com.webapps2015.exception.WebApps2015Exception;
import com.webapps2015.util.Constant;
import com.webapps2015.util.Utils;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author dar27
 */
@Stateless
public class ActivityServiceImpl implements ActivityService {

    @EJB
    ActivityDAO acivityDAO;
    @EJB
    SystemUserDAO systemUserDAO;

    @EJB
    DonationDAO donationDAO;

    @Override
    public Activity getActivity(Fundraiser creator, Cause cause) {
        return acivityDAO.getActivity(creator, cause);
    }

    @Override
    public Activity addActivity(Activity activity) {
        return acivityDAO.addActivity(activity);
    }

    @Override
    public List<Activity> loadAllActivitiesCreatedBy(Fundraiser curentFundraiser) {
        return acivityDAO.loadAllActivitiesCreatedBy(curentFundraiser);
    }

    @Override
    public List<Activity> getAllActivitiesExceptOfWhichCreatedBy(Fundraiser fundraiser) {
        return acivityDAO.getAllActivitiesExceptOfWhichCreatedBy(fundraiser);
    }

    @Override
    public String donate(Donation donation) throws WebApps2015Exception {
        Fundraiser frishlyCheckedFundraiser = doCreditValidation(donation);
        doAmountValidateion(donation);
        deductCreditAndUpdateFunderizer(frishlyCheckedFundraiser, donation);
        increaseCharityCredit(donation.getActivity().getCause().getCharity(), donation.getAmout());
        donation.setDonationDate(new Date());
        int numberOfPrevoiusDonation = getActivityDonations(donation.getFundraiser(), donation.getActivity()).size();
        donationDAO.AddDonation(donation);
        String result = (String) Utils.consumePostWebService(Constant.getApplicationWebServicesPath() + "charity/donate", donation, String.class);
        if (result.equalsIgnoreCase("true")) {
            if (numberOfPrevoiusDonation > 0) {
                String spth = Utils.getSPTH(++numberOfPrevoiusDonation);
                return String.format(" Thanks for %s %s donation for %s activity", numberOfPrevoiusDonation, spth, donation.getActivity().getActivityName());
            }
            return "Thanks for your donation ";
        } else {
            throw new WebApps2015Exception("Error with registering your donation - please try again later");
        }
    }

    private List<Donation> getActivityDonations(SystemUser fundraiser, Activity activity) {
        return donationDAO.getActivityDonations(fundraiser, activity);
    }

    private Fundraiser doCreditValidation(Donation donation) throws WebApps2015Exception {
        Fundraiser fundraiser = systemUserDAO.getUserById(donation.getFundraiser().getId());

        if (donation.getAmout() > fundraiser.getCredit()) {
            throw new WebApps2015Exception(String.format("You dont have enough credit to donate by (%s)$ ", donation.getAmout()));

        }
        return fundraiser;
    }

    private void doAmountValidateion(Donation donation) throws WebApps2015Exception {
        if (donation.getAmout() <0) {
            throw new WebApps2015Exception("Cannot donate a negative amount ! ");
        }
         if (donation.getAmout() == 0 ) {
            throw new WebApps2015Exception("Zero is not valid amount to donate with");
        }
        if (donation.getAmout() > Constant.MAX_ALLOWED_BATCH_DONATION_AMOUNT) {
            throw new WebApps2015Exception(String.format("You may only donate with $ %s at this time  ", Constant.MAX_ALLOWED_BATCH_DONATION_AMOUNT));
        }
    }

    private void deductCreditAndUpdateFunderizer(Fundraiser freshlyCheckedFundraiser, Donation donation) {
        freshlyCheckedFundraiser.setCredit(freshlyCheckedFundraiser.getCredit() - donation.getAmout());
        systemUserDAO.addOrUpdateFundriser(freshlyCheckedFundraiser);
    }

    @Override
    public Activity getActivityById(Long id) {
        return acivityDAO.getactivityById(id);
    }

    @Override
    public ActivityFundraisingResult getActivityFundraisingResult(Activity activity) {
       
      Double increase =   systemUserDAO.getTotalAmountOfActivity(activity);
      Double totalDonationsForCause = systemUserDAO.getTotalAmountOfCause(activity.getCause());
      
      return new ActivityFundraisingResult(activity.getActivityName(), increase, totalDonationsForCause);
      
    }


  
    private synchronized void increaseCharityCredit(Charity charity, Double amount) {
        charity = systemUserDAO.getCharityById(charity.getId());
        double charityCredit = charity.getCredit()!=null?charity.getCredit():0L;
        charityCredit+=amount;
        charity.setCredit(charityCredit);
        systemUserDAO.addCharity(charity);
    }

}
