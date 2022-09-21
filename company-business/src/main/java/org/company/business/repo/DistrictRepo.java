/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import java.util.List;
import org.company.business.domain.District;
import org.company.business.domain.Province;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface DistrictRepo extends AbstractNameDescRepo<District, String> {

    @Query("from District d left join fetch d.province where d.province=:province Order By d.name ASC")
    public List<District> getOptByProvince(@Param("province") Province province);
}
