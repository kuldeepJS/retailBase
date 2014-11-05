/**
 * 
 */
package com.innovations.retailBase.entities.legacy;

import java.util.List;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file ZoneInstance.java
 * @project legacy
 * @package com.innovations.retailBase.entities.legacy
 * @summary Loads combinational data for zones 
 */
public class ZoneInstance {
	
	private static String ZONE_COMBO_ID = "S0;D0;C0;A0";
	private static List<ZoneInstance> combinationalZones;
	
	private Object zoneId;
	private List<ZoneInstance> containedZones;
	private ZoneInstance parentInstance;
	private boolean virtualInstance;
	
	//1: ZIPCODE/AREA; 2: STATE; 3: DISTRICT; 4: CITY
	private int zoneLevel;
	
	public ZoneInstance(){
		
		//Level from default constructor will only
		//be set to 1 i.e. from UI zone at level of
		//pin code...
		zoneLevel = 1;
		
		virtualInstance = true;
		
	}
	
	public void freezeInstance(){
		//This method will be called only after all the entries
		//to be made are set 
		
	}
	
	@Override
	public boolean equals(Object second){
		if(second != null && second instanceof ZoneInstance){
			ZoneInstance scnd = (ZoneInstance)second;
			if(scnd.zoneLevel == this.zoneLevel){
				switch(scnd.zoneLevel){
				case 1:
					return (Integer.parseInt(this.zoneId.toString()) == Integer.parseInt(scnd.zoneId.toString()));
				default:
					return (this.zoneId.toString().equals(scnd.zoneId.toString()));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
