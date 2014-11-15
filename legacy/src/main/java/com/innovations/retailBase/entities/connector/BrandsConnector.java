/**
 * 
 */
package com.innovations.retailBase.entities.connector;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.innovations.retailBase.applicationConnector.Connection;
import com.innovations.retailBase.applicationConnector.DatabaseConnector;
import com.innovations.retailBase.applicationConnector.ORMSchema;
import com.innovations.retailBase.applicationConnector.ORMTable;
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
public class BrandsConnector {
	
	private Connection retailConnector;
	private List<BrandInstance> brandCache;
	private String selectSQL;
	private ORMTable ormMapping;
	private DatabaseConnector dbConnector;
	
	private ORMXMLParser xmlParser;
	
	public ORMXMLParser getXmlParser() {
		return xmlParser;
	}

	public BrandsConnector(ORMXMLParser xmlParser) throws SQLException {
		
		this.xmlParser = xmlParser;
		
		ormMapping = ORMTable.getORMTableByAssociatedClass(
								ORMSchema.getORMSchemaByName(
										xmlParser.getDatabaseInfo(), 
										"ARCH_PRD"), 
								BrandInstance.class);
		dbConnector = DatabaseConnector.getDatabaseConnector();
		if(dbConnector.isConsumable()){
			retailConnector = dbConnector.getRetailConnection();
		} else {
			throw new InvalidParameterException("Unable to connect to database. Please contact administrator...");
		}
		
		ResultSet brandsData = prepareLoading();
		brandCache = new ArrayList<BrandInstance>();
		ORMLinker linker = new ORMLinker(ormMapping);
		while(brandsData.next()) {
			try {
				BrandInstance brInst = new BrandInstance();
				linker.loadData(brInst, brandsData);
				brandCache.add(brInst);
				LoggerHandle.println("Brand -> " + brInst.toString(), 1, 1);
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
	}

	private ResultSet prepareLoading() throws SQLException{
		selectSQL = "SELECT * FROM " + dbConnector.getConnectedSchema() + "." + ormMapping.getName() + ";";
		Statement stmt = retailConnector.createStatement();
		
		if(stmt.execute(selectSQL))
			return stmt.getResultSet();
		
		return null;
	}

	public List<BrandInstance> getBrandCache() {
		return brandCache;
	}
	
	public boolean addBrand(BrandInstance newBrand){
		if(newBrand.isVirtualInstance()){
			brandCache.add(newBrand);
		} else {
			return false;
		}
		
		return true;
	}
	
	public void removeBrand(BrandInstance trgBrand){
		brandCache.remove(trgBrand);
	}
	
	public synchronized boolean persistBrands(){
		
		//Refresh the brands cache if updated successfully
		return false;
	}
}
