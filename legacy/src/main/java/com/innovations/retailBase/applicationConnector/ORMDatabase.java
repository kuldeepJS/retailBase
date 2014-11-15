/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMDatabase.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary <Database address="50.62.209.107" port="3306">
 */
public class ORMDatabase {
	
	private String address;
	private int port;
	private List<ORMSchema> schemas;
	
	public ORMDatabase(Node databaseXMLNode){
		Element databaseElement = (Element)databaseXMLNode;
		address = databaseElement.getAttribute("address");
		port = Integer.parseInt(databaseElement.getAttribute("port"));
		
		LoggerHandle.println("Connecting using -> " + address + " @ " + port, 1, 1);
		
		NodeList ormElements = databaseXMLNode.getChildNodes();
		if(ormElements != null && ormElements.getLength() > 0){
			schemas = new ArrayList<ORMSchema>();
			
			for(int scmIndex = 0; scmIndex<ormElements.getLength(); scmIndex++){
				Node tmpNode = ormElements.item(scmIndex);
				if(tmpNode.getNodeName().equals("Schema"))
					schemas.add(new ORMSchema(tmpNode));
			}
		}
		
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public List<ORMSchema> getSchemas() {
		return schemas;
	}
	
	
}
