package org.tact.base.rest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tact.base.util.GeoLocationUtil;

import com.maxmind.geoip.Location;

@RestController
@RequestMapping(value = "/base")
public class BaseController {
	
	/**
	 * 
	 * @return
	 * 
	 * Possible urls:
	 * 		http://localhost:1878/base/
	 */
    @GetMapping(value = "")
    public <T> T testBase() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
        map.put("three", "four");
        map.put("five", "six");
        map.put("seven", "eight");
        
        return (T) map;
    }
    
    /**
     * 
     * @param ip
     * @param request
     * @param response
     * @return
     * @throws IOException
     * 
     * Possible urls:
	 * 		http://localhost:1878/base/get/ip/location
	 * 
	 * Sample:
	 * 		http://localhost:1878/base/get/ip/location?ip=14.139.250.240
	 * 		http://localhost:1878/base/get/ip/location?ip=5.34.169.72
	 * 		http://localhost:1878/base/get/ip/location?ip=70.28.13.169
     */
    @GetMapping(value = "get/ip/location")
    public <T> T getGeoLocation(
    	@RequestParam(value = "ip") String ip,
    	HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	Location location = GeoLocationUtil.getGeoLocation(ip);
    	
    	if(location == null){
    		Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("error", "Location Not Available");
            
            return (T) map;
    	}
    	
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("city", location.city);
        map.put("country_code", location.countryCode);
        map.put("country_name", location.countryName);
        
        return (T) map;
    }
}
