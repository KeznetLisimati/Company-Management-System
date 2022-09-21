/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.converter;

import javax.annotation.Resource;
import org.company.business.domain.ContactType;
import org.company.business.service.ContactTypeService;
import org.springframework.core.convert.converter.Converter;

public class ContactTypeConverter implements Converter<String, ContactType> {

    @Resource
    private ContactTypeService contactTypeService;

   @Override
    public ContactType convert(String s) {
        if(s.equals("")) return null;
        return contactTypeService.get(s);
    }
    
}