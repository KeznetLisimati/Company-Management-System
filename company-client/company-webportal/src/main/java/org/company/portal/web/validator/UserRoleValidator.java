/*
 * Copyright 2014 Edward Zengeni.
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
package org.company.portal.web.validator;

import javax.annotation.Resource;
import org.company.business.domain.UserRole;
import org.company.business.service.UserRoleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Edward Zengeni
 */
@Component
public class UserRoleValidator implements Validator {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public boolean supports(Class<?> type) {
        return UserRole.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "field.empty");
        /**
         * @param name province name must always be unique
         */
        UserRole current = (UserRole) o;
        UserRole old = null;
        if(current.getId() != null){
            //old = userRoleService.get(current.getId());
        }
        if(userRoleService.checkDuplicate(current, old)){
            errors.rejectValue("name", "item.duplicate");
        }
    }
}