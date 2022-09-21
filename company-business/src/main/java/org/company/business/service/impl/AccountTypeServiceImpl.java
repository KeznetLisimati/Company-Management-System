/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.AccountType;
import org.company.business.service.AccountTypeService;
import org.company.business.repo.AccountTypeRepo;
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
public class AccountTypeServiceImpl implements AccountTypeService{

    @Resource
    private AccountTypeRepo accountTypeRepo;
    @Resource
    private UserService userService;

    @Override
    public List<AccountType> getAll() {
        return accountTypeRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public AccountType get(String id) {
        return accountTypeRepo.findOne(id);
    }

    @Override
    public void delete(AccountType t) {
        if (t.getAccountTypeId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        accountTypeRepo.save(t);
    }

    @Override
    public List<AccountType> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountType save(AccountType t) {
        if (t.getAccountTypeId().isEmpty()) {
            t.setAccountTypeId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return accountTypeRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return accountTypeRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(AccountType current, AccountType old) {
        
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (accountTypeRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (accountTypeRepo.findByName(current.getName()) != null) {
                return true;
            }
        
        }
        
        return false;
  
    }

}
