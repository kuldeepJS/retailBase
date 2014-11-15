/**
 * 
 */
package com.innovations.retailBase.utility.xmlParsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file LegacyParsers.java
 * @project legacy
 * @package com.innovations.retailBase.utility.xmlParsers
 * @summary Contains resources to parse xml based on document parsers
 */
public class LegacyParsers {
	
	private DocumentBuilderFactory builderFactory;
	private SchemaFactory schemaFactory;
	private DocumentBuilder builder;
	private String sourcePath;
	private boolean documentParseReady;
	private Exception recentException;
	private Schema xsd;
	private String language;
	private Validator schemaValidator;
	private boolean validated;
	private boolean valid;
	
	protected Document xmlDocumentHandle;
	
	public LegacyParsers(String xmlPath, String xsdPath){
		//Load the xml
		this(xmlPath);
		
		//If xml was loaded successfully
		if(documentParseReady){
			language = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
			try {
				xsd = schemaFactory.newSchema(new File(xsdPath));
				schemaValidator = xsd.newValidator();
				try {
					schemaValidator.validate(new DOMSource(xmlDocumentHandle));
					valid = true;
				} catch (IOException e) {
					e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
					recentException = e;
				}
				validated = true;
			} catch (SAXException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
				recentException = e;
			}
		}
	}
	
	public LegacyParsers(String xmlPath){
		builderFactory = DocumentBuilderFactory.newInstance();
		sourcePath = xmlPath;
		
		try {
			builder = builderFactory.newDocumentBuilder();
			xmlDocumentHandle = builder.parse(new FileInputStream(sourcePath));
			documentParseReady = true;
			LoggerHandle.println("XML @" + xmlPath + " loaded successfully!", 1, 1);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LoggerHandle.println("Error loading parser!", 1, 1);
			e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			recentException = e;
		}
		
	}

	public DocumentBuilderFactory getBuilderFactory() {
		return builderFactory;
	}

	public DocumentBuilder getBuilder() {
		return builder;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public boolean isDocumentParseReady() {
		return documentParseReady;
	}

	public Exception getRecentException() {
		return recentException;
	}

	public Document getXmlDocumentHandle() {
		return xmlDocumentHandle;
	}

	public SchemaFactory getSchemaFactory() {
		return schemaFactory;
	}

	public Schema getXsd() {
		return xsd;
	}

	public String getLanguage() {
		return language;
	}

	public Validator getSchemaValidator() {
		return schemaValidator;
	}

	public boolean isValidated() {
		return validated;
	}

	public boolean isValid() {
		return valid;
	}
	
	
}
