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
import org.company.business.domain.Station;
import org.company.business.service.StationService;
import org.company.business.repo.StationRepo;
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
@Repository("stationService")
public class StationServiceImpl implements StationService{

    @Resource
    private StationRepo stationRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Station> getAll() {
        return stationRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public Station get(String id) {
        return stationRepo.findOne(id);
    }

    @Override
    public void delete(Station t) {
        if (t.getStationId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        stationRepo.save(t);
    }

    @Override
    public List<Station> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Station save(Station t) {
         if (t.getStationId().isEmpty()) {
            t.setStationId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return stationRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return stationRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Station current, Station old) {
        
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (stationRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (stationRepo.findByName(current.getName()) != null) {
                return true;
            }
        
        }
        
        return false;
  
    }

    @Override
    public List<Station> getByDistrict(District district) {
        return stationRepo.getOptByDistrict(district);
    }

}
