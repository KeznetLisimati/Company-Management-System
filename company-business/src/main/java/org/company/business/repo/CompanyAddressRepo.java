/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import org.company.business.domain.Company;
import org.company.business.domain.CompanyAddress;
import org.company.business.domain.AddressType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyAddressRepo extends AbstractCompanyAttrRepo<CompanyAddress, String> {

    @Query("from CompanyAddress pc left join fetch pc.addressType left join fetch pc.company where pc.company=:company and pc.addressDetail=:addressDetail")
    public CompanyAddress findByCompanyAndItem(@Param("company") Company company, @Param("addressDetail") String addressDetail);

    @Query("from CompanyAddress pc left join fetch pc.addressType left join fetch pc.company where pc.company=:company and pc.addressType=:addressType and pc.addressDetail=:addressDetail")
    public CompanyAddress findByCompanyAndTypeAndItem(@Param("company") Company company, @Param("addressType") AddressType addressType, @Param("addressDetail") String addressDetail);
}
