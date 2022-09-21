/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.District;
import org.company.business.domain.Province;
import org.company.business.repo.DistrictRepo;
import org.company.business.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {

    @Resource
    private DistrictRepo districtRepo;
    @Resource
    private UserService userService;

    @Override
    public List<District> getAll() {
        return districtRepo.findByActiveOrderByNameAsc(Boolean.TRUE);

    }

    @Override
    public District get(String id) {

        return districtRepo.findOne(id);
    }

    @Override
    public void delete(District t) {
        if (t.getDistrictId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        districtRepo.save(t);
    }

    @Override
    public List<District> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public District save(District t) {
        if (t.getDistrictId().isEmpty()) {
            t.setDistrictId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return districtRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return districtRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(District current, District old) {
          if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (districtRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (districtRepo.findByName(current.getName()) != null) {
                return true;
            }

        }

        return false;
    }
    
    @Override
    public List<District> getDistrictByProvince(Province province) {
        return districtRepo.getOptByProvince(province);
    }

    @Override
    public List<District> getActive() {
        return districtRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

}
