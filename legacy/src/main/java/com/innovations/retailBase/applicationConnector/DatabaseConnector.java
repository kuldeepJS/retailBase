/**
 * 
 */
package com.innovations.retailBase.applicationConnector;

import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.locks.ReadWriteLock;

import com.innovations.retailBase.locks.LockFactory;
import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 07-Nov-2014
 * @file DatabaseConnector.java
 * @project legacy
 * @package com.innovations.retailBase.applicationConnector
 * @summary 
 */
public class DatabaseConnector {
	
	private static DatabaseConnector localInstance;
	
	private Vector<Connection> availableConnections;
	private Vector<Connection> underUseConnections;
	private int maxNumberOfConnections;
	private boolean consumable;
	private Exception encounteredException;
	private ReadWriteLock dbLock;
	private int maxRetryCount;
	private long waitLimit;
	private static DatabaseProperties dbProperties;
	
	public String getConnectedSchema(){
		return dbProperties.getSchema();
	}
	
	public static DatabaseConnector getLocalInstance() {
		return localInstance;
	}

	public int getMaxNumberOfConnections() {
		return maxNumberOfConnections;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public Exception getEncounteredException() {
		return encounteredException;
	}

	public static synchronized DatabaseConnector getDatabaseConnector(){
		
		if(localInstance == null){
			localInstance = new DatabaseConnector();
		}
		
		return localInstance;
	}
	
	/*
	 * Represents a connection pool
	 * This should be used to provide connection pool
	 * for all the modules
	 */
	private DatabaseConnector(){
		//This should be configurable at latter stages
		maxNumberOfConnections = 2;
		
		//Loading connection properties
		dbProperties = new DatabaseProperties();
		dbProperties.driverName = "com.mysql.jdbc.Driver";
		dbProperties.driverConnectorSuffix = "jdbc:mysql";
		dbProperties.serverAddress = "50.62.209.107";
		dbProperties.serverPort = 3306;
		dbProperties.schema = "ARCH_PRD";
		dbProperties.userName="archprd";
		dbProperties.password = "~Royals88~";
		
		//Load the available list of connections
		availableConnections = new Vector<Connection>(maxNumberOfConnections);
		underUseConnections = new Vector<Connection>(maxNumberOfConnections);
		
		//Loading the connections into the available list of connections
		try {
			for(int conIndex=0; conIndex<maxNumberOfConnections; conIndex++){
				availableConnections.add(new Connection(dbProperties));
			}
			
			consumable = true;
			dbLock = LockFactory.getRetailsDBConnectionLock(true);
			waitLimit = 1000;
			maxRetryCount = 5;
			
		} catch (ClassNotFoundException e) {
			//Error loading the driver
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			encounteredException = e;
		} catch (SQLException e) {
			//Error connecting database
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			encounteredException = e;
		}
	}
	
	public Connection getRetailConnection(){
		try{
			int localCounter = maxRetryCount;
			
			while(availableConnections.size() == 0 && localCounter>0) {
				dbLock.wait(waitLimit);
				localCounter--;
			}

			if(localCounter == 0)
				throw new IllegalStateException("Connection pool sinked :( Some one not returning...");
			
			Connection tmpCon = availableConnections.remove(0);
			try {
				if(tmpCon.innerConnection.isClosed()){
					tmpCon = new Connection(dbProperties);
				}
			} catch (SQLException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			} catch (ClassNotFoundException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
			underUseConnections.add(tmpCon);
			return tmpCon;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void releaseRetailConnection(Connection trgCon){
		try {
			if(!trgCon.innerConnection.isClosed())
				trgCon.innerConnection.close();
		} catch (SQLException e) {
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
		}
		underUseConnections.remove(trgCon);
		availableConnections.add(trgCon);
		try{
			dbLock.notifyAll();
		} catch(Exception ex){
			LoggerHandle.println("Error notifying listeneres...", 1, 1);
		}
	}
	
	/*
	 * Stores database connection properties
	 */
	class DatabaseProperties{
		private String serverAddress;
		private int serverPort;
		private String userName;
		private String password;
		private String schema;
		private String driverName;
		private String driverConnectorSuffix;
		
		public String getDriverConnectorSuffix() {
			return driverConnectorSuffix;
		}
		public String getDriverName() {
			return driverName;
		}
		public String getServerAddress() {
			return serverAddress;
		}
		public int getServerPort() {
			return serverPort;
		}
		public String getUserName() {
			return userName;
		}
		public String getPassword() {
			return password;
		}
		public String getSchema() {
			return schema;
		}
		
	}
	
}
