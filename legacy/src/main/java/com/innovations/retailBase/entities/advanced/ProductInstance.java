package com.innovations.retailBase.entities.advanced;

import java.util.List;

import com.innovations.retailBase.entities.legacy.PriceInstance;

public class ProductInstance {

	private com.innovations.retailBase.entities.legacy.ProductInstance coreInstance;
	
	//Price after applying any discount
	private PriceInstance priceInstance;
	
	//List of products which are clubbed with the original products
	private List<com.innovations.retailBase.entities.legacy.ProductInstance> addedProducts;
	
	public ProductInstance(com.innovations.retailBase.entities.legacy.ProductInstance coreInstance) {
		this.coreInstance = coreInstance;
	}

	public com.innovations.retailBase.entities.legacy.ProductInstance getCoreInstance() {
		return coreInstance;
	}

	public PriceInstance getPriceInstance() {
		return priceInstance;
	}

	public void setPriceInstance(PriceInstance priceInstance) {
		this.priceInstance = priceInstance;
	}

	public List<com.innovations.retailBase.entities.legacy.ProductInstance> getAddedProducts() {
		return addedProducts;
	}
	
}
