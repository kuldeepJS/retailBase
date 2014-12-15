package com.innovations.retailBase.entities.legacy;

public enum PaymentStatus {

	//0:UNDER_PROCESS;1:DONE;2:CANCELLED;3:INCOMPLETE
	UNDER_PROCESS,
	DONE,
	CANCELLED,
	INCOMPLETE,
	NONE;
	
	public static PaymentStatus getStatus(int status){
		switch(status){
		case 0:
			return UNDER_PROCESS;
		case 1:
			return DONE;
		case 2:
			return CANCELLED;
		case 3:
			return INCOMPLETE;
		default:
			return NONE;
		}
	}
	
}
