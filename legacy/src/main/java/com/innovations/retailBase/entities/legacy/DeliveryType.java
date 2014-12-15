package com.innovations.retailBase.entities.legacy;

public enum DeliveryType {

	//0:Same day;1:One day delivery and so on
	SAME_DAY,
	ONE_DAY,
	DEFAULT,
	NONE;
	
	public static DeliveryType getDeliveryType(int type){
		switch(type){
		case 0:
			return SAME_DAY;
		case 1:
			return ONE_DAY;
		case 2:
			return DEFAULT;
		default:
			return NONE;
		}
	}
	
}
