/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name = "station", catalog = "company", schema = "")
@XmlRootElement
public class Station extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String stationId;
    private District district;
    private StationCategory category;
    private String longitudeCoordinate;
    private String latitudeCoordinate;
    private String externalStationId;
    private String stationCode;
    private Province province;

    public Station() {
    }

    public Station(String stationId) {
        this.stationId = stationId;
    }

    public Station(String stationId, String name, Date dateCreated) {
        this.stationId = stationId;

    }
    
    @ManyToOne
    @JoinColumn(name = "station_category_id", nullable = true, updatable = true)
    public StationCategory getCategory() {
        return category;
    }

    public void setCategory(StationCategory category) {
        this.category = category;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "station_id", nullable = false, length = 36)
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Basic(optional=true)
    @Column(name = "station_code",nullable=true)
    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = true, updatable = true)
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Column(name = "external_station_id", nullable = true, length = 50)
    public String getExternalStationId() {
        return externalStationId;
    }

    public void setExternalStationId(String externalStationId) {
        this.externalStationId = externalStationId;
    }

    @Column(name = "latitude_coordinate", nullable = true, length = 50)
    public String getLatitudeCoordinate() {
        return latitudeCoordinate;
    }

    public void setLatitudeCoordinate(String latitudeCoordinate) {
        this.latitudeCoordinate = latitudeCoordinate;
    }

    @Column(name = "longitude_coordinate", nullable = true, length = 50)
    public String getLongitudeCoordinate() {
        return longitudeCoordinate;
    }

    public void setLongitudeCoordinate(String longitudeCoordinate) {
        this.longitudeCoordinate = longitudeCoordinate;
    }

    @Transient
    public String getId() {
        return stationId;
    }

    @Transient
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Station)) {
            return false;
        }
        return this.getStationId().equals(((Station)obj).getStationId());
    }

    @Override
    public int hashCode() {
        return stationId.hashCode();
    }
}
