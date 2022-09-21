/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.Privilege;
import org.company.business.repo.PrivilegeRepo;
import org.company.business.service.PrivilegeService;
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
public class PrivilegeServiceImpl implements PrivilegeService {

    @Resource
    private PrivilegeRepo privilegeRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Privilege> getAll() {
        return privilegeRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public Privilege get(String id) {
        return privilegeRepo.findOne(id);
    }

    @Override
    public void delete(Privilege t) {
        if (t.getPrivilegeId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        privilegeRepo.save(t);
    }

    @Override
    public List<Privilege> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Privilege save(Privilege t) {
        if (t.getPrivilegeId().isEmpty()) {
            t.setPrivilegeId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return privilegeRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return privilegeRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Privilege current, Privilege old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (privilegeRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (privilegeRepo.findByName(current.getName()) != null) {
                return true;
            }

        }

        return false;
    }

}
