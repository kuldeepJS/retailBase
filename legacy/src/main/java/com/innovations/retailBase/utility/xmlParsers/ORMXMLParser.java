/**
 * 
 */
package com.innovations.retailBase.utility.xmlParsers;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;

import com.innovations.retailBase.applicationConnector.ORMDatabase;

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
	
}
