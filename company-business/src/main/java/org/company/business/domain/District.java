/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Edward Zengeni
 */
@Entity
@Table(name = "district", catalog = "company")
@XmlRootElement
public class District extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String districtId;
    private Province province;
    private String districtCode;
    
    public District() {
    }

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = true, updatable = true)
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
    @Basic(optional=true)
    @Column(name = "district_code",nullable=true)
    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public District(String districtId) {
        this.districtId = districtId;
    }

    public District(String districtId, String name, Date dateCreated) {
        this.districtId = districtId;
        super.setName(name);
        super.setDateCreated(dateCreated);
    }

    @Id
    @Basic(optional = false)
    @Column(name = "district_id", nullable = false, length = 36)
    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
    

    @Transient
    public String getId() {
        return districtId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof District)) {
            return false;
        }
        return this.getDistrictId().equals(((District) obj).getDistrictId());
    }

    @Override
    public int hashCode() {
        return districtId.hashCode();
    }
    
}
