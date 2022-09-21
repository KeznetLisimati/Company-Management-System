/*
 * Copyright 2014 Edward Zengeni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.company.business.util.dto;

import java.io.Serializable;
import org.company.business.domain.Province;
import org.company.business.domain.District;
import org.company.business.domain.Station;

/**
 *
 * @author Edward Zengeni
 *
 */
public class SearchDTO implements Serializable {

    private District district;
    private Station station;
    private Province province;

    public SearchDTO() {
    }

    public SearchDTO(District district, Station station, Province province) {
        this.district = district;
        this.station = station;
        this.province = province;
    }

    
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Boolean getSearch() {
        if (station == null && district == null && province == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public static SearchDTO getInstance(SearchDTO dto){
        return new SearchDTO(dto.getDistrict(),dto.getStation(), dto.getProvince());
    }

    public String getQueryString(SearchDTO dto) {
        StringBuilder query = new StringBuilder("");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                query.append("?province=");
                query.append(dto.getProvince().getId());
                position++;
            } else {
                query.append("&province=");
                query.append(dto.getProvince().getId());
            }
        }
        if (dto.getStation() != null) {
            if (position == 0) {
                query.append("?station=");
                query.append(dto.getStation().getId());
                position++;
            } else {
                query.append("&station=");
                query.append(dto.getStation().getId());
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                query.append("?district=");
                query.append(dto.getDistrict().getId());
                position++;
            } else {
                query.append("&district=");
                query.append(dto.getDistrict().getId());
            }
        }
        return query.toString();
    }

    @Override
    public String toString() {
        return "SearchDTO{" + "district=" + district + ", station=" + station + ", province=" + province + '}';
    }
}
