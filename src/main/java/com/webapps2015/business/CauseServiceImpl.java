/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.business;

import com.webapps2015.dao.CauseDAO;
import com.webapps2015.dto.CauseFundRaisingResult;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import java.util.List; 
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author dar27 
 */
@Stateful
public class CauseServiceImpl implements CauseService{

    @EJB
    CauseDAO causeDAO;
    
    @Override
    public Cause addOrUpdateCause(Cause cause) {
       return causeDAO.addOrUpdateCause(cause);
    }

    @Override
    public List<Cause> getCharityCauses(Charity charity) {
        return causeDAO.getCharityCauses(charity,false);
    }

    @Override
    public List<Cause> getActiveCharityCauses(Charity charity) {
        return causeDAO.getCharityCauses(charity,true);
    }

    @Override
    public Cause getCauseById(Long id) {
        return causeDAO.getCauseById(id);
    }

    @Override
    public CauseFundRaisingResult getCauseFundringResult(Cause cause) {
       Double total =  causeDAO.getCharityTotalCausesFundraising(cause.getCharity());
       Double causeFundrising =  causeDAO.getCauseFunding(cause);
       return new CauseFundRaisingResult(causeFundrising, total);
    }
    
    
    
}
