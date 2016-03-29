package com.javaeeee.FullStackProject;

import com.mongodb.MongoClient;

import io.dropwizard.lifecycle.Managed;

public class MongoClientManager implements Managed {

	private final MongoClient mongoClient;
	
	public MongoClientManager(final MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	
	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws Exception {
		mongoClient.close();

	}

}
