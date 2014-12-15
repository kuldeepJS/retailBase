package com.innovations.retailBase.entities.legacy;

public enum BucketStatus {
	
	IN_PURCHASE,
	UNDER_CHECKOUT,
	FROZEN,
	CANCELLED,
	NONE;
	
	public static BucketStatus getStatus(int status){
		switch(status){
		case 0:
			return IN_PURCHASE;
		case 1:
			return UNDER_CHECKOUT;
		case 2:
			return FROZEN;
		case 3:
			return CANCELLED;
		default:
			return NONE;
		}
	}
}
