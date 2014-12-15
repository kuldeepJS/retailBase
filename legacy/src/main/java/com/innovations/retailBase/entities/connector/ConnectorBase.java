/**
 * 
 */
package com.innovations.retailBase.entities.connector;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.innovations.retailBase.applicationConnector.Connection;
import com.innovations.retailBase.applicationConnector.DatabaseConnector;
import com.innovations.retailBase.applicationConnector.ORMColumnMap;
import com.innovations.retailBase.applicationConnector.ORMSchema;
import com.innovations.retailBase.applicationConnector.ORMTable;
import com.innovations.retailBase.entities.legacy.LegacyEntity;
import com.innovations.retailBase.utility.xmlParsers.ORMXMLParser;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 16-Nov-2014
 * @file ConnectorBase.java
 * @project legacy
 * @package com.innovations.retailBase.entities.connector
 * @summary Base class for all connector classes 
 */
public class ConnectorBase {
	
	private Connection retailConnector;
	private String selectSQL;
	private DatabaseConnector dbConnector;
	private ORMXMLParser xmlParser;
	
	protected ORMLinker linker;
	protected ORMTable ormMapping;
	protected ResultSet rsPointer;
	
	public ConnectorBase(ORMXMLParser xmlParser, Class<? extends LegacyEntity> targetClass) throws SQLException{
		this.xmlParser = xmlParser;
		
		ormMapping = ORMTable.getORMTableByAssociatedClass(
								ORMSchema.getORMSchemaByName(
										xmlParser.getDatabaseInfo(), 
										"ARCH_PRD"), 
										targetClass);
		dbConnector = DatabaseConnector.getDatabaseConnector();
		if(dbConnector.isConsumable()){
			retailConnector = dbConnector.getRetailConnection();
		} else {
			throw new InvalidParameterException("Unable to connect to database. Please contact administrator...");
		}
		
		rsPointer = prepareLoading();
		linker = new ORMLinker(ormMapping);
	}
	
	public ConnectorBase(ORMXMLParser xmlParser, Class<? extends LegacyEntity> targetClass, String orderByProperty) throws SQLException{
		this.xmlParser = xmlParser;
		
		ormMapping = ORMTable.getORMTableByAssociatedClass(
								ORMSchema.getORMSchemaByName(
										xmlParser.getDatabaseInfo(), 
										"ARCH_PRD"), 
										targetClass);
		dbConnector = DatabaseConnector.getDatabaseConnector();
		if(dbConnector.isConsumable()){
			retailConnector = dbConnector.getRetailConnection();
		} else {
			throw new InvalidParameterException("Unable to connect to database. Please contact administrator...");
		}
		
		ORMColumnMap orderByColumn = null;
		for(ORMColumnMap col : ormMapping.getColumnMappings()){
			if(col.getProperty().equals(orderByProperty)){
				orderByColumn = col;
				break;
			}
		}
		
		if(orderByColumn != null)
			rsPointer = prepareLoading(orderByColumn);
		else
			rsPointer = prepareLoading();
		
		linker = new ORMLinker(ormMapping);
	}
	
	private ResultSet prepareLoading() throws SQLException{
		selectSQL = "SELECT * FROM " + dbConnector.getConnectedSchema() + "." + ormMapping.getName() + ";";
		Statement stmt = retailConnector.createStatement();
		
		if(stmt.execute(selectSQL))
			return stmt.getResultSet();
		
		return null;
	}
	
	private ResultSet prepareLoading(ORMColumnMap orderBy) throws SQLException{
		selectSQL = "SELECT * FROM " + dbConnector.getConnectedSchema() + "." + ormMapping.getName() + " ORDER BY " + orderBy.getColumnName() + ";";
		Statement stmt = retailConnector.createStatement();
		
		if(stmt.execute(selectSQL))
			return stmt.getResultSet();
		
		return null;
	}

	public Connection getRetailConnector() {
		return retailConnector;
	}

	public String getSelectSQL() {
		return selectSQL;
	}

	public DatabaseConnector getDbConnector() {
		return dbConnector;
	}

	public ORMXMLParser getXmlParser() {
		return xmlParser;
	}

	public ORMLinker getLinker() {
		return linker;
	}

	public ORMTable getOrmMapping() {
		return ormMapping;
	}

	public ResultSet getRsPointer() {
		return rsPointer;
	}
	
	protected void releaseConnection(){
		if(retailConnector != null)
			dbConnector.releaseRetailConnection(retailConnector);
	}
	
	
}
