/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.AddressType;
import org.company.business.service.AddressTypeService;
import org.company.business.repo.AddressTypeRepo;
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
public class AddressTypeServiceImpl implements AddressTypeService{

    @Resource
    private AddressTypeRepo addressTypeRepo;
    @Resource
    private UserService userService;

    @Override
    public List<AddressType> getAll() {
        return addressTypeRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public AddressType get(String id) {
        return addressTypeRepo.findOne(id);
    }

    @Override
    public void delete(AddressType t) {
        if (t.getAddressTypeId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        addressTypeRepo.save(t);
    }

    @Override
    public List<AddressType> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AddressType save(AddressType t) {
        if (t.getAddressTypeId().isEmpty()) {
            t.setAddressTypeId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return addressTypeRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return addressTypeRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(AddressType current, AddressType old) {
        
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (addressTypeRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (addressTypeRepo.findByName(current.getName()) != null) {
                return true;
            }
        
        }
        
        return false;
  
    }

}
