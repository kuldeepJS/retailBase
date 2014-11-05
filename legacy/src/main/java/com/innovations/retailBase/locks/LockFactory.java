/**
 * 
 */
package com.innovations.retailBase.locks;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file LockFactory.java
 * @project legacy
 * @package com.innovations.retailBase.locks
 * @summary Provides locks for different entities
 */
public class LockFactory {
	
	public static LockBase getHierarchyLock(){
		return new LockBase();
	}
	
	public static LockBase getProductMasterLock(){
		return new LockBase();
	}
	
	public static LockBase getProductInstanceLock(){
		return new LockBase();
	}
	
}
