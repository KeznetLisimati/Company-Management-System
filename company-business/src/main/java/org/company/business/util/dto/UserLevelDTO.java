package org.company.business.util.dto;


import org.company.business.domain.User;

/*
 * Copyright 2015 Edward Zengeni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 *
 * @author Edward Zengeni
 */
public class UserLevelDTO {
    
    private User user;

    public UserLevelDTO(User user) {
        this.user = user;
    }

    public UserLevelDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInstance(UserLevelDTO dto) {
        User u = dto.getUser();
        u = sanitizeUser(u);
        return u;
    }
    
    private User sanitizeUser(User u){
        if(u.getDistrict() != null){
            u.setProvince(null);
            u.setStation(null);
            return u;
        }
        return u;
    }

    @Override
    public String toString() {
        return "UserLevelDTO{" + "user=" + user.getUserLevel().getName() + '}';
    }
    
    
}
