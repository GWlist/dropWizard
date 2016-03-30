package com.javaeeee.FullStackProject;

import java.net.UnknownHostException;

import com.javaeeee.FullStackProject.resources.ItemResource;
import com.javaeeee.FullStackProject.resources.ProfileResource;
import com.mongodb.MongoClient;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FullStackProjectApplication extends Application<FullStackProjectConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FullStackProjectApplication().run(args);
    }

    @Override
    public String getName() {
        return "FullStackProject";
    }

    @Override
    public void initialize(final Bootstrap<FullStackProjectConfiguration> bootstrap) {
 
    	 AssetsBundle bundle = new AssetsBundle("/html", "/");
    	 bootstrap.addBundle(bundle);

    }

    @Override
    public void run(final FullStackProjectConfiguration configuration,
                    final Environment environment) throws UnknownHostException {
    	
 
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
    	environment.lifecycle().manage(new MongoClientManager(mongoClient));
        environment.jersey().register(new ProfileResource(mongoClient));
        environment.jersey().register(new ItemResource(mongoClient));
    }

}
