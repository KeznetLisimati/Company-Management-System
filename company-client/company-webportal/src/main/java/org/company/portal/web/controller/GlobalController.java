package org.company.portal.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.company.business.domain.District;
import org.company.business.domain.Province;
import org.company.business.domain.Station;
import org.company.business.service.DistrictService;
import org.company.business.service.StationService;
import org.company.business.util.dto.NameIdDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Edward Zengeni
 */
@Controller
@RequestMapping("/gobal")
public class GlobalController {
    
    @Resource
    private DistrictService districtService;
    @Resource
    private StationService stationService;    
    
    @RequestMapping(value="/getprovincedistricts", method= RequestMethod.GET, headers="Accept=application/json")
    @ResponseBody
    public List<NameIdDTO> getProvinceDistricts(Province province){
        return formatDistricts(districtService.getDistrictByProvince(province));
    }
    
    @RequestMapping(value="/getdistrictstations", method= RequestMethod.GET,  headers="Accept=application/json")
    @ResponseBody
    public List<NameIdDTO> getDistrictStations(District district){        
        return formatStations(stationService.getByDistrict(district));
    }
    
    private List<NameIdDTO> formatDistricts(List<District> districts){
        List<NameIdDTO> items = new ArrayList<>();
        for(District district : districts){
            items.add(new NameIdDTO(district.getName(), district.getDistrictId()));
        }
        return items;
    }
    
    private List<NameIdDTO> formatStations(List<Station> stations){
        List<NameIdDTO> items = new ArrayList<>();
        for(Station station : stations){
            items.add(new NameIdDTO(station.getName(), station.getStationId()));
        }
        return items;
    }
    
    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    @ResponseBody
    public List<NameIdDTO> getTest(){
        List<NameIdDTO> items = new ArrayList<>();
        items.add(new NameIdDTO("Judge", "1"));
        items.add(new NameIdDTO("Jackie", "2"));
        items.add(new NameIdDTO("Nyasha", "3"));
        items.add(new NameIdDTO("Tapiwa", "4"));
        return items;
    }
}