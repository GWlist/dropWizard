package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.javaeeee.FullStackProject.representations.ItemJson;
import com.javaeeee.api.GWListApi;
import com.javaeeee.dao.ItemDAOImpl;
import com.javaeeee.dao.ItemDaoException;
import com.javaeeee.dao.ItemDAO;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.javaeeee.entities.Item;


@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

	private Datastore datastore;
	private GWListApi api;

	public ItemResource(final MongoClient mongoClient) {

		datastore = new Morphia().createDatastore(mongoClient, "gwlist");
        ItemDAO itemDAO = new ItemDAOImpl(Item.class, datastore);
        GWListApi api = new GWListApi(itemDAO);
        this.api = api;
	}

	@GET
	@Path("/{itemid}")
	  public Response getItem(@PathParam("itemid") String itemid) throws ItemDaoException {
	    // retrieve information about the item with the provided id
		//ItemDAO itemDAO = new ItemDAOImpl(Item.class, datastore);
	    return Response
	        .ok(api.getItem(itemid))
	        .build();
	  }

	
	/*curl -H "Content-Type: application/json" -X POST -d '{"itemid": "20", "name": "Test Item", "price" :100, "numSold": 1, "location": "DC", "userid":"15"}' 
     http://localhost:9000/service/items/create
    */



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
	public Response deleteContact(@PathParam("itemid") String itemid) throws ItemDaoException {
		
		api.deleteItem(itemid);
		
		return Response
			      .noContent()
			      .build();
		
	}
	
	
}
