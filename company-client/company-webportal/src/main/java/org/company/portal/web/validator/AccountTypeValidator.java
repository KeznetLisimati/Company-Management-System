/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import org.company.business.domain.AccountType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(AccountType.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountType item = (AccountType) o;    
        ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "description.required");
    }
}