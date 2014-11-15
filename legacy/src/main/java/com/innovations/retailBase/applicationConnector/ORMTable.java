/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.innovations.retailBase.entities.legacy.LegacyEntity;
import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMTable.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary Contains ORM information for the table and its dependent class
 * <Table name="BRAND_MASTER" description="Store brand master data">
 */
public class ORMTable {
	
	private String name;
	private String description;
	private ORMAssociation association;
	private List<ORMColumnMap> columnMappings;
	
	public static ORMTable getORMTableByName(ORMSchema trgSchema, 
			String tbName) {
		
		if(trgSchema == null)
			return null;
		for(ORMTable ormTb : trgSchema.getTables()){
			if(ormTb.getName().equals(tbName))
				return ormTb;
		}
		
		return null;
	}
	
	public static ORMTable getORMTableByAssociatedClass(ORMSchema trgSchema, 
			Class<? extends LegacyEntity> associatedClass) {
		if(trgSchema == null)
			return null;
		for(ORMTable ormTb : trgSchema.getTables()){
			if((ormTb.getAssociation().getPackageName() + "."
					+ ormTb.getAssociation().getClassName()).equals(associatedClass.getName()))
				return ormTb;
		}
		
		return null;
	}
	
	public ORMTable(Node tableXMLNode){
		
		NamedNodeMap attMap = tableXMLNode.getAttributes();
		
		name = attMap.getNamedItem("name").getNodeValue();
		description = attMap.getNamedItem("description").getNodeValue();
		
		LoggerHandle.println("@ " + tableXMLNode.getNodeName() + " Name -> " + name + " description -> " + description, 1, 1);
		
		NodeList containedNodes = tableXMLNode.getChildNodes();
		
		if(containedNodes != null && containedNodes.getLength() > 0){
			Node associationNode = null;
			Node columnsNode = null;
			
			for(int nodeIndex = 0; nodeIndex < containedNodes.getLength(); nodeIndex++){
				Node tmpNode = containedNodes.item(nodeIndex);
				if(tmpNode.getNodeName().equals("Association"))
					associationNode = tmpNode;
				else if(tmpNode.getNodeName().equals("Columns"))
					columnsNode = tmpNode;
			}
			
			if(associationNode != null && columnsNode != null && associationNode.getNodeName().equals("Association")
					&& columnsNode.getNodeName().equals("Columns")){
				association = new ORMAssociation(associationNode);
				
				NodeList columnList = columnsNode.getChildNodes();
				
				if(columnList != null && columnList.getLength() > 0){
					columnMappings = new ArrayList<ORMColumnMap>();
					
					//Load the column instances
					for(int colIndex = 0; colIndex < columnList.getLength(); colIndex++){
						Node tmpNode =  columnList.item(colIndex);
						if(tmpNode.getNodeName().equals("Column"))
							columnMappings.add(new ORMColumnMap(association, tmpNode));
					}
				}
				
			} else {
				throw new org.w3c.dom.DOMException((short) 0, "Invalid elements or order of elements in table node @" + name + " !");
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ORMAssociation getAssociation() {
		return association;
	}
	
	public List<ORMColumnMap> getColumnMappings() {
		return columnMappings;
	}
	
}
