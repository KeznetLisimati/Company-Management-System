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
@Table(name = "company_contact", catalog = "company", schema = "")
@XmlRootElement
public class CompanyContact extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String companyContactId;
    private String contactDetail;
    private boolean status = true;
    private ContactType contactType;
    private Company company;

    public CompanyContact() {
    }

    public CompanyContact(String companyContactId) {
        this.companyContactId = companyContactId;
    }

    public CompanyContact(Company company) {
        this.company = company;
    }

    public CompanyContact(String companyContactId, String contactDetail, boolean status) {
        this.companyContactId = companyContactId;
        this.contactDetail = contactDetail;
        this.status = status;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "company_contact_id", nullable = false, length = 36)
    public String getCompanyContactId() {
        return companyContactId;
    }

    public void setCompanyContactId(String companyContactId) {
        this.companyContactId = companyContactId;
    }

    @Basic(optional = false)
    @Column(name = "contact_detail", nullable = false, length = 255)
    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
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

    @JoinColumn(name = "contact_type_id", referencedColumnName = "contact_type_id", nullable = false)
    @ManyToOne(optional = false)
    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
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
        if (! (obj instanceof CompanyContact)) {
            return false;
        }
        return this.getCompanyContactId().equals(((CompanyContact)obj).getCompanyContactId());
    }

    @Override
    public int hashCode() {
        return companyContactId.hashCode();
    }

    @Override
    public String toString() {
        return getContactDetail();
    }
}