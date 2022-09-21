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
@Table(name = "station_category", catalog = "company", schema = "")
@XmlRootElement
public class StationCategory extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String stationCategoryId;

    public StationCategory() {
    }

    public StationCategory(String stationCategoryId) {
        this.stationCategoryId = stationCategoryId;
    }

    public StationCategory(String stationCategoryId, String name, Date dateCreated) {
        this.stationCategoryId = stationCategoryId;

    }

    @Id
    @Basic(optional = false)
    @Column(name = "station_category_id", nullable = false, length = 36)
    public String getStationCategoryId() {
        return stationCategoryId;
    }

    public void setStationCategoryId(String stationCategoryId) {
        this.stationCategoryId = stationCategoryId;
    }

    @Transient
    public String getId() {
        return stationCategoryId;
    }

   @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof StationCategory)) {
            return false;
        }
        return this.getStationCategoryId().equals(((StationCategory)obj).getStationCategoryId());
    }

    @Override
    public int hashCode() {
        return stationCategoryId.hashCode();
    }
}
