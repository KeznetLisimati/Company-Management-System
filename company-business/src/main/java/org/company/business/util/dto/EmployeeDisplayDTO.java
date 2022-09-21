package org.company.business.util.dto;

import java.io.Serializable;

/**
 *
 * @author Edward Zengeni
 */
public class EmployeeDisplayDTO implements Serializable {

    private String post;
    private String ecNumber;
    private String id;
    private String name;
    private String district;
    private String station;

    public EmployeeDisplayDTO() {
    }

    public EmployeeDisplayDTO(String post, String ecNumber, String id, String name, String district, String station) {
        this.post = post;
        this.ecNumber = ecNumber;
        this.id = id;
        this.name = name;
        this.district = district;
        this.station = station;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEcNumber() {
        return ecNumber;
    }

    public void setEcNumber(String ecNumber) {
        this.ecNumber = ecNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
    
}
