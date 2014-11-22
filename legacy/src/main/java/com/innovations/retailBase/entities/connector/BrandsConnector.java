/**
 * 
 */
package com.innovations.retailBase.entities.connector;

import java.sql.SQLException;
import java.util.HashMap;

import com.innovations.retailBase.entities.legacy.BrandInstance;
import com.innovations.retailBase.logger.LoggerHandle;
import com.innovations.retailBase.utility.xmlParsers.ORMXMLParser;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 07-Nov-2014
 * @file BrandsConnector.java
 * @project legacy
 * @package com.innovations.retailBase.entities.connector
 * @summary 
 */
public class BrandsConnector extends ConnectorBase {
	
	private HashMap<Integer, BrandInstance> brandCache;
	private static BrandsConnector connectorInstance;

	private BrandsConnector(ORMXMLParser xmlParser) throws SQLException {
		
		super(xmlParser, BrandInstance.class);
		
		brandCache = new HashMap<Integer,BrandInstance>();
		while(rsPointer.next()) {
			try {
				BrandInstance brInst = new BrandInstance();
				linker.loadData(brInst, rsPointer);
				brandCache.put(brInst.getBrandId(), brInst);
				LoggerHandle.println("Brand -> " + brInst.toString(), 1, 1);
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
	}

	public synchronized static HashMap<Integer, BrandInstance> getBrandCache() throws SQLException {
		if(connectorInstance == null)
			connectorInstance = new BrandsConnector(ORMXMLParser.getPrdArchParser());
		return connectorInstance.brandCache;
	}
	
	public void removeBrand(BrandInstance trgBrand){
		brandCache.remove(trgBrand);
	}
	
	public synchronized boolean persistBrands(){
		
		//Refresh the brands cache if updated successfully
		return false;
	}
}
