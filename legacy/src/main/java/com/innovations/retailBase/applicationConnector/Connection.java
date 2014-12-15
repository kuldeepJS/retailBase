/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 07-Nov-2014
 * @file Connection.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary 
 */
public class Connection {
	
	private static int connectionCount = 0;
	
	java.sql.Connection innerConnection;
	
	protected Connection(DatabaseConnector.DatabaseProperties dbProperties) throws ClassNotFoundException, SQLException{
		LoggerHandle.println("Loading driver for " + dbProperties.getDriverName() + " @ index " + (++connectionCount), 1, 1);
		Class.forName(dbProperties.getDriverName());
		
		innerConnection = DriverManager.getConnection(
					dbProperties.getDriverConnectorSuffix()+"://" + 
					dbProperties.getServerAddress() + "/" + 
					dbProperties.getSchema() + 
					"?user=" + dbProperties.getUserName() + 
					"&password=" + dbProperties.getPassword());
		
	}
	
	public Statement createStatement() throws SQLException{
		return innerConnection.createStatement();
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException{
		return innerConnection.prepareStatement(sql);
	}
	
	
}
