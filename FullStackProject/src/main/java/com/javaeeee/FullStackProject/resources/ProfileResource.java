package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.javaeeee.FullStackProject.representations.Profile;


@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	  
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
	  public Response createProfile(
	      Profile profile) {
	    // store the new profile 
	    // ...
	    return Response
	        .created(null)
	        .build();
	  }
	
	
	}