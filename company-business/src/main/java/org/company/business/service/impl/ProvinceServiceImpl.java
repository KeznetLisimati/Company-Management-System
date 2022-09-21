/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.Province;
import org.company.business.service.ProvinceService;
import org.company.business.repo.ProvinceRepo;
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
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceRepo provinceRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Province> getAll() {
        return provinceRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public Province get(String id) {
        return provinceRepo.findOne(id);
    }

    @Override
    public void delete(Province t) {
        if (t.getProvinceId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        provinceRepo.save(t);
    }

    @Override
    public List<Province> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Province save(Province t) {
        if (t.getProvinceId() == null || t.getProvinceId().trim().isEmpty()) {
            t.setProvinceId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return provinceRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return provinceRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Province current, Province old) {

        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (provinceRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (provinceRepo.findByName(current.getName()) != null) {
                return true;
            }

        }

        return false;

    }

}
