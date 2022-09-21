/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service;

import org.company.business.domain.Company;
import org.company.business.domain.CompanyBankDetail;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyBankDetailService extends AbstractCompanyAttrService<CompanyBankDetail> {

    public CompanyBankDetail getByCompanyAndBankAndDetail(Company company, String bank, String accountNumber);
}