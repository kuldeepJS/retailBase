/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import java.sql.DriverManager;
import java.sql.SQLException;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 07-Nov-2014
 * @file Connection.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary 
 */
public class Connection {
	
	java.sql.Connection innerConnection;
	
	protected Connection(DatabaseConnector.DatabaseProperties dbProperties) throws ClassNotFoundException, SQLException{
		Class.forName(dbProperties.getDriverName());
		
		innerConnection = DriverManager.getConnection(
					dbProperties.getDriverConnectorSuffix()+"://" + 
					dbProperties.getServerAddress() + "/" + 
					dbProperties.getSchema() + 
					"?user=" + dbProperties.getUserName() + 
					"&password=" + dbProperties.getPassword());
		
	}
	
	
}
