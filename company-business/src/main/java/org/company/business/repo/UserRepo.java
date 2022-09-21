/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.repo;

import org.company.business.domain.User;

/**
 *
 * @author Edward Zengeni
 */
public interface UserRepo extends AbstractNameDescRepo<User, String> {
    
    public User findByUsername(String name);
}
