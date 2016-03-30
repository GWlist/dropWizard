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
		morphia.map(Item.class);
		datastore = new Morphia().createDatastore(mongoClient, "gwlist");

	}

	@GET
	@Path("/{itemid}")
	  public Response getItem(@PathParam("itemid") String itemid) {
	    // retrieve information about the profile with the provided id
	    // ...
		ItemDAO itemDAO = new ItemDAO(Item.class, datastore);
	    return Response
	        .ok(itemDAO.getItem(itemid))
	        .build();
	  }
	
	
}
