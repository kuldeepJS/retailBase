package goods4u.admin.authentication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.innovations.retailBase.entities.connector.OperationConnector;
import com.innovations.retailBase.entities.legacy.StoreOperation;
import com.innovations.retailBase.logger.LoggerHandle;

public class AdminOperationConsumer {
	
	private static HashMap<Integer, StoreOperation> operations;
	private static JSONObject jsonOperations;

	public static JSONObject getOpertions() {
		
		if(operations == null){
			try {
				operations = OperationConnector.getHierarchyCache();
				jsonOperations = new JSONObject();
				
				List<JSONObject> jsons = new ArrayList<JSONObject>();
				
				int currentLevel = Integer.MAX_VALUE;
				
				for(StoreOperation instance : operations.values()){
					if(instance.getLevel() <= currentLevel){
						jsons.add(JSONConverter.getOperationInstanceJSON(instance));
						currentLevel = instance.getLevel();
					}
					else
						break;
				}
				
				jsonOperations.put("hierarchies", jsons);
			} catch (SQLException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
		return jsonOperations;
	}
	
}
