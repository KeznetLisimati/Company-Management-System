/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service;

import org.company.business.domain.Company;
import org.company.business.domain.CompanyContact;
import org.company.business.domain.ContactType;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyContactService extends AbstractCompanyAttrService<CompanyContact> {

    public CompanyContact getByContactTypeAndContactDetail(Company company, ContactType contactType, String contactDetail);
}
