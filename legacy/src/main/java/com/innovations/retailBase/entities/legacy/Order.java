package com.innovations.retailBase.entities.legacy;

import java.util.Date;

public class Order {

	private int orderId;
	private Date orderedOn;
	private Address address;
	private Bucket bucket;
	private DeliveryType deliveryType;
	private int transitId;
	Payment payment;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderedOn() {
		return orderedOn;
	}
	public void setOrderedOn(Date orderedOn) {
		this.orderedOn = orderedOn;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Bucket getBucket() {
		return bucket;
	}
	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}
	public int getTransitId() {
		return transitId;
	}
	public void setTransitId(int transitId) {
		this.transitId = transitId;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	
}
