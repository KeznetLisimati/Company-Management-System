package org.company.business.repo;

import java.io.Serializable;
import java.util.List;
import org.company.business.domain.Company;

/**
 *
 * @author Edward Zengeni
 * @param <T>
 * @param <ID>
 */
public interface AbstractCompanyAttrRepo<T, ID extends Serializable> extends AbstractRepo<T, ID> {

    public List<T> findByCompanyAndActive(Company company, Boolean active);

}
