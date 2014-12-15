package goods4u.admin.authentication;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.innovations.retailBase.entities.legacy.StoreOperation;

public class JSONConverter {
	
	public static JSONObject getOperationInstanceJSON(StoreOperation opInstance){
		if(opInstance == null)
			return null;
		
		JSONObject hJson = new JSONObject();
		
		hJson.put("id", "AO_" + opInstance.getHierarchyId());
		hJson.put("name", opInstance.getName());
		hJson.put("value", opInstance.getLabel());
		hJson.put("hover", opInstance.getDescription());
		hJson.put("level", opInstance.getLevel());
		hJson.put("link", "/goto?id=AO_" + opInstance.getHierarchyId());
		if(opInstance.getParentHierarchy() != null)
			hJson.put("parent", "AO_" + opInstance.getParentHierarchy().getHierarchyId());
		if(opInstance.getChildren() != null && opInstance.getChildren().size() > 0){
			
			List<JSONObject> childJSONS = new ArrayList<JSONObject>();
			for(StoreOperation hrCI : opInstance.getChildren())
				childJSONS.add(getOperationInstanceJSON(hrCI));
			
			hJson.put("nextGen", childJSONS);
			
		}
		
		return hJson;
		
	}
}
