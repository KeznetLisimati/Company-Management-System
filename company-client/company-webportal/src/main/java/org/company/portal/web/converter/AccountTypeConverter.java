/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.converter;

import javax.annotation.Resource;
import org.company.business.domain.AccountType;
import org.company.business.service.AccountTypeService;
import org.springframework.core.convert.converter.Converter;

public class AccountTypeConverter implements Converter<String, AccountType> {

    @Resource
    private AccountTypeService accountTypeService;

   @Override
    public AccountType convert(String s) {
        if(s.equals("")) return null;
        return accountTypeService.get(s);
    }
    
}