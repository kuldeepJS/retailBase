/**
 * 
 */
package com.innovations.retailBase.entities.connector;

import java.sql.SQLException;
import java.util.HashMap;

import com.innovations.retailBase.entities.legacy.HierarchyInstance;
import com.innovations.retailBase.logger.LoggerHandle;
import com.innovations.retailBase.utility.xmlParsers.ORMXMLParser;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 16-Nov-2014
 * @file HierarchyConnector.java
 * @project legacy
 * @package com.innovations.retailBase.entities.connector
 * @summary Connector for hierarchy 
 */
public class HierarchyConnector extends ConnectorBase {

	private HashMap<Integer, HierarchyInstance> hierarchyCache;
	private static HierarchyConnector connectorInstance;
	
	private HierarchyConnector(ORMXMLParser xmlParser) throws SQLException {
		
		super(xmlParser, HierarchyInstance.class, "level");
		
		hierarchyCache = new HashMap<Integer, HierarchyInstance>();
		while(rsPointer.next()) {
			try {
				HierarchyInstance hrInst = new HierarchyInstance(rsPointer, hierarchyCache);
				//linker.loadData(hrInst, rsPointer);
				hierarchyCache.put(hrInst.getHierarchyId(), hrInst);
				LoggerHandle.println("Hierarchy -> " + hrInst.toString(), 1, 1);
			} catch (SecurityException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
		//Release the connection
		releaseConnection();
	}

	public synchronized static HashMap<Integer, HierarchyInstance> getHierarchyCache() throws SQLException {
		if(connectorInstance == null){
			connectorInstance = new HierarchyConnector(ORMXMLParser.getPrdArchParser());
			for(HierarchyInstance instance : connectorInstance.hierarchyCache.values()){
				if(instance.getParentHierarchy() != null){
					instance.getParentHierarchy().addChild(instance);
				}
			}
		}
		return connectorInstance.hierarchyCache;
	}
	
}
