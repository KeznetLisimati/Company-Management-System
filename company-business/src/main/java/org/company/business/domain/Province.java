/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "province", catalog = "company", schema = "")
@XmlRootElement
public class Province extends BaseMetaData implements Serializable {

    private String provinceId;
    
    public Province() {
    }

    public Province(String provinceId) {
        this.provinceId = provinceId;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "province_id", nullable = false, length = 36)
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Transient
    public String getId() {
        return provinceId;
    }

   
   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Province)) {
            return false;
        }
        return this.getProvinceId().equals(((Province)obj).getProvinceId());
    }

    @Override
    public int hashCode() {
        return provinceId.hashCode();
    }
}
