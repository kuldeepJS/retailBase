package com.innovations.retailBase.entities.legacy;

public enum PaymentType {
	//0:NET_BANKING;1:DEBIT_CARD;2:CREDIT_CARD;3:COD
	NET_BANKING,
	DEBIT_CARD,
	CREDIT_CARD,
	COD,
	NONE;
	
	public static PaymentType getPaymentType(int type){
		switch(type){
		case 0:
			return NET_BANKING;
		case 1:
			return DEBIT_CARD;
		case 2:
			return CREDIT_CARD;
		case 3:
			return COD;
		default:
			return NONE;
		}
	}
}
