/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import org.company.business.domain.Company;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Edward Zengeni
 */
@Component
public class CompanyValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Company.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
//        ValidationUtils.rejectIfEmpty(errors, "country", "item.required");
//        ValidationUtils.rejectIfEmpty(errors, "industry", "item.required");
//        ValidationUtils.rejectIfEmpty(errors, "companyStatus", "item.required");

    }
}
