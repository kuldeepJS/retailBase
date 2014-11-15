/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMColumnMap.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary <Column name="BRANDID" property="brandId" dbType="INT" jType="int"/> 
 */
public class ORMColumnMap {
	
	private String columnName;
	private String property;
	private String dbType;
	private String jType;
	
	ORMAssociation association;
	
	public ORMColumnMap(ORMAssociation association, Node columnXMLNode){
		this.association = association;
		NamedNodeMap attMap = columnXMLNode.getAttributes();
		columnName=  attMap.getNamedItem("name").getNodeValue();
		property = attMap.getNamedItem("property").getNodeValue();
		dbType = attMap.getNamedItem("dbType").getNodeValue();
		jType = attMap.getNamedItem("jType").getNodeValue();
	}

	public String getColumnName() {
		return columnName;
	}

	public String getProperty() {
		return property;
	}

	public String getDbType() {
		return dbType;
	}

	public String getjType() {
		return jType;
	}

	public ORMAssociation getAssociation() {
		return association;
	}
	
}
