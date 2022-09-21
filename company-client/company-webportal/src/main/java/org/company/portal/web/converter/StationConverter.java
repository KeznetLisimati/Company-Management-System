/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.converter;

import javax.annotation.Resource;
import org.company.business.domain.Station;
import org.company.business.service.StationService;
import org.springframework.core.convert.converter.Converter;

public class StationConverter implements Converter<String, Station> {

    @Resource
    private StationService stationService;

    @Override
    public Station convert(String s) {
        if(s.equals("")) return null;
        return stationService.get(s);
    }
}