package org.tact.base.util;

import java.io.IOException;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class GeoLocationUtil {

	
	/**
	 * 
	 * @param ip
	 * @return
	 * @throws IOException
	 * 
	 * Note:
	 * 		GeoLiteCity.dat file can be downloaded from https://dev.maxmind.com/geoip/legacy/geolite/
	 * 
	 */
	public static Location getGeoLocation(String ip) throws IOException{
		
		String geoDatFileLocation = "C:\\geolocation\\GeoLiteCity.dat";
		
		LookupService cl = new LookupService(geoDatFileLocation, 
				LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
		Location location = cl.getLocation(ip);	
		
		return location;
	}
}
