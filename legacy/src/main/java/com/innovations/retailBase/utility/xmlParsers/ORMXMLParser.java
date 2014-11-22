/**
 * 
 */
package com.innovations.retailBase.utility.xmlParsers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;

import com.innovations.retailBase.applicationConnector.ORMDatabase;
import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMXMLParser.java
 * @project legacy
 * @package com.innovations.retailBase.utility.xmlParsers
 * @summary 
 */
public class ORMXMLParser extends LegacyParsers {

	private ORMDatabase databaseInfo;
	private static String prdArchXMLPath;
	private static ORMXMLParser prdArchParser;
	
	static {
		try {
			String PWD = new java.io.File( "." ).getCanonicalPath();
			
			//Path for Product arch schema xml path
			String ormMappingXmlRelFolder = "\\target\\classes\\com\\innovations\\retailBase\\applicationConnector\\";
	        String ormMappingXml = "ClassSchemaMapping.xml";
	        prdArchXMLPath = PWD + ormMappingXmlRelFolder + ormMappingXml;
			prdArchParser = new ORMXMLParser(prdArchXMLPath);
			
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
		}
	}
	
	public ORMDatabase getDatabaseInfo() {
		return databaseInfo;
	}
	
	public ORMDatabase getDatabaseInfoAfterRefresh(){
		processXMLDocument();
		return databaseInfo;
	}

	public ORMXMLParser(String path) throws ParserConfigurationException {
		//Calling the super class constructor
		super(path);
		
		if(isDocumentParseReady())
			processXMLDocument();
	}
	
	private void processXMLDocument(){
		
		NodeList ormElements = xmlDocumentHandle.getElementsByTagName("Database");
		
		if(ormElements != null && ormElements.getLength() > 0)
			databaseInfo = new ORMDatabase(ormElements.item(0));
	}

	public static String getPrdArchXMLPath() {
		return prdArchXMLPath;
	}

	public static ORMXMLParser getPrdArchParser() {
		return prdArchParser;
	}
	
	
}
