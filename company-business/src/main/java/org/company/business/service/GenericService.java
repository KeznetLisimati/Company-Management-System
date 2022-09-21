package org.company.business.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Edward Zengeni
 * @param <T>
 *  
 */
public interface GenericService<T extends Serializable> extends Serializable {
    
    public List<T> getAll();
    public T get(String id);
    public void delete(T t);
    public List<T> getPageable();
    public T save(T t);
    public Boolean checkDuplicate(T current, T old);
}