/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.Employee;
import org.company.business.service.EmployeeService;
import org.company.business.repo.EmployeeRepo;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeRepo employeeRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Employee> getAll() {
        return employeeRepo.findByActiveOrderByNameAsc(Boolean.TRUE);
    }

    @Override
    public Employee get(String id) {
        return employeeRepo.findOne(id);
    }

    @Override
    public void delete(Employee t) {
        if (t.getEmployeeId().isEmpty()) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        employeeRepo.save(t);
    }

    @Override
    public List<Employee> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee save(Employee t) {
        if (t.getEmployeeId() == null || t.getEmployeeId().trim().isEmpty()) {
            t.setEmployeeId(UUIDGen.generateUUID());
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return employeeRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return employeeRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Employee current, Employee old) {

        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (employeeRepo.findByName(current.getName()) != null) {
                    return true;
                }
            }
        } else if (current.getId() == null) {

            if (employeeRepo.findByName(current.getName()) != null) {
                return true;
            }

        }

        return false;

    }

}
