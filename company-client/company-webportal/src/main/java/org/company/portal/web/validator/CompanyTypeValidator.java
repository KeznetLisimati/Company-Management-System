/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import org.company.business.domain.CompanyType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CompanyTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(CompanyType.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CompanyType item = (CompanyType) o;    
        ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "description.required");
    }
}