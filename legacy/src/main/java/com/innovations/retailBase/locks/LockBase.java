/**
 * 
 */
package com.innovations.retailBase.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 03-Nov-2014
 * @file LockBase.java
 * @project legacy
 * @package com.innovations.retailBase.locks
 * @summary 
 */
public class LockBase implements ReadWriteLock {

	private ReadWriteLock lockInstance;
	private boolean forWrite;
	
	public LockBase(ReadWriteLock lockInstance, boolean forWrite){
		this.lockInstance = lockInstance;
		this.forWrite = forWrite;
	}
	
	@Override
	public Lock readLock() {
		
		Lock readLock = lockInstance.readLock();
		
		while(!readLock.tryLock())
			readLock.lock();
			
		return readLock;
	}

	@Override
	public Lock writeLock() {
		
		if(!forWrite)
			return null;
		
		Lock writeLock = lockInstance.writeLock();
		while(!writeLock.tryLock())
			writeLock.lock();
		
		return writeLock;
	}

	
	
}
