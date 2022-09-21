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
import org.company.business.domain.CompanyAddress;
import org.company.business.domain.AddressType;
import org.company.business.repo.CompanyAddressRepo;
import org.company.business.service.CompanyAddressService;
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
public class CompanyAddressServiceImpl implements CompanyAddressService {

    @Resource
    private CompanyAddressRepo companyAddressRepo;
    @Resource
    private UserService userService;

    @Override
    public List<CompanyAddress> getAll() {
        return companyAddressRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public CompanyAddress get(String id) {
        return companyAddressRepo.findOne(id);
    }

    @Override
    public void delete(CompanyAddress t) {
        if (t.getCompanyAddressId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        companyAddressRepo.delete(t);
    }

    @Override
    public List<CompanyAddress> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyAddress save(CompanyAddress t) {
        if (t.getCompanyAddressId().isEmpty()) {
            t.setCompanyAddressId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return companyAddressRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return companyAddressRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(CompanyAddress current, CompanyAddress old) {
        if (!current.getCompanyAddressId().isEmpty()) {
            /**
             * @param current is in existence
             */
            if (!((current.getAddressDetail().equals(old.getAddressDetail())) && (current.getAddressType().equals(old.getAddressType())))) {
                if (getByAddressTypeAndAddressDetail(current.getCompany(), current.getAddressType(), current.getAddressDetail()) != null) {
                    return true;
                }
            }
        } else if (current.getCompanyAddressId().isEmpty()) {
            if (getByAddressTypeAndAddressDetail(current.getCompany(), current.getAddressType(), current.getAddressDetail()) != null) {
                return true;
            }
        }
        return false;

    }

    @Override
    public List<CompanyAddress> getByCompany(Company company) {
        return companyAddressRepo.findByCompanyAndActive(company, Boolean.TRUE);
    }

    @Override
    public CompanyAddress getByAddressTypeAndAddressDetail(Company company, AddressType addressType, String addressDetail) {
        return companyAddressRepo.findByCompanyAndTypeAndItem(company, addressType, addressDetail);
    }

}
