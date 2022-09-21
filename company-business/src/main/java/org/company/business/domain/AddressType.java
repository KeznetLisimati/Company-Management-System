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
@Table(name = "address_type", catalog = "company", schema = "")
@XmlRootElement
public class AddressType extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String addressTypeId;

    public AddressType() {
    }

    public AddressType(String addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public AddressType(String addressTypeId, String name, Date dateCreated) {
        this.addressTypeId = addressTypeId;

    }

    @Id
    @Basic(optional = false)
    @Column(name = "address_type_id", nullable = false, length = 36)
    public String getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(String addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    @Transient
    public String getId() {
        return addressTypeId;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof AddressType)) {
            return false;
        }
        return this.getAddressTypeId().equals(((AddressType)obj).getAddressTypeId());
    }

    @Override
    public int hashCode() {
        return addressTypeId.hashCode();
    }
}
