package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.javaeeee.FullStackProject.representations.Item;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

	@GET
	@Path("/{itemid}")
	  public Response getItem(@PathParam("itemid") String itemid) {
	    // retrieve information about the profile with the provided id
	    // ...
	    return Response
	        .ok(new Item(itemid, "Test", "Washington DC", "testuser", 1000, 1))
	        .build();
	  }
	
	
}
