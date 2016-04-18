package com.javaeeee.FullStackProject.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.javaeeee.FullStackProject.representations.DistanceJson;
import com.javaeeee.FullStackProject.representations.ItemJson;
import com.javaeeee.FullStackProject.representations.ProfileJson;
import com.javaeeee.api.GWListApi;
import com.javaeeee.dao.ItemDAO;
import com.javaeeee.dao.ItemDAOImpl;
import com.javaeeee.dao.ItemDaoException;
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
	  
	  @GET
	  @Path("/{userid}/distance")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response getDistance(@PathParam("userid") String userid, @QueryParam("with") String otherUser) throws Exception {
		  
		  Profile profile1 = api.getProfile(userid);
		  Profile profile2 = api.getProfile(otherUser);
		  
		  String distance = api.getDistance(profile1.getAddress().getZipcode(), profile2.getAddress().getZipcode());
		  
		  
		  DistanceJson json = new DistanceJson(userid, otherUser, distance);
		  
		  return Response.ok(json).build();
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
	  
	  @PUT
		@Path("update")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateProfile(ProfileJson json) throws ProfileDaoException{
			// store a new item
			
			Profile profile = json.asProfile();
			
			
			//ItemDAO itemDAO = new ItemDAO(ItemJson.class, datastore);
			boolean update = api.updateProfile(profile);

			return ( (update)? Response.ok(null).build() :
					Response.notModified().build());

		}
		
		@DELETE
		@Path("/{userid}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response deleteProfile(@PathParam("userid") String userid) throws ProfileDaoException {
			
			api.deleteProfile(userid);
			
			return Response
				      .noContent()
				      .build();
			
		}
	
	
	}