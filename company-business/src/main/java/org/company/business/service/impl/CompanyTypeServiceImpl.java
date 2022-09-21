/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.CompanyType;
import org.company.business.service.CompanyTypeService;
import org.company.business.repo.CompanyTypeRepo;
import org.company.business.service.UserService;
import org.company.business.utils.UUIDGen;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edward Zengeni
 */
@Transactional(readOnly = true)
@Repository
public class CompanyTypeServiceImpl implements CompanyTypeService{

    @Resource
    private CompanyTypeRepo companyTypeRepo;
    @Resource
    private UserService userService;

    @Override
    public List<CompanyType> getAll() {
        return companyTypeRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public CompanyType get(String id) {
        return companyTypeRepo.findOne(id);
    }

    @Override
    public void delete(CompanyType t) {
        if (t.getCompanyTypeId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        companyTypeRepo.save(t);
    }

    @Override
    public List<CompanyType> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyType save(CompanyType t) {
        if (t.getCompanyTypeId().isEmpty()) {
            t.setCompanyTypeId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return companyTypeRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return companyTypeRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(CompanyType current, CompanyType old) {
        
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (companyTypeRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (companyTypeRepo.findByName(current.getName()) != null) {
                return true;
            }
        
        }
        
        return false;
  
    }

}
