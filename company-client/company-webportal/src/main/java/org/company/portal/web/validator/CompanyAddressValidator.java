/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import javax.annotation.Resource;
import org.company.business.domain.CompanyAddress;
import org.company.business.domain.AddressType;
import org.company.business.service.CompanyAddressService;
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
public class CompanyAddressValidator implements Validator {

    @Resource
    private CompanyAddressService companyAddressService;

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(CompanyAddress.class);
    }

    public void validateAll(Object o, Errors errors) {
        validateEmployeeAddress(o, errors);
    }

    public void validateEmployeeAddress(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "addressDetail", "addressDetail.required");
        CompanyAddress current = (CompanyAddress) o;
        CompanyAddress old = null;
        if (current.getCompany() != null && !current.getAddressDetail().equals("") && current.getAddressDetail() != null) {
            if (current.getCompanyAddressId() != null) {
                old = companyAddressService.get(current.getCompanyAddressId());
            }
            if (companyAddressService.checkDuplicate(current, old)) {
                errors.rejectValue("addressDetail", "item.duplicate");
            }
        }
        if (current.getAddressType() == null) {
            ValidationUtils.rejectIfEmpty(errors, "addressType", "addressType.required");
        }
        if (current.getAddressType() != null) {
            AddressType addressType = current.getAddressType();
            String addressDetail = current.getAddressDetail();
            if (addressTypeContains(addressType, "number") && (!addressTypeContains(addressType, "cell"))) {
                if (current.getAddressDetail() != null
                        && current.getAddressDetail().replace(" ", "").matches(".*\\D+.*")) {
                    errors.rejectValue("addressDetail", "addressDetail.invalidNumber");
                }
            }
            if (addressTypeContains(addressType, "cell")) {
                if (addressDetail != null) {
                    if (!addressDetail.matches("^[0-9]{9,}||[+0-9]{13,}+$")) {
                        errors.rejectValue("addressDetail", "addressDetail.invalidCellNumber");
                    }
                }
            }
            if (addressTypeContains(addressType, "email")) {
                if (addressDetail != null) {
                    if (!addressDetail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        errors.rejectValue("addressDetail", "addressDetail.invalidEmailAddress");
                    }
                }
            }
        }

    }

    @Override
    public void validate(Object o, Errors errors) {
    }

    private boolean addressTypeContains(AddressType addressType, String content) {
        return addressType != null
                && addressType.getName() != null
                && addressType.getName().trim().toLowerCase().contains(content);
    }
}
