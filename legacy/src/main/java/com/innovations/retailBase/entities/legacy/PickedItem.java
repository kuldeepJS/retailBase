package com.innovations.retailBase.entities.legacy;

import java.util.Date;

public class PickedItem {
	
	private int itemId;
	private ProductInstance prdInstance;
	private float pickedPrice;
	private Date pickedOn;
	private int numberOfInstances;
	private Date lastUpdatedOn;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public ProductInstance getPrdInstance() {
		return prdInstance;
	}
	public void setPrdInstance(ProductInstance prdInstance) {
		this.prdInstance = prdInstance;
	}
	public float getPickedPrice() {
		return pickedPrice;
	}
	public void setPickedPrice(float pickedPrice) {
		this.pickedPrice = pickedPrice;
	}
	public Date getPickedOn() {
		return pickedOn;
	}
	public void setPickedOn(Date pickedOn) {
		this.pickedOn = pickedOn;
	}
	public int getNumberOfInstances() {
		return numberOfInstances;
	}
	public void setNumberOfInstances(int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	
	
}
