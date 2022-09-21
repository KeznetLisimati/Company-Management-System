/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service;

import java.util.List;
import org.company.business.domain.District;
import org.company.business.domain.Province;

/**
 *
 * @author Edward Zengeni
 */
public interface DistrictService extends GenericService<District> {

    public List<District> getDistrictByProvince(Province province);

    public List<District> getActive();
}