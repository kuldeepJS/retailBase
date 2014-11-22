/**
 * 
 */
package com.innovations.retailBase.unitTests.entities.connector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.innovations.retailBase.entities.connector.BrandsConnector;
import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file BrandConnectorTest.java
 * @project legacy
 * @package com.innovations.retailBase.unitTests.entities.connector
 * @summary 
 */
public class BrandConnectorTest {

	private static String PWD;
	
	/**
	 * @throws java.lang.Exception
	 * @return void
	 * @see void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			PWD = new java.io.File( "." ).getCanonicalPath();
	        LoggerHandle.println(PWD, 1, 1);
		} catch (IOException e) {
			LoggerHandle.println("Unable to access current directory...", 1, 1);
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
		}
	}

	/**
	 * @throws java.lang.Exception
	 * @return void
	 * @see void
	 */
	@Before
	public void setUp() throws Exception {
		String ormMappingXmlRelFolder = "\\target\\classes\\com\\innovations\\retailBase\\applicationConnector\\";
        String ormMappingXml = "ClassSchemaMapping.xml";
        String path = PWD + ormMappingXmlRelFolder + ormMappingXml;
        LoggerHandle.println("Expected xml path: " + path, 1, 1);
	}

	/**
	 * Test method for {@link com.innovations.retailBase.entities.connector.BrandsConnector#getBrandCache()}.
	 */
	@Test
	public final void testGetBrandCache() {
		try {
			assertNotNull(BrandsConnector.getBrandCache());
		} catch (SQLException e) {
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			fail("Error in loading brands...");
		}
	}

}
