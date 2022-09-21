/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "company_type", catalog = "company", schema = "")
@XmlRootElement
public class CompanyType extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String companyTypeId;

    public CompanyType() {
    }

    public CompanyType(String companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public CompanyType(String companyTypeId, String name, Date dateCreated) {
        this.companyTypeId = companyTypeId;

    }

    @Id
    @Basic(optional = false)
    @Column(name = "company_type_id", nullable = false, length = 36)
    public String getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(String companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    @Transient
    public String getId() {
        return companyTypeId;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof CompanyType)) {
            return false;
        }
        return this.getCompanyTypeId().equals(((CompanyType)obj).getCompanyTypeId());
    }

    @Override
    public int hashCode() {
        return companyTypeId.hashCode();
    }
}
