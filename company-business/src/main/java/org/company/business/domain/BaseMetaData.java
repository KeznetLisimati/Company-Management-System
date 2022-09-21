/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.util.UUID;
import javax.persistence.*;
import org.company.business.utils.StringUtils;

/**
 *
 * @author Edward Zengeni
 */
@MappedSuperclass
public abstract class BaseMetaData extends AbstractEntity {

    private String name;
    private String description;
    private String retireReason;
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description != null ? StringUtils.toCamelCase2(description) : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name != null ? StringUtils.toCamelCase2(name) : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "retire_reason", nullable = true)
    public String getRetireReason() {
        return retireReason;
    }

    public void setRetireReason(String retireReason) {
        this.retireReason = retireReason;
    }

    @Column(nullable = true, updatable = true)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseMetaData other = (BaseMetaData) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Transient
    public String getShortName(){
        String raw = name.trim();
        if(raw.split("\\s").length > 1){
            String output = "";
            String [] arr = raw.split("\\s");
            if(arr[0].length() >= 3){
                output += arr[0].substring(0, 3);
            }
            if(arr[1].length() >= 3){
                output += " "+arr[1];
            }
            return StringUtils.toCamelCase2(output);
        }
        if(name.length() >= 7)
            return getName().substring(0, 6);
        return getName();
    }
}
