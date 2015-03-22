package com.innovations.retailBase.entities.connector;

import java.sql.SQLException;
import java.util.HashMap;

import com.innovations.retailBase.entities.legacy.BrandInstance;
import com.innovations.retailBase.entities.legacy.HierarchyInstance;
import com.innovations.retailBase.entities.legacy.ProductSkeleton;
import com.innovations.retailBase.utility.xmlParsers.ORMXMLParser;

public class ProductMasterConnector extends ConnectorBase {

	private HashMap<Integer, ProductSkeleton> productCache;
	private static ProductMasterConnector connectorInstance;
	
	private ProductMasterConnector(ORMXMLParser xmlParser,
			HashMap<Integer, HierarchyInstance> hierarchyCache,
			HashMap<Integer, BrandInstance> brandCache) throws SQLException {
		
		super(xmlParser, ProductSkeleton.class);
		
		productCache= new HashMap<Integer, ProductSkeleton>();
		while(rsPointer.next()) {
			ProductSkeleton prSkl = new ProductSkeleton(rsPointer, hierarchyCache, brandCache, ormMapping);
			productCache.put(prSkl.getProductId(), prSkl);
		}
	}

	public synchronized static HashMap<Integer, ProductSkeleton> getProductCache() throws SQLException {
		if(connectorInstance == null)
			connectorInstance = new ProductMasterConnector(ORMXMLParser.getPrdArchParser(), 
					HierarchyConnector.getHierarchyCache(), 
					BrandsConnector.getBrandCache());
		return connectorInstance.productCache;
	}
	
}
