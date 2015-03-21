/**
 * 
 */
package com.innovations.retailBase.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file LockFactory.java
 * @project legacy
 * @package com.innovations.retailBase.locks
 * @summary Provides locks for different entities
 */
public class LockFactory {
	
	private static ReadWriteLock brandLock;
	private static ReadWriteLock hierarchyLock;
	private static ReadWriteLock productMasterLock;
	private static ReadWriteLock productInstanceLock;
	private static ReadWriteLock priceInstanceLock;
	private static ReadWriteLock dbConnectionLock;
	
	static {
		brandLock = new ReentrantReadWriteLock();
	}
	
	public static ReadWriteLock getHierarchyLock(boolean forWrite){
		return hierarchyLock;
	}
	
	public static ReadWriteLock getProductMasterLock(boolean forWrite){
		return productMasterLock;
	}
	
	public static ReadWriteLock getProductInstanceLock(boolean forWrite){
		return productInstanceLock;
	}
	
	public static ReadWriteLock getBrandInstanceLock(boolean forWrite){
		return new LockBase(brandLock, forWrite);
	}
	
	public static ReadWriteLock getPriceInstanceLock(boolean forWrite){
		return priceInstanceLock;
	}
	
	public static ReadWriteLock getRetailsDBConnectionLock(boolean forWrite){
		return dbConnectionLock;
	}
	
}
