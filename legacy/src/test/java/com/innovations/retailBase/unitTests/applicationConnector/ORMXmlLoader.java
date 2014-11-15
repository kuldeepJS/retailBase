/**
 * 
 */
package com.innovations.retailBase.unitTests.applicationConnector;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.innovations.retailBase.applicationConnector.ORMDatabase;
import com.innovations.retailBase.logger.LoggerHandle;
import com.innovations.retailBase.utility.xmlParsers.ORMXMLParser;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMXmlLoader.java
 * @project legacy
 * @package com.innovations.retailBase.unitTests.applicationConnector
 * @summary 
 */
public class ORMXmlLoader {

	private static String PWD;
	private String PWF;
	
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
        PWF = PWD + ormMappingXmlRelFolder + ormMappingXml;
	}

	/**
	 * Test method for {@link com.innovations.retailBase.utility.xmlParsers.ORMXMLParser#getDatabaseInfo()}.
	 */
	@Test
	public final void testGetDatabaseInfo() {
		
        try {
        	ORMDatabase ormMapDatabase;
	        ORMXMLParser parser;
			parser = new ORMXMLParser(PWF);
			ormMapDatabase = parser.getDatabaseInfo();
			LoggerHandle.println("XML loaded successfully!", 0, 1);
			assertNotNull(ormMapDatabase);
		} catch (ParserConfigurationException e) {
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			fail("Exception loading xml @ " + PWF);
		}
	}

}
