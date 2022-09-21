package org.company.business.service;

import java.util.List;
import org.company.business.domain.User;
import org.company.business.domain.UserRole;

/**
 *
 * @author Edward Zengeni
 */
public interface UserService extends GenericService<User> {

    public User findByUserName(String name);

    public List<UserRole> getRoles();
    
    public User getUserByUserName(String username);
    
    String getCurrentUsername();
    
    User getCurrentUser();
    
    public User changePassword(User t);
}
