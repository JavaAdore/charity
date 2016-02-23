/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webapps2015.dao;

import com.webapps2015.entity.Cause;
import com.webapps2015.entity.Charity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dar27
 */
@Local
public interface CauseDAO {
      Cause addOrUpdateCause(Cause cause);
    List <Cause> getCharityCauses(Charity charity, boolean activeOnly);

    public Cause getCauseById(Long id);

    public Double getCharityTotalCausesFundraising(Charity charity);

    public Double getCauseFunding(Cause cause);
}
