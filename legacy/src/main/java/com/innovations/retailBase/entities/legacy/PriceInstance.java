/**
 * 
 */
package com.innovations.retailBase.entities.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import com.innovations.retailBase.locks.LockBase;
import com.innovations.retailBase.locks.LockFactory;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 07-Nov-2014
 * @file PriceInstance.java
 * @project legacy
 * @package com.innovations.retailBase.entities.legacy
 * @summary 
 */
public class PriceInstance implements LegacyEntity {
	
	private static int LOC_PID=0;
	
	private int localPriceId;
	private int priceId;
	private float sellingPrice;
	private float buyingPrice;
	private Timestamp validTill;
	private int productInstanceId;
	private int active;
	private boolean productFound;
	
	public int getActive() {
		return active;
	}
	public boolean isProductFound() {
		return productFound;
	}
	public int getProductInstanceId() {
		return productInstanceId;
	}
	public void setProductInstanceId(int productInstanceId) {
		this.productInstanceId = productInstanceId;
		productFound = true;
	}
	public float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public float getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(float buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public Timestamp getValidTill() {
		return validTill;
	}
	public void setValidTill(Timestamp validTill) {
		this.validTill = validTill;
	}
	public static int getLOC_PID() {
		return LOC_PID;
	}
	public int getLocalPriceId() {
		return localPriceId;
	}
	public int getPriceId() {
		return priceId;
	}
	
	public PriceInstance(){
		LockBase base = LockFactory.getPriceInstanceLock();
		base.lock();
		localPriceId = ++LOC_PID;
		base.unlock();
	}
	
	public PriceInstance(ResultSet rdPointer,
			HashMap<Integer, ProductInstance> productInstances) throws SQLException{
		priceId = rdPointer.getInt("PRICE_ID");
		sellingPrice = rdPointer.getFloat("SELLING_PRICE");
		buyingPrice = rdPointer.getFloat("BUYING_PRICE");
		validTill = rdPointer.getTimestamp("VALID_TILL");
		active = rdPointer.getInt("ACTIVE");
		productInstanceId = rdPointer.getInt("PRD_INSTANCE_ID");
		
		if(productInstances.containsKey(productInstanceId)){
			productFound = true;
			productInstances.get(productInstanceId).setPriceTag(this);
		}
	}
	
}
