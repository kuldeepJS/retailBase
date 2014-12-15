/**
 * 
 */
package com.innovations.retailBase.entities.connector;

import java.sql.SQLException;
import java.util.HashMap;

import com.innovations.retailBase.entities.legacy.StoreOperation;
import com.innovations.retailBase.logger.LoggerHandle;
import com.innovations.retailBase.utility.xmlParsers.ORMXMLParser;

/**Copyright 2014 Innovations
 * @author sony
 * @created 14-Dec-2014
 * @file OperationConnector.java
 * @project legacy
 * @package com.innovations.retailBase.entities.connector
 * @summary Connector for store operations
 */
public class OperationConnector extends ConnectorBase {
	private HashMap<Integer, StoreOperation> operationCache;
	private static OperationConnector connectorInstance;
	
	private OperationConnector(ORMXMLParser xmlParser) throws SQLException {
		
		super(xmlParser, StoreOperation.class, "level");
		
		operationCache = new HashMap<Integer, StoreOperation>();
		while(rsPointer.next()) {
			try {
				StoreOperation hrInst = new StoreOperation(rsPointer, operationCache);
				//linker.loadData(hrInst, rsPointer);
				operationCache.put(hrInst.getHierarchyId(), hrInst);
				LoggerHandle.println("Operation -> " + hrInst.toString(), 1, 1);
			} catch (SecurityException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
		//Release the connection
		releaseConnection();
	}

	public synchronized static HashMap<Integer, StoreOperation> getHierarchyCache() throws SQLException {
		if(connectorInstance == null){
			connectorInstance = new OperationConnector(ORMXMLParser.getPrdArchParser());
			for(StoreOperation instance : connectorInstance.operationCache.values()){
				if(instance.getParentHierarchy() != null){
					instance.getParentHierarchy().addChild(instance);
				}
			}
		}
		return connectorInstance.operationCache;
	}
}
