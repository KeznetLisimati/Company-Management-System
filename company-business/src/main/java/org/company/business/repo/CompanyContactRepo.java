/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import org.company.business.domain.Company;
import org.company.business.domain.CompanyContact;
import org.company.business.domain.ContactType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyContactRepo extends AbstractCompanyAttrRepo<CompanyContact, String> {

    @Query("from CompanyContact pc left join fetch pc.contactType left join fetch pc.company where pc.company=:company and pc.contactDetail=:contactDetail")
    public CompanyContact findByCompanyAndItem(@Param("company") Company company, @Param("contactDetail") String contactDetail);

    @Query("from CompanyContact pc left join fetch pc.contactType left join fetch pc.company where pc.company=:company and pc.contactType=:contactType and pc.contactDetail=:contactDetail")
    public CompanyContact findByCompanyAndTypeAndItem(@Param("company") Company company, @Param("contactType") ContactType contactType, @Param("contactDetail") String contactDetail);
}
