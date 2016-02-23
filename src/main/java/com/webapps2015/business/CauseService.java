/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.business;

import com.webapps2015.dto.CauseFundRaisingResult;
import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import java.util.List;
import javax.ejb.Local; 

/**
 *
 * @author dar27   
 */
@Local
public interface CauseService {

    Cause addOrUpdateCause(Cause cause);

    List<Cause> getCharityCauses(Charity charity);

    List<Cause> getActiveCharityCauses(Charity charity);

    public Cause getCauseById(Long id);

    public CauseFundRaisingResult getCauseFundringResult(Cause cause);

}
