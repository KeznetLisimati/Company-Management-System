/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "company_address", catalog = "company", schema = "")
@XmlRootElement
public class CompanyAddress extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String companyAddressId;
    private String addressDetail;
    private boolean status = true;
    private AddressType addressType;
    private Company company;

    public CompanyAddress() {
    }

    public CompanyAddress(String companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public CompanyAddress(Company company) {
        this.company = company;
    }

    public CompanyAddress(String companyAddressId, String addressDetail, boolean status) {
        this.companyAddressId = companyAddressId;
        this.addressDetail = addressDetail;
        this.status = status;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "company_address_id", nullable = false, length = 36)
    public String getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(String companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    @Basic(optional = false)
    @Column(name = "address_detail", nullable = false, length = 255)
    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Basic(optional = false)
    @Column(name = "Status", nullable = false)
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Transient
    public String getPreferred() {
        String preferred = status ? "Active" : "Inactive";
        return preferred;
    }

    @JoinColumn(name = "address_type_id", referencedColumnName = "address_type_id", nullable = false)
    @ManyToOne(optional = false)
    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @XmlTransient
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof CompanyAddress)) {
            return false;
        }
        return this.getCompanyAddressId().equals(((CompanyAddress)obj).getCompanyAddressId());
    }

    @Override
    public int hashCode() {
        return companyAddressId.hashCode();
    }

    @Override
    public String toString() {
        return getAddressDetail();
    }
}