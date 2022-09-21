/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import java.util.List;
import org.company.business.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyRepo extends AbstractRepo<Company, String> {

    @Override
    public List<Company> findAll();

    public List<Company> findByActiveOrderByNameAsc(Boolean active);

    @Query("from Company e  where  e.active=:active and (e.name Like :name%  or e.companyNo Like :name%) order by e.name ASC")
    public List<Company> findByName(@Param("name") String name, @Param("active") Boolean active);

}
