package goods4u.consumers.hierarchies;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.innovations.retailBase.entities.legacy.HierarchyInstance;

public class JSONConverter {

	public static JSONObject getHierarchyInstanceJSON(HierarchyInstance hrInstance){
		if(hrInstance == null)
			return null;
		
		JSONObject hJson = new JSONObject();
		
		hJson.put("id", "H_" + hrInstance.getHierarchyId());
		hJson.put("name", hrInstance.getName());
		hJson.put("value", hrInstance.getLabel());
		hJson.put("hover", hrInstance.getDescription());
		hJson.put("level", hrInstance.getLevel());
		hJson.put("link", "/explore?id=H_" + hrInstance.getHierarchyId());
		if(hrInstance.getParentHierarchy() != null)
			hJson.put("parent", "H_" + hrInstance.getParentHierarchy().getHierarchyId());
		if(hrInstance.getChildren() != null && hrInstance.getChildren().size() > 0){
			
			List<JSONObject> childJSONS = new ArrayList<JSONObject>();
			for(HierarchyInstance hrCI : hrInstance.getChildren())
				childJSONS.add(getHierarchyInstanceJSON(hrCI));
			
			hJson.put("nextGen", childJSONS);
			
		}
		
		return hJson;
		
	}
	
	
}
