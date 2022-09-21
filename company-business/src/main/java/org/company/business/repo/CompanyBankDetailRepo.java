/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import org.company.business.domain.Company;
import org.company.business.domain.CompanyBankDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyBankDetailRepo extends AbstractCompanyAttrRepo<CompanyBankDetail, String> {

    @Query("from CompanyBankDetail eb left join fetch eb.company left join fetch eb.accountType where eb.company=:company and eb.bank=:bank and eb.accountNumber=:accountNumber")
    public CompanyBankDetail findByCompanyAndBankAndDetail(@Param("company") Company company, @Param("bank") String bank, @Param("accountNumber") String accountNumber);
}
