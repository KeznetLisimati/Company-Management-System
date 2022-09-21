/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "privilege", catalog = "company", uniqueConstraints
        = @UniqueConstraint(columnNames = "name"))
public class Privilege extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String privilegeId;
    Set<UserRole> roles = new HashSet<>();

    public Privilege() {
    }

    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "privilege_id", nullable = false, length = 36)
    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    @ManyToMany(mappedBy = "privileges")
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Transient
    public String getId() {
        return privilegeId;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Privilege)) {
            return false;
        }
        return this.getPrivilegeId().equals(((Privilege)obj).getPrivilegeId());
    }

    @Override
    public int hashCode() {
        return privilegeId.hashCode();
    }
}
