package com.javaeeee.FullStackProject.resources;

import com.javaeeee.FullStackProject.representations.ItemJson;
import com.javaeeee.api.GWListApi;
import com.javaeeee.dao.*;
import com.javaeeee.entities.Item;
import com.javaeeee.entities.Profile;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

	private Datastore datastore;
	private GWListApi api;

	public ItemResource(final MongoClient mongoClient) {

		datastore = new Morphia().createDatastore(mongoClient, "gwlist");
        ItemDAO itemDAO = new ItemDAOImpl(Item.class, datastore);
		ProfileDAO profileDAO = new ProfileDAOImpl(Profile.class, datastore);
        this.api = new GWListApi(profileDAO, itemDAO);
	}

	@GET
	@Path("/{itemid}")
	  public Response getItem(@PathParam("itemid") String itemid) throws ItemDaoException {
	    // retrieve information about the item with the provided id
		//ItemDAO itemDAO = new ItemDAOImpl(Item.class, datastore);
		/*String user = httpRequest.getHeader("user");
		String token = httpRequest.getHeader("token");*/
		//if(api.checkValidToken(user, token)) {
			return Response
					.ok(api.getItem(itemid).get())
					.build();
		/*} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}*/
	  }

	
	/*curl -H "Content-Type: application/json" -X POST -d '{"itemid": "20", "name": "Test Item", "price" :100, "numSold": 1, "location": "DC", "userid":"15"}' 
     http://localhost:9000/service/items/create
    */

	@Context
	protected HttpServletRequest httpRequest;

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(ItemJson json) throws ItemDaoException {
		// store a new item
	    Item item = json.asItem();
		//ItemDAO itemDAO = new ItemDAO(Item.class, datastore);
        //itemDAO.saveItem(item, datastore);
		//datastore.save(item);
		api.saveItem(item, datastore);
		return Response
				.created(null)
				.build();
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(ItemJson json) throws ItemDaoException{
		// store a new item
		
		Item item = json.asItem();
		
		
		//ItemDAO itemDAO = new ItemDAO(ItemJson.class, datastore);
		boolean update = api.updateItem(item);

		return ( (update)? Response.ok(null).build() :
				Response.notModified().build());

	}
	
	@DELETE
	@Path("/{itemid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("itemid") String itemid) throws ItemDaoException {
		api.deleteItem(itemid);
		
		return Response
			      .noContent()
			      .build();
		
	}

	@GET
	@Path("search/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchItemByName(@PathParam("name") String itemName) throws ItemDaoException {

		return Response
				.ok(api.searchItemsByName(itemName))
				.build();

	}

	@GET
	@Path("searchAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchItemByName() throws ItemDaoException {

		return Response
				.ok(api.findAllItems())
				.build();

	}
	
	
}
