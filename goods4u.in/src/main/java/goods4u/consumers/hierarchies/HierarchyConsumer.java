package goods4u.consumers.hierarchies;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.innovations.retailBase.entities.connector.HierarchyConnector;
import com.innovations.retailBase.entities.legacy.HierarchyInstance;
import com.innovations.retailBase.logger.LoggerHandle;

public class HierarchyConsumer {
	
	private static HashMap<Integer, HierarchyInstance> hierarchies;
	private static JSONObject jsonHierarchies;

	public static JSONObject getHierarchies() {
		
		if(hierarchies == null){
			try {
				hierarchies = HierarchyConnector.getHierarchyCache();
				jsonHierarchies = new JSONObject();
				
				List<JSONObject> jsons = new ArrayList<JSONObject>();
				
				int currentLevel = Integer.MAX_VALUE;
				
				for(HierarchyInstance instance : hierarchies.values()){
					if(instance.getLevel() <= currentLevel){
						jsons.add(JSONConverter.getHierarchyInstanceJSON(instance));
						currentLevel = instance.getLevel();
					}
					else
						break;
				}
				
				jsonHierarchies.put("hierarchies", jsons);
			} catch (SQLException e) {
				e.printStackTrace(LoggerHandle.getLoggerPrintStream(1));
			}
		}
		
		return jsonHierarchies;
	}
	
}
