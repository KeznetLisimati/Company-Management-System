/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "employee", catalog = "company", schema = "")
@XmlRootElement
public class Employee extends BaseMetaData implements Serializable {

    private String employeeId;
    private String contact;
    private String address;
    private String gender;
    private int age;
    private boolean status = true;

    public Employee() {
    }

    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(String contact, String address, String gender, int age) {
        this.contact = contact;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }

    
    @Id
    @Basic(optional = false)
    @Column(name = "employee_id", nullable = false, length = 36)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        String preferred = status ? "Married" : "Single";
        return preferred;
    }

    @Transient
    public String getId() {
        return employeeId;
    }

   
   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Employee)) {
            return false;
        }
        return this.getEmployeeId().equals(((Employee)obj).getEmployeeId());
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode();
    }
}
