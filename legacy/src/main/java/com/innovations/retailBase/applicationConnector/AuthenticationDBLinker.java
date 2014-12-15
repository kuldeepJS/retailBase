package com.innovations.retailBase.applicationConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.innovations.retailBase.entities.legacy.Admin;
import com.innovations.retailBase.logger.LoggerHandle;

public class AuthenticationDBLinker {
	
	private boolean forAdmin;
	private DatabaseConnector connector;
	private Connection retailConnection;
	
	public AuthenticationDBLinker(boolean forAdmin){
		this.forAdmin = forAdmin;
		
		connector = DatabaseConnector.getLocalInstance();
		if(connector == null){
			connector = DatabaseConnector.getDatabaseConnector();
		}
		
	}
	
	public Admin getDetails(String userName, String password){
		
		if(!forAdmin)
			return null;
		
		//Retrieve connection
		retailConnection = connector.getRetailConnection();
		
		String verificationSQL = "SELECT * FROM ADMIN_TABLE WHERE EMAIL_ID=? AND PASSWORD=?";
		
		PreparedStatement pstmt;
		try {
			pstmt = retailConnection.getPreparedStatement(verificationSQL);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				return new Admin(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
		} finally {
			//Release connection
			connector.releaseRetailConnection(retailConnection);
		}
		
		return null;
	}
	
	

}
