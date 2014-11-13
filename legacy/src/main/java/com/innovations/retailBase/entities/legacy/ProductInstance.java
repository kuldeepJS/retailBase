/**
 * 
 */
package com.innovations.retailBase.entities.legacy;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file ProductInstance.java
 * @project legacy
 * @package com.innovations.retailBase.entities.legacy
 * @summary Stores data from product_instance table
 */
public class ProductInstance {
	
	private int instanceId;
	private ProductSkeleton productMaster;
	private String name;
	private String label;
	private String description;
	private PriceInstance priceTag;
	private int status;
	public int getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}
	public ProductSkeleton getProductMaster() {
		return productMaster;
	}
	public void setProductMaster(ProductSkeleton productMaster) {
		this.productMaster = productMaster;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PriceInstance getPriceTag() {
		return priceTag;
	}
	public void setPriceTag(PriceInstance priceTag) {
		this.priceTag = priceTag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
