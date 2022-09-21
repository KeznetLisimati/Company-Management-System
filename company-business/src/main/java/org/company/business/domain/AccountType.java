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
@Table(name = "account_type", catalog = "company", schema = "")
@XmlRootElement
public class AccountType extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String accountTypeId;

    public AccountType() {
    }

    public AccountType(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public AccountType(String accountTypeId, String name, Date dateCreated) {
        this.accountTypeId = accountTypeId;

    }

    @Id
    @Basic(optional = false)
    @Column(name = "account_type_id", nullable = false, length = 36)
    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Transient
    public String getId() {
        return accountTypeId;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof AccountType)) {
            return false;
        }
        return this.getAccountTypeId().equals(((AccountType)obj).getAccountTypeId());
    }

    @Override
    public int hashCode() {
        return accountTypeId.hashCode();
    }
}
