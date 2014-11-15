/**
 * 
 */
package com.innovations.retailBase.unitTests.applicationConnector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.innovations.retailBase.applicationConnector.DatabaseConnector;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file DBConnectionTest.java
 * @project legacy
 * @package com.innovations.retailBase.unitTests.applicationConnector
 * @summary 
 */
public class DBConnectionTest {

	/**
	 * @throws java.lang.Exception
	 * @return void
	 * @see void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//In future we can change the name of access parameters here
	}

	/**
	 * @throws java.lang.Exception
	 * @return void
	 * @see void
	 */
	@Before
	public void setUp() throws Exception {
		//In future we can check the connection for various thread level cases
	}

	/**
	 * Test method for {@link com.innovations.retailBase.applicationConnector.DatabaseConnector#getDatabaseConnector()}.
	 */
	@Test
	public final void testGetDatabaseConnector() {
		assertNotNull(DatabaseConnector.getDatabaseConnector());
		assertTrue(DatabaseConnector.getLocalInstance().isConsumable());
	}
	
}
