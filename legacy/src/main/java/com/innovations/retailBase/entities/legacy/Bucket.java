package com.innovations.retailBase.entities.legacy;

import java.util.Date;
import java.util.List;

public class Bucket {
	
	private int bucketId;
	private Date pickedOn;
	private Date lastUpdatedOn;
	private int pickedId;
	private BucketStatus status;
	private List<PickedItem> items;
	
	public int getBucketId() {
		return bucketId;
	}
	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}
	public Date getPickedOn() {
		return pickedOn;
	}
	public void setPickedOn(Date pickedOn) {
		this.pickedOn = pickedOn;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public int getPickedId() {
		return pickedId;
	}
	public void setPickedId(int pickedId) {
		this.pickedId = pickedId;
	}
	public BucketStatus getStatus() {
		return status;
	}
	public void setStatus(BucketStatus status) {
		this.status = status;
	}
	public List<PickedItem> getItems() {
		return items;
	}
	public void setItems(List<PickedItem> items) {
		this.items = items;
	}
	
	
}
