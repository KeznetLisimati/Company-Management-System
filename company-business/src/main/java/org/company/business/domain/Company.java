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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "company", catalog = "company")
@XmlRootElement
public class Company extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String companyId;
    private String name;
    private double balance;
    private String contactPerson;
    private String industry;
    private String country;
    private String currency;
    private Date startDate;
    private String companyNo;
    private Set<CompanyBankDetail> companyBankDetail;
    private Set<CompanyContact> companyContacts;
    private Set<CompanyAddress> companyAddress;
    private String district;
    private String province;
    private String companyType;
    
    public Company() {
    }

    public Company(String companyId) {
        this.companyId = companyId;
    }

    public Company(String companyId, String name, double balance, String companyType, String contactPerson, String industry, String country, String currency, Date startDate, String companyNo, Set<CompanyBankDetail> companyBankDetail, Set<CompanyContact> companyContacts, Set<CompanyAddress> companyAddress, String district, String province) {
        this.companyId = companyId;
        this.name = name;
        this.balance = balance;
        this.contactPerson = contactPerson;
        this.industry = industry;
        this.country = country;
        this.currency = currency;
        this.startDate = startDate;
        this.companyNo = companyNo;
        this.companyBankDetail = companyBankDetail;
        this.companyContacts = companyContacts;
        this.companyAddress = companyAddress;
        this.district = district;
        this.province = province;
        
    }
    

    @Id
    @Basic(optional = false)
    @Column(name = "company_id", nullable = false, length = 36)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    @OneToMany(mappedBy = "company")
    public Set<CompanyBankDetail> getCompanyBankDetails() {
        return companyBankDetail;
    }

    public void setCompanyBankDetails(Set<CompanyBankDetail> companyBankDetail) {
        this.companyBankDetail = companyBankDetail;
    }

    @OneToMany(mappedBy = "company")
    public Set<CompanyContact> getCompanyContacts() {
        return companyContacts;
    }

    public void setCompanyContacts(Set<CompanyContact> companyContacts) {
        this.companyContacts = companyContacts;
    }

    @OneToMany(mappedBy = "company")
    public Set<CompanyAddress> getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Set<CompanyAddress> companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

  

    
    @Transient
    public String getPic() {
//        if (gender == null) {
//            return "/resources/images/noimage.gif";
//        } else if (gender.equalsIgnoreCase("M")) {
//            return "/resources/images/male.gif";
//        } else if (gender.equalsIgnoreCase("F")) {
//            return "/resources/images/female.gif";
//        }
        return "/resources/images/noimage.gif";

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Company)) {
            return false;
        }
        return this.getCompanyId().equals(((Company) obj).getCompanyId());
    }

    @Override
    public int hashCode() {
        return companyId.hashCode();
    }
}
