package com.javaeeee.services;

import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.javaeeee.entities.Profile;
import com.mongodb.MongoClient;

public class ProfileServiceTest {

	
	@Test
	public void topUserTest() throws Exception {
		
		
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
		Datastore datastore = new Morphia().createDatastore(mongoClient, "gwlist");
		Profile profile = ProfileService.getTopUser(datastore);
		Assert.assertEquals(profile.getUserid(), "20");
		
	}
	
}
