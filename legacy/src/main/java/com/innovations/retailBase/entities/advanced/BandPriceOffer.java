package com.innovations.retailBase.entities.advanced;

import javax.activity.InvalidActivityException;

import com.innovations.retailBase.entities.legacy.BrandInstance;
import com.innovations.retailBase.entities.legacy.PriceInstance;

public class BandPriceOffer extends ProductOffer {

	private BrandInstance brand;
	private float[] priceDiscount;
	
	public BrandInstance getBrand() {
		return brand;
	}

	public float[] getPriceDiscount() {
		return priceDiscount;
	}

	public BandPriceOffer(BrandInstance brand, OfferType typeOfOffer, float[] priceDiscount) {
		super(typeOfOffer);
		this.brand = brand;
		this.priceDiscount = priceDiscount;
	}

	@Override
	public ProductInstance getAdvancedProductInstance(
			com.innovations.retailBase.entities.legacy.ProductInstance coreInstance) throws InvalidActivityException {
		
		if(coreInstance.getProductMaster().getBrand().getBrandId() != brand.getBrandId())
			throw new InvalidActivityException("Brand mismatch during applying discount!");
		
		ProductInstance advInstance = new ProductInstance(coreInstance);
		
		PriceInstance coreInsPrice = coreInstance.getPriceTag();
		float corePrice = coreInsPrice.getSellingPrice();
		
		for(float dp : priceDiscount) {
			corePrice = corePrice - corePrice*(dp/100);
			if(corePrice < 0) {
				corePrice = 0;
				break;
			}
		}
		
		PriceInstance newOfferPrice = new PriceInstance();
		newOfferPrice.setBuyingPrice(corePrice);
		
		advInstance.setPriceInstance(newOfferPrice);
		
		return advInstance;
	}

}
