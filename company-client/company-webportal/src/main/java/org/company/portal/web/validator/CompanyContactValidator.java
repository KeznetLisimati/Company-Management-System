/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import javax.annotation.Resource;
import org.company.business.domain.CompanyContact;
import org.company.business.domain.ContactType;
import org.company.business.service.CompanyContactService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Edward Zengeni
 *
 */
@Component
public class CompanyContactValidator implements Validator {

    @Resource
    private CompanyContactService personContactService;

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(CompanyContact.class);
    }

    public void validateAll(Object o, Errors errors) {
        validateEmployeeContact(o, errors);
    }

    public void validateEmployeeContact(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "contactDetail", "contactDetail.required");
        CompanyContact current = (CompanyContact) o;
        CompanyContact old = null;
        if (current.getCompany() != null && !current.getContactDetail().equals("") && current.getContactDetail() != null) {
            if (current.getCompanyContactId() != null) {
                old = personContactService.get(current.getCompanyContactId());
            }
            if (personContactService.checkDuplicate(current, old)) {
                errors.rejectValue("contactDetail", "item.duplicate");
            }
        }
        if (current.getContactType() == null) {
            ValidationUtils.rejectIfEmpty(errors, "contactType", "contactType.required");
        }
        if (current.getContactType() != null) {
            ContactType contactType = current.getContactType();
            String contactDetail = current.getContactDetail();
            if (contactTypeContains(contactType, "number") && (!contactTypeContains(contactType, "cell"))) {
                if (current.getContactDetail() != null
                        && current.getContactDetail().replace(" ", "").matches(".*\\D+.*")) {
                    errors.rejectValue("contactDetail", "contactDetail.invalidNumber");
                }
            }
            if (contactTypeContains(contactType, "cell")) {
                if (contactDetail != null) {
                    if (!contactDetail.matches("^[0-9]{9,}||[+0-9]{13,}+$")) {
                        errors.rejectValue("contactDetail", "contactDetail.invalidCellNumber");
                    }
                }
            }
            if (contactTypeContains(contactType, "email")) {
                if (contactDetail != null) {
                    if (!contactDetail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        errors.rejectValue("contactDetail", "contactDetail.invalidEmailAddress");
                    }
                }
            }
        }

    }

    @Override
    public void validate(Object o, Errors errors) {
    }

    private boolean contactTypeContains(ContactType contactType, String content) {
        return contactType != null
                && contactType.getName() != null
                && contactType.getName().trim().toLowerCase().contains(content);
    }
}
