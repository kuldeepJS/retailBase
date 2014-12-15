package com.innovations.retailBase.entities.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Admin {
	
	private int adminId;
	private String emailId;
	private AdminPermissions permissionSet;
	private Date lastLoggedOn;

	public Admin(ResultSet rs) throws SQLException{
		adminId = rs.getInt("ADMIN_ID");
		emailId = rs.getString("EMAIL_ID");
		permissionSet = AdminPermissions.getPermission(rs.getInt("PERMISSION_SET"));
		lastLoggedOn = new Date(rs.getTimestamp("LAST_LOGGED_ON").getTime());
	}
	
	public int getAdminId() {
		return adminId;
	}



	public String getEmailId() {
		return emailId;
	}



	public AdminPermissions getPermissionSet() {
		return permissionSet;
	}



	public Date getLastLoggedOn() {
		return lastLoggedOn;
	}

	public enum AdminPermissions{
		VIEW,
		VIEW_EDIT,
		VIEW_EDIT_CREATE,
		VIEW_EDIT_CREATE_DELETE,
		VIEW_CREATE,
		VIEW_DELETE,
		VIEW_UPDATE_ADMIN,
		ALL,
		NONE;
		
		public static AdminPermissions getPermission(int permSet){
			switch(permSet){
			case 0:
				return VIEW;
			case 1:
				return VIEW_EDIT;
			case 2:
				return VIEW_EDIT_CREATE;
			case 3:
				return VIEW_EDIT_CREATE_DELETE;
			case 4:
				return VIEW_CREATE;
			case 5:
				return VIEW_DELETE;
			case 6:
				return VIEW_UPDATE_ADMIN;
			case 7:
				return ALL;
			default:
				return NONE;
			}
		}
		
		public static int getPermissionId(AdminPermissions permSet){
			switch(permSet){
			case VIEW:
				return 0;
			case VIEW_EDIT:
				return 1;
			case VIEW_EDIT_CREATE:
				return 2;
			case VIEW_EDIT_CREATE_DELETE:
				return 3;
			case VIEW_CREATE:
				return 4;
			case VIEW_DELETE:
				return 5;
			case VIEW_UPDATE_ADMIN:
				return 6;
			case ALL:
				return 7;
			default:
				return -1;
			}
		}
	}
	
}
