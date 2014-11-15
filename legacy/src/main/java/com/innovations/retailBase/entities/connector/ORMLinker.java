/**
 * 
 */
package com.innovations.retailBase.entities.connector;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.innovations.retailBase.applicationConnector.ORMColumnMap;
import com.innovations.retailBase.applicationConnector.ORMTable;
import com.innovations.retailBase.entities.legacy.LegacyEntity;
import com.innovations.retailBase.logger.LoggerHandle;

/**Copyright 2014 Innovations
 * @author Kuldeep Sharma
 * @created 15-Nov-2014
 * @file ORMLinker.java
 * @project legacy
 * @package com.innovations.retailBase.entities.connector
 * @summary 
 */
public class ORMLinker {
	
	private ORMTable tbMapping;
	private Class<? extends LegacyEntity> classHandle;
	private ResultSetMetaData rsMetadata;
	
	public ORMLinker(ORMTable tbMapping){
		this.tbMapping = tbMapping;
	}
	
	@SuppressWarnings("unchecked")
	public Object loadData(Object trgInstance, ResultSet trgDataRow) throws NoSuchFieldException, SecurityException, SQLException{
		List<ORMColumnMap> columnsMap = tbMapping.getColumnMappings();
		classHandle = (Class<? extends LegacyEntity>) trgInstance.getClass();
		
		if(rsMetadata == null) {
			rsMetadata = trgDataRow.getMetaData();
		}
		
		for(int ormColIndex = 0; ormColIndex < columnsMap.size(); ormColIndex++){
			ORMColumnMap tmpCol = columnsMap.get(ormColIndex);
			String propSuffix = tmpCol.getProperty();
			Field fldHandle = classHandle.getDeclaredField(propSuffix);
			
			//Set private or protected accessibility to true
			fldHandle.setAccessible(true);
			try {
				setValue(fldHandle, trgDataRow, tmpCol, trgInstance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
		return trgInstance;
	}
	
	private void setValue(Field fldHandle, 
			ResultSet rsPointer, 
			ORMColumnMap colMap,
			Object instance) 
					throws 	SQLException, 
							IllegalArgumentException, 
							IllegalAccessException{
		int colType = rsMetadata.getColumnType(rsPointer.findColumn(colMap.getColumnName()));
		
		switch(colType){
		case java.sql.Types.INTEGER:
			loadIntValue(fldHandle, rsPointer, colMap, instance);
			break;
		case java.sql.Types.VARCHAR:
			loadStringValue(fldHandle, rsPointer, colMap, instance);
			break;
		case java.sql.Types.BINARY:
			loadBooleanValue(fldHandle, rsPointer, colMap, instance);
			break;
		case java.sql.Types.FLOAT:
			loadFloatValue(fldHandle, rsPointer, colMap, instance);
			break;
		case java.sql.Types.TIMESTAMP:
			break;
		}
	}
	
	private void loadIntValue(Field fldHandle, 
			ResultSet rsPointer, 
			ORMColumnMap colMap,
			Object instance) throws IllegalArgumentException, IllegalAccessException, SQLException{
		fldHandle.setInt(instance, rsPointer.getInt(colMap.getColumnName()));
	}
	
	private void loadStringValue(Field fldHandle, 
			ResultSet rsPointer, 
			ORMColumnMap colMap,
			Object instance) throws IllegalArgumentException, IllegalAccessException, SQLException{
		fldHandle.set(instance, rsPointer.getString(colMap.getColumnName()));
	}
	
	private void loadBooleanValue(Field fldHandle, 
			ResultSet rsPointer, 
			ORMColumnMap colMap,
			Object instance) throws IllegalArgumentException, IllegalAccessException, SQLException{
		fldHandle.setBoolean(instance, rsPointer.getBoolean(colMap.getColumnName()));
	}
	
	private void loadFloatValue(Field fldHandle, 
			ResultSet rsPointer, 
			ORMColumnMap colMap,
			Object instance) throws IllegalArgumentException, IllegalAccessException, SQLException{
		fldHandle.setFloat(instance, rsPointer.getFloat(colMap.getColumnName()));
	}
	
}
