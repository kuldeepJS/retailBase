package com.innovations.retailBase.entities.legacy;

public enum CustomerType {
	
	REGISTERD,
	VISITOR,
	THIRD_PARTY,
	NONE;
	
	public CustomerType getCustomerType(int customerType){
		switch(customerType){
		case 1:
			return REGISTERD;
		case 2:
			return VISITOR;
		case 3:
			return THIRD_PARTY;
		default:
			return NONE;
		}
	}
}
