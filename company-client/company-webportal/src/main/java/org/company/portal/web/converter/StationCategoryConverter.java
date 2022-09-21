/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.converter;

import javax.annotation.Resource;
import org.company.business.domain.StationCategory;
import org.company.business.service.StationCategoryService;
import org.springframework.core.convert.converter.Converter;

public class StationCategoryConverter implements Converter<String, StationCategory> {

    @Resource
    private StationCategoryService service;

   @Override
    public StationCategory convert(String s) {
        if(s.equals("")) return null;
        return service.get(s);
    }
    
}