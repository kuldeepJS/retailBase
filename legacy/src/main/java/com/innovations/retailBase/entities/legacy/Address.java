package com.innovations.retailBase.entities.legacy;

public class Address {
	
	private int addressId;
	private String houseNo;
	private String locality;
	private String labdMark;
	private String street1;
	private String street2;
	private String area;
	private ZoneInstance zipCode;
	private ZoneInstance city;
	private ZoneInstance state;
	private Customer customer;
	private AddressType addressType;
	private boolean defaultAddress;
	private String contactNumber;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getLabdMark() {
		return labdMark;
	}
	public void setLabdMark(String labdMark) {
		this.labdMark = labdMark;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public ZoneInstance getZipCode() {
		return zipCode;
	}
	public void setZipCode(ZoneInstance zipCode) {
		this.zipCode = zipCode;
	}
	public ZoneInstance getCity() {
		return city;
	}
	public void setCity(ZoneInstance city) {
		this.city = city;
	}
	public ZoneInstance getState() {
		return state;
	}
	public void setState(ZoneInstance state) {
		this.state = state;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	public boolean isDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
