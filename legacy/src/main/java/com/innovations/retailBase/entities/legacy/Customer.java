package com.innovations.retailBase.entities.legacy;

import java.util.Date;
import java.util.List;

public class Customer {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String emailId;
	private Date registeredOn;
	private Date lastVisitedOn;
	private Password password;
	private String contactNumber;
	private List<Address> addresses;
	private CustomerType customerType;
	private Bucket currentBucket;
	private String thirdPartyId;
	
	
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getRegisteredOn() {
		return registeredOn;
	}
	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}
	public Date getLastVisitedOn() {
		return lastVisitedOn;
	}
	public void setLastVisitedOn(Date lastVisitedOn) {
		this.lastVisitedOn = lastVisitedOn;
	}
	public Password getPassword() {
		return password;
	}
	public void setPassword(Password password) {
		this.password = password;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public Bucket getCurrentBucket() {
		return currentBucket;
	}
	public void setCurrentBucket(Bucket currentBucket) {
		this.currentBucket = currentBucket;
	}
}
