/*
 * Copyright 2016 Edward Zengeni.
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

import org.company.business.domain.District;
import org.company.business.domain.Province;
import org.company.business.domain.Station;

/**
 *
 * @author Edward Zengeni
 */
public class GenderDTO extends SearchDTO {
    
    private String gender;

    public GenderDTO() {
    }

    public GenderDTO(String gender, District district, Station station, Province province) {
        super(district, station, province);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
    public static GenderDTO getInstance(GenderDTO dto){
        GenderDTO item = new GenderDTO(dto.getGender(), dto.getDistrict(), dto.getStation(), dto.getProvince());
        sanitizeItem(item);
        return item;
    }
    
    private static void sanitizeItem(GenderDTO dto){
        if(dto.getStation() != null){
            dto.setDistrict(null);
            dto.setProvince(null);
        }
        if(dto.getDistrict() != null){
            dto.setProvince(null);
        }
    }
}