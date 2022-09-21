/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.Company;
import org.company.business.domain.CompanyBankDetail;
import org.company.business.repo.CompanyBankDetailRepo;
import org.company.business.service.CompanyBankDetailService;
import org.company.business.service.UserService;
import org.company.business.utils.UUIDGen;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edward Zengeni
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class CompanyBankDetailServiceImpl implements CompanyBankDetailService {

    @Resource
    private CompanyBankDetailRepo companyBankDetailRepo;
    @Resource
    private UserService userService;

    @Override
    public List<CompanyBankDetail> getAll() {
        return companyBankDetailRepo.findByActive(Boolean.TRUE);

    }

    @Override
    public CompanyBankDetail get(String id) {

        return companyBankDetailRepo.findOne(id);
    }

    @Override
    public void delete(CompanyBankDetail t) {
        if (t.getCompanyBankDetailId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        companyBankDetailRepo.delete(t);
    }

    @Override
    public List<CompanyBankDetail> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyBankDetail save(CompanyBankDetail t) {
        if (t.getCompanyBankDetailId().isEmpty()) {
            t.setCompanyBankDetailId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return companyBankDetailRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return companyBankDetailRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(CompanyBankDetail current, CompanyBankDetail old) {
        if (!current.getCompanyBankDetailId().isEmpty()) {
            /**
             * @param current is in existence
             */
            if (!((current.getBank().equals(old.getBank())) && (current.getAccountNumber().equals(old.getAccountNumber())))) {
                if (getByCompanyAndBankAndDetail(current.getCompany(), current.getBank(), current.getAccountNumber()) != null) {
                    return true;
                }
            }
        } else if (current.getCompanyBankDetailId().isEmpty()) {
            if (getByCompanyAndBankAndDetail(current.getCompany(), current.getBank(), current.getAccountNumber()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CompanyBankDetail> getByCompany(Company company) {
        return companyBankDetailRepo.findByCompanyAndActive(company, Boolean.TRUE);
    }

    @Override
    public CompanyBankDetail getByCompanyAndBankAndDetail(Company company, String bank, String accountNumber) {
        return companyBankDetailRepo.findByCompanyAndBankAndDetail(company, bank, accountNumber);
    }

}
