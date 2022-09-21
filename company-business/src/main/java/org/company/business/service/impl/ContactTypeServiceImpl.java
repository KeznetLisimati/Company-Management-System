/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.ContactType;
import org.company.business.service.ContactTypeService;
import org.company.business.repo.ContactTypeRepo;
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
public class ContactTypeServiceImpl implements ContactTypeService{

    @Resource
    private ContactTypeRepo contactTypeRepo;
    @Resource
    private UserService userService;

    @Override
    public List<ContactType> getAll() {
        return contactTypeRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public ContactType get(String id) {
        return contactTypeRepo.findOne(id);
    }

    @Override
    public void delete(ContactType t) {
        if (t.getContactTypeId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        contactTypeRepo.save(t);
    }

    @Override
    public List<ContactType> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContactType save(ContactType t) {
        if (t.getContactTypeId().isEmpty()) {
            t.setContactTypeId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return contactTypeRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return contactTypeRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(ContactType current, ContactType old) {
        
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (contactTypeRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (contactTypeRepo.findByName(current.getName()) != null) {
                return true;
            }
        
        }
        
        return false;
  
    }

}
