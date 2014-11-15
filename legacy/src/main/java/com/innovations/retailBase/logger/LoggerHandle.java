/**
 * 
 */
package com.innovations.retailBase.logger;

import java.io.PrintStream;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file LoggerHandle.java
 * @project legacy
 * @package com.innovations.retailBase.logger
 * @summary 
 */
public class LoggerHandle {
	
	private static PrintStream ps;
	
	static {
		ps = System.out;
	}
	
	public static void println(String message, int severity, int logLocation){
		ps.println("S" + severity + ": " + message + "\t-> " + logLocation);
	}
	
	public static PrintStream getLoggerPrintStream(int logLocation){
		return ps;
	}
	
}
