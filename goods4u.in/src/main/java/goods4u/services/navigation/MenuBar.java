package goods4u.services.navigation;

import goods4u.admin.authentication.AdminOperationConsumer;
import goods4u.consumers.hierarchies.HierarchyConsumer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/")
public class MenuBar {

	@GET
    @Path("/defaultNavigation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMenuBar(){
		JSONObject tmpJSON = HierarchyConsumer.getHierarchies();
		Response rb = Response.ok(tmpJSON.toString()).build();
		return rb;
	}
	
	@GET
    @Path("/adminNavigation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdminMenuBar(){
		
		JSONObject tmpJSON = AdminOperationConsumer.getOpertions();
		Response rb = Response.ok(tmpJSON.toString()).build();
		return rb;
	}
	
	@GET
    @Path("/customizedNavigation")
	@Produces(MediaType.APPLICATION_JSON)
    public Response storeEnquiry(@QueryParam("query") JSONObject inputJsonObj) throws Exception {
JSONObject tmpJSON = new JSONObject();
		
		Response rb = Response.ok(tmpJSON.toString()).build();
		return rb;
	}
	
}
