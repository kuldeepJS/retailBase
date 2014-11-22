/**
 * 
 */
package com.innovations.retailBase.entities.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.innovations.retailBase.locks.LockBase;
import com.innovations.retailBase.locks.LockFactory;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file HierarchyInstance.java
 * @project legacy
 * @package com.innovations.retailBase.entities.legacy
 * @summary This class stores the information based on 
 * the table HIERARCHY_MASTER
 */
public class HierarchyInstance implements LegacyEntity {
	
	private static int LOCAL_HID = 0;
	
	private int localHierarchId;
	private int hierarchyId;
	private HierarchyInstance parentHierarchy;
	private List<HierarchyInstance> children;
	private int level;
	private int status;
	private String name;
	private String label;
	private String description;
	private boolean parentFound;
	private boolean virtualMode;
	
	
	public static int getLOCAL_HID() {
		return LOCAL_HID;
	}

	public boolean isVirtualMode() {
		return virtualMode;
	}

	public int getLocalHierarchId() {
		return localHierarchId;
	}

	public List<HierarchyInstance> getChildren() {
		return children;
	}
	
	public synchronized void addChild(HierarchyInstance child) {
		if(this.children == null)
			this.children = new ArrayList<HierarchyInstance>();
		this.children.add(child);
	}
	
	public boolean isParentFound() {
		return parentFound;
	}
	
	public void setParentFound(boolean parentFound) {
		this.parentFound = parentFound;
	}
	
	public int getHierarchyId() {
		return hierarchyId;
	}
	
	public void setHierarchyId(int hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	
	public HierarchyInstance getParentHierarchy() {
		return parentHierarchy;
	}
	
	public void setParentHierarchy(HierarchyInstance parentHierarchy) {
		this.parentHierarchy = parentHierarchy;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public HierarchyInstance(){
		LockBase base = LockFactory.getHierarchyLock();
		base.lock();
		localHierarchId = ++LOCAL_HID;
		virtualMode = true;
		base.unlock();
	}
	
	public void linkToHierarchyInstance(HierarchyInstance hierarchyInstance) throws IllegalAccessException{
		if(virtualMode){
			if(hierarchyInstance != null){
				hierarchyInstance.addChild(this);
				parentHierarchy = hierarchyInstance;
			}
		} else {
			throw new IllegalAccessException("State not feasible since hierarchy is not in creational mode!");
		}
	}
	
	public HierarchyInstance(ResultSet rdPointer, 
			HashMap<Integer, HierarchyInstance> hierarchyInstances) throws SQLException{
		hierarchyId = rdPointer.getInt("MasterId");
		level = rdPointer.getInt("level");
		status = rdPointer.getInt("Status");
		name = rdPointer.getString("Name");
		label = rdPointer.getString("Label");
		description = rdPointer.getString("Description");
		int tmpParentId = rdPointer.getInt("ParentId");
		if(hierarchyInstances.containsKey(tmpParentId)){
			parentFound = true;
			parentHierarchy = hierarchyInstances.get(tmpParentId);
		} else {
			//If this parent found flag is reset, this instance will applicable after
			//all the records are loaded for scanning of parent
			parentFound = false;
		}
	}
	
}
