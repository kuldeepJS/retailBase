package com.innovations.retailBase.entities.legacy;

import java.util.Date;

public class Payment {
	private int paymentId;
	private int orderId;
	private Date paidOn;
	private PaymentType paymentType;
	private PaymentStatus paymentStatus;
	private int paymentDetailsId;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getPaymentDetailsId() {
		return paymentDetailsId;
	}
	public void setPaymentDetailsId(int paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}
}
