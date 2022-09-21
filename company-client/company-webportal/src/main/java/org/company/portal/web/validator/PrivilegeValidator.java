package org.company.portal.web.validator;

import org.company.business.domain.Privilege;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PrivilegeValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Privilege.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Privilege item = (Privilege) o;    
        ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "description.required");
    }
}