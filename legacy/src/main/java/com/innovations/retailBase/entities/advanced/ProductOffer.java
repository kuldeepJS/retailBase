package com.innovations.retailBase.entities.advanced;

import javax.activity.InvalidActivityException;

public abstract class ProductOffer {

	private OfferType typeOfOffer;
	
	public ProductOffer(OfferType typeOfOffer) {
		this.typeOfOffer = typeOfOffer;
	}
	
	public OfferType getTypeOfOffer() {
		return typeOfOffer;
	}

	public abstract ProductInstance getAdvancedProductInstance(com.innovations.retailBase.entities.legacy.ProductInstance coreInstance)  throws InvalidActivityException;
	
	public enum OfferType {
		PRODUCT_INSTANCE,
		PRODUCT_MASTER,
		HIERATCHY_MASTER,
		BRAND_MASTER
	}
	
}
