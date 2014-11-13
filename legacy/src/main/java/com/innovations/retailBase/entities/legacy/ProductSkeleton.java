/**
 * 
 */
package com.innovations.retailBase.entities.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.innovations.retailBase.locks.LockBase;
import com.innovations.retailBase.locks.LockFactory;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file ProductSkeleton.java
 * @project legacy
 * @package com.innovations.retailBase.entities.legacy
 * @summary This class contains the details from product_master
 */
public class ProductSkeleton {
	
	private static int LOCAL_PID = 0;
	
	private int localProductId;
	private int productId;
	private String name;
	private String label;
	private String description;
	private HierarchyInstance hierarchyInstance;
	private int status;
	private String imageURL;
	private boolean virtualProduct;
	private BrandInstance brand;
	
	public BrandInstance getBrand() {
		return brand;
	}
	public void setBrand(BrandInstance brand) {
		this.brand = brand;
	}
	public static int getLOCAL_PID() {
		return LOCAL_PID;
	}
	public int getLocalProductId() {
		return localProductId;
	}
	public boolean isVirtualProduct() {
		return virtualProduct;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public HierarchyInstance getHierarchyInstance() {
		return hierarchyInstance;
	}
	public void setHierarchyInstance(HierarchyInstance hierarchyInstance) {
		this.hierarchyInstance = hierarchyInstance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public ProductSkeleton(){
		LockBase lock = LockFactory.getProductMasterLock();
		lock.lock();
		localProductId = ++LOCAL_PID;
		virtualProduct = true;
		lock.unlock();
	}
	
	public ProductSkeleton(ResultSet rdPointer, 
			HashMap<Integer, HierarchyInstance> hierarchyInstances,
			HashMap<Integer, BrandInstance> brands) throws SQLException{
		productId = rdPointer.getInt(" ProductId");
		name = rdPointer.getString("Name");
		label = rdPointer.getString("Label");
		description = rdPointer.getString("description");
		int hierarchyMasterId = rdPointer.getInt("hierarchyMasterId");
		status = rdPointer.getInt("STATUS");
		imageURL = rdPointer.getString("ImageURL");
		int brandId = rdPointer.getInt("BRANDID");
		
		//Load the image here or we can delay it(Lazy load)
		
		if(hierarchyInstances.containsKey(hierarchyMasterId)){
			hierarchyInstance = hierarchyInstances.get(hierarchyMasterId);
		}
		
		if(brands.containsKey(brandId)){
			brand = brands.get(brandId);
		}
	}
	
}
