/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.service;

import java.util.List;
import org.company.business.domain.Company;
import org.company.business.util.dto.SearchByNameDTO;

/**
 *
 * @author Edward Zengeni
 */
public interface CompanyService extends GenericService<Company> {

    public List<Company> findByActiveOrderByName(Boolean active);

    public List<Company> search(SearchByNameDTO dto);

}
