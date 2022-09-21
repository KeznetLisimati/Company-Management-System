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
import org.apache.commons.validator.EmailValidator;
import org.company.business.domain.User;
import org.company.business.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.company.business.util.dto.ChangePasswordDTO;
import org.company.business.util.dto.ChangePrivilegesDTO;
import org.company.business.util.dto.UserLevelDTO;
import org.company.business.util.dto.UserLevel;

/**
 *
 * @author Edward Zengeni
 */
@Component
public class UserValidator implements Validator {

    @Resource
    private UserService userService;

    @Override
    public boolean supports(Class<?> type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmpty(errors, "firstName", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "username", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "roles", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "field.empty");

        if (!EmailValidator.getInstance().isValid(user.getUsername())) {
            errors.rejectValue("username", "email.format");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password.confirm");
        }

    }

    public void validateChangepassword(Object o, Errors errors) throws Exception {
        ChangePasswordDTO changePassword = (ChangePasswordDTO) o;
        User currentUser = changePassword.getUser();
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "oldPassword", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "item.required");

        if (!changePassword.getNewPassword().equals("") || changePassword.getNewPassword().isEmpty()) {
            if (!passwordMeetsRequirements(changePassword.getNewPassword())) {
                errors.rejectValue("newPassword", "password.notmeetingrequirements");
            }
        }
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password.confirm");
        }

    }

    public void manageChangepassword(Object o, Errors errors) {
        ChangePasswordDTO changePassword = (ChangePasswordDTO) o;
        User currentUser = changePassword.getUser();
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "item.required");
        if (!changePassword.getNewPassword().equals("") || changePassword.getNewPassword().isEmpty()) {
            if (!passwordMeetsRequirements(changePassword.getNewPassword())) {
                errors.rejectValue("newPassword", "password.notmeetingrequirements");
            }
        }
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password.confirm");
        }

    }

     public void validateUserLevel(Object o, Errors errors) {
        UserLevelDTO dto = (UserLevelDTO) o;
        if(dto.getUser().getUserLevel() == null){
            errors.rejectValue("user.userLevel", "field.required");
        }
        if(dto.getUser().getUserLevel() != null){
            if(dto.getUser().getUserLevel().equals(UserLevel.PROVINCE)){
                if(dto.getUser().getProvince() == null){
                    errors.rejectValue("user.province", "field.required");
                }
            }
            if(dto.getUser().getUserLevel().equals(UserLevel.DISTRICT)){
                if(dto.getUser().getProvince() == null){
                    errors.rejectValue("user.province", "field.required");
                }
                if(dto.getUser().getDistrict()== null){
                    errors.rejectValue("user.district", "field.required");
                }
            }
        }
    }
    
    public void validateChangeprivileges(Object o, Errors errors) {
        ChangePrivilegesDTO dto = (ChangePrivilegesDTO) o;
        if (dto.getUser().getRoles().isEmpty()) {
            errors.rejectValue("user.userRoles", "field.required");
        }
    }

    private boolean passwordMeetsRequirements(String password) {

        if (password == null || password.trim().equals("")) {
            return false;
        }

        PasswordValidator passwordValidator = new PasswordValidator();
        return passwordValidator.validate(password);

    }
}
