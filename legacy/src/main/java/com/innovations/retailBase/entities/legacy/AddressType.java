package com.innovations.retailBase.entities.legacy;

public enum AddressType {
	
	//1:RESIDENCE;2:OFFICE;3:PARENTS_ADDRESS;4:NEIGHBOUR;5:NEARBY_RETAILER
	RESIDENCE,
	OFFICE,
	PARENT_ADDRESS,
	NEIGHBOUR,
	NEARBY_RETAILER,
	NONE;
	
	public static AddressType getAddressType(int addressType){
		switch(addressType){
		case 1:
			return RESIDENCE;
		case 2:
			return OFFICE;
		case 3:
			return PARENT_ADDRESS;
		case 4:
			return NEIGHBOUR;
		case 5:
			return NEARBY_RETAILER;
		default:
			return NONE;
		}
	}
	
}
