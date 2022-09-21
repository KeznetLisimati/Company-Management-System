/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "role", catalog = "company", uniqueConstraints =
@UniqueConstraint(columnNames = "name"))
@XmlRootElement
public class UserRole extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String roleId;
    @XmlTransient
    private Set<User> users = new HashSet<>();
    private Set<Privilege> privileges = new HashSet<>(0);

    public UserRole() {
    }

    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "role_id", nullable = false, length = 36)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "role_privilege", joinColumns = {
        @JoinColumn(name = "role_id")}, inverseJoinColumns = {
        @JoinColumn(name = "privilege_id")})
    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
    
    @Transient
    @Override
    public String getName(){
        return super.getName() != null ? super.getName().toUpperCase() : super.getName();
    }

    @Transient
    public String getId() {
        return roleId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof UserRole)) {
            return false;
        }
        return this.getRoleId().equals(((UserRole)obj).getRoleId());
    }

    @Override
    public int hashCode() {
        return roleId.hashCode();
    }
}
