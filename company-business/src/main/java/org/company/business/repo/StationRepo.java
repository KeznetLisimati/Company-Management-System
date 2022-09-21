/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import java.util.List;
import org.company.business.domain.District;
import org.company.business.domain.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface StationRepo extends AbstractNameDescRepo<Station, String> {

    @Query("from Station d left join fetch d.district where d.district=:district Order By d.name ASC")
    public List<Station> getOptByDistrict(@Param("district") District district);

    @Query("from Station s left join fetch s.district where s.active=:active order by s.name asc")
    @Override
    public List<Station> findByActiveOrderByNameAsc(@Param("active") Boolean active);
}
