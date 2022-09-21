/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.StationCategory;
import org.company.business.service.StationCategoryService;
import org.company.business.repo.StationCategoryRepo;
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
public class StationCategoryServiceImpl implements StationCategoryService{

    @Resource
    private StationCategoryRepo stationCategoryRepo;
    @Resource
    private UserService userService;

    @Override
    public List<StationCategory> getAll() {
        return stationCategoryRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public StationCategory get(String id) {
        return stationCategoryRepo.findOne(id);
    }

    @Override
    public void delete(StationCategory t) {
        if (t.getStationCategoryId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        stationCategoryRepo.save(t);
    }

    @Override
    public List<StationCategory> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StationCategory save(StationCategory t) {
        if (t.getStationCategoryId().isEmpty()) {
            t.setStationCategoryId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return stationCategoryRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return stationCategoryRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(StationCategory current, StationCategory old) {
        
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (stationCategoryRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (stationCategoryRepo.findByName(current.getName()) != null) {
                return true;
            }
        
        }
        
        return false;
  
    }

}
