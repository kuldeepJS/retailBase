/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMAssociation.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary <Association 
				package="com.innovations.retailBase.entities.legacy" 
				name="BrandInstance"
				loadingType="1"/>
 */
public class ORMAssociation {
	
	private String packageName;
	private String className;
	private int loadingType;
	
	public ORMAssociation(Node associationXMLNode){
		NamedNodeMap attMap = associationXMLNode.getAttributes();
		packageName = attMap.getNamedItem("package").getNodeValue();
		className = attMap.getNamedItem("name").getNodeValue();
		loadingType = Integer.parseInt(attMap.getNamedItem("loadingType").getNodeValue());
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public String getClassName() {
		return className;
	}
	
	public int getLoadingType() {
		return loadingType;
	}
	
	
}
