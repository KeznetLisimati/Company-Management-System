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
package org.company.business.util.dto;

import org.company.business.domain.User;

/**
 *
 * @author Edward Zengeni
 */
public class ChangePrivilegesDTO {

    private User user;

    public ChangePrivilegesDTO(User user) {
        this.user = user;
    }

    public ChangePrivilegesDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInstance(ChangePrivilegesDTO dto) {
        User u = dto.getUser();
        return u;
    }

}
