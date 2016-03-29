package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.javaeeee.FullStackProject.representations.Profile;
import com.mongodb.MongoClient;


@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	  private Datastore datastore;
	
	  public ProfileResource(final MongoClient mongoClient) {
	    
	   datastore = new Morphia().createDatastore(mongoClient, "testdb");
	    
	  }
	  
	  
	  
	  @GET
	  @Path("/{userid}")
	  public Response getProfile(@PathParam("userid") String userid) {
	    // retrieve information about the profile with the provided id
	    // ...
	    return Response
	        .ok(new Profile(userid, "Test", "User", 5, "555-555-5555", "user@test.com"))
	        .build();
	  }
	  
	  @POST
	  @Path("create")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response createProfile(
	      Profile profile) {
	    // store the new profile 
	    
		datastore.save(profile);
	    return Response
	        .created(null)
	        .build();
	  }
	
	
	}