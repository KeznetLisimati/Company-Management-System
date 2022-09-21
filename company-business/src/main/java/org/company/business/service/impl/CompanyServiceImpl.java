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
import org.company.business.repo.CompanyRepo;
import org.company.business.service.CompanyService;
import org.company.business.service.UserService;
import org.company.business.util.dto.SearchByNameDTO;
import org.company.business.utils.UUIDGen;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edward Zengeni
 */
@Transactional(readOnly = true)
@Repository("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyRepo companyRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Company> getAll() {
        return companyRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public Company get(String id) {
        return companyRepo.findOne(id);
    }

    @Override
    public void delete(Company t) {
        if (t.getCompanyId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        companyRepo.save(t);

    }

    @Override
    public List<Company> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company save(Company t) {
        if (t.getCompanyId().isEmpty()) {
            t.setCompanyId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return companyRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return companyRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Company current, Company old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> findByActiveOrderByName(Boolean active) {
        return companyRepo.findByActiveOrderByNameAsc(active);
    }

    @Override
    public List<Company> search(SearchByNameDTO dto) {
        String[] exp = dto.getName();
        if (exp == null) {
            throw new IllegalArgumentException("Provide parameter for search");
        } else if (exp.length == 1) {
            return companyRepo.findByName(exp[0], Boolean.TRUE);
        }
        return companyRepo.findByName(exp[0], Boolean.TRUE);

    }

}
