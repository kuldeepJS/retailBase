/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMSchema.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary <Schema name="ARCH_PRD" description="">
 */
public class ORMSchema {
	
	private String name;
	private String description;
	private List<ORMTable> tables;
	
	public static ORMSchema getORMSchemaByName(ORMDatabase ormDatabase, String scmName){
		
		if(ormDatabase == null)
			return null;
		
		for(ORMSchema scm : ormDatabase.getSchemas()) {
			if(scm.getName().equals(scmName))
				return scm;
		}
		
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<ORMTable> getTables() {
		return tables;
	}
	
	public ORMSchema(Node schemaXmlNode){
		
		NamedNodeMap attMap = schemaXmlNode.getAttributes();
		
		name = attMap.getNamedItem("name").getNodeValue();
		description = attMap.getNamedItem("description").getNodeValue();
		
		LoggerHandle.println("@" + schemaXmlNode.getNodeName() + " Name -> " + name + " description -> " + description, 1, 1);
		
		tables = new ArrayList<ORMTable>();
		
		NodeList containedNodes = schemaXmlNode.getChildNodes();
		
		if(containedNodes != null && containedNodes.getLength() > 0){
			for(int tbIndex = 0; tbIndex < containedNodes.getLength(); tbIndex++){
				Node tmpNode = containedNodes.item(tbIndex);
				if(tmpNode.getNodeName().equals("Table"))
					tables.add(new ORMTable(tmpNode));
			}
		}
		
	}
	
}
