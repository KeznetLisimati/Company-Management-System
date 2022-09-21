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
@Table(name = "company_bank_detail", catalog = "company", schema = "")
@XmlRootElement
public class CompanyBankDetail extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String companyBankDetailId;
    private String accountNumber;
    private String accountName;
    private String branch;
    private String bank;
    private Company company;
    private AccountType accountType;
    private Boolean status = Boolean.TRUE;

    public CompanyBankDetail() {
    }

    public CompanyBankDetail(String companyBankDetailId) {
        this.companyBankDetailId = companyBankDetailId;
    }

    public CompanyBankDetail(String companyBankDetailId, String accountNumber, String accountName, String branch, String bank) {
        this.companyBankDetailId = companyBankDetailId;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.branch = branch;
        this.bank = bank;
    }

    public CompanyBankDetail(Company company) {
        this.company = company;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "company_bank_detail_id", nullable = false, length = 36)
    public String getCompanyBankDetailId() {
        return companyBankDetailId;
    }

    public void setCompanyBankDetailId(String companyBankDetailId) {
        this.companyBankDetailId = companyBankDetailId;
    }

    @Basic(optional = false)
    @Column(name = "account_number", nullable = false, length = 50)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Basic(optional = false)
    @Column(name = "account_name", nullable = false, length = 100)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Basic(optional = false)
    @Column(name = "branch", nullable = false, length = 100)
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Basic(optional = false)
    @Column(name = "bank", nullable = false, length = 150)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @XmlTransient
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
    @ManyToOne(optional = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id", nullable = false)
    @ManyToOne(optional = false)
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof CompanyBankDetail)) {
            return false;
        }
        return this.getCompanyBankDetailId().equals(((CompanyBankDetail)obj).getCompanyBankDetailId());
    }

    @Override
    public int hashCode() {
        return companyBankDetailId.hashCode();
    } 
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "org.company.CompanyBankDetail[ companyBankDetailId=" + companyBankDetailId + " ]";
    }
}