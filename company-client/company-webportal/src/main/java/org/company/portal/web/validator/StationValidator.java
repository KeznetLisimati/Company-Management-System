/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.validator;

import org.company.business.domain.Station;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StationValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Station.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Station item = (Station) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
        ValidationUtils.rejectIfEmpty(errors, "stationCode", "stationCode.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "description.required");
        if (item.getDistrict() == null) {
            ValidationUtils.rejectIfEmpty(errors, "district", "district.required");
        }
        if (item.getCategory() == null) {
            ValidationUtils.rejectIfEmpty(errors, "category", "category.required");
        }
        if(item.getProvince() == null){
            errors.rejectValue("province", "field.empty");
        }
    }
}
