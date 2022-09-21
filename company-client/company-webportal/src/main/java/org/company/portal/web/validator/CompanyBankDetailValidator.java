/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import javax.annotation.Resource;
import org.company.business.domain.CompanyBankDetail;
import org.company.business.service.CompanyBankDetailService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CompanyBankDetailValidator implements Validator {
    
    @Resource
    private CompanyBankDetailService employeeBankDetailService;
    
    @Override
    public boolean supports(Class<?> type) {
        return type.equals(CompanyBankDetail.class);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        CompanyBankDetail current = (CompanyBankDetail) o;
        CompanyBankDetail old = null;
        if (current.getCompany() != null && !current.getBank().equals("") && !current.getAccountNumber().equals("")) {
            if (current.getCompanyBankDetailId() != null) {
                old = employeeBankDetailService.get(current.getCompanyBankDetailId());
            }
            if (employeeBankDetailService.checkDuplicate(current, old)) {
                errors.rejectValue("accountNumber", "item.duplicate");
            }
        }
        ValidationUtils.rejectIfEmpty(errors, "branch", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "bank", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "accountName", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "accountNumber", "item.required");
        if (current.getAccountType() == null) {
            errors.rejectValue("accountType", "item.required");
        }
    }
}
