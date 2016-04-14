package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.javaeeee.FullStackProject.representations.ProfileJson;
import com.javaeeee.api.GWListApi;
import com.javaeeee.dao.ItemDAO;
import com.javaeeee.dao.ItemDAOImpl;
import com.javaeeee.dao.ProfileDAO;
import com.javaeeee.dao.ProfileDAOImpl;
import com.javaeeee.dao.ProfileDaoException;
import com.javaeeee.entities.Item;
import com.mongodb.MongoClient;
import com.javaeeee.entities.Profile;


@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private Datastore datastore;
	private GWListApi api;
	
	  public ProfileResource(final MongoClient mongoClient) {
	    
		  
			datastore = new Morphia().createDatastore(mongoClient, "gwlist");
	        ProfileDAO profileDAO = new ProfileDAOImpl(Profile.class, datastore);
	        GWListApi api = new GWListApi(profileDAO);
	        this.api = api;
	    
	  }
	  
	  
	  
	  @GET
	  @Path("/{userid}")
	  public Response getProfile(@PathParam("userid") String userid) throws ProfileDaoException {
	    // retrieve information about the profile with the provided id
	    // ...
	    return Response
	        .ok(api.getProfile(userid))
	        .build();
	  }
	  
	  @POST
	  @Path("create")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response createProfile(
	      ProfileJson json) throws ProfileDaoException {
	    // store the new profile 
	    Profile profile = json.asProfile();
	    api.saveProfile(profile, datastore);
		//datastore.save(profile);
	    return Response
	        .created(null)
	        .build();
	  }
	
	
	}