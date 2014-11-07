/**
 * 
 */
package com.innovations.retailBase.entities.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 07-Nov-2014
 * @file BrandInstance.java
 * @project legacy
 * @package com.innovations.retailBase.entities.legacy
 * @summary 
 */
public class BrandInstance {
	
	private static int LOCAL_BID = 0;
	
	private int localBrandId;
	private int brandId;
	private String name;
	private String label;
	private String description;
	private int active;
	private String imageURL;
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public static int getLOCAL_BID() {
		return LOCAL_BID;
	}
	public int getLocalBrandId() {
		return localBrandId;
	}
	public int getBrandId() {
		return brandId;
	}
	
	public BrandInstance(){
		
	}
	
	public BrandInstance(ResultSet rdPointer) throws SQLException{
		brandId = rdPointer.getInt("BRANDID");
		name = rdPointer.getString("NAME");
		label = rdPointer.getString("LABEL");
		description = rdPointer.getString("DESCRIPTION");
		active = rdPointer.getInt("IS_ACTIVE");
		imageURL = rdPointer.getString("IMAGEURL");
	}
	
}
