package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.javaeeee.FullStackProject.representations.Item;
import com.mongodb.MongoClient;
import daos.ItemDAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

	private Datastore datastore;

	public ItemResource(final MongoClient mongoClient) {
		Morphia morphia = new Morphia();
		datastore = new Morphia().createDatastore(mongoClient, "gwlist");

	}

	@GET
	@Path("/{itemid}")
	  public Response getItem(@PathParam("itemid") String itemid) {
	    // retrieve information about the item with the provided id
		ItemDAO itemDAO = new ItemDAO(Item.class, datastore);
	    return Response
	        .ok(itemDAO.getItem(itemid))
	        .build();
	  }

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(Item item) {
		// store a new item
		datastore.save(item);
		return Response
				.created(null)
				.build();
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(Item item) {
		// store a new item
		ItemDAO itemDAO = new ItemDAO(Item.class, datastore);
		boolean update = itemDAO.updateItem(item);

		return ( (update)? Response.ok(null).build() :
				Response.notModified().build());

	}
	
	
}
