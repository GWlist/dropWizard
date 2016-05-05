package com.javaeeee.FullStackProject;

import com.javaeeee.FullStackProject.resources.ItemResource;
import com.javaeeee.FullStackProject.resources.LogInResource;
import com.javaeeee.FullStackProject.resources.ProfileResource;
import com.javaeeee.FullStackProject.utils.PropertiesReader;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;

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
                    final Environment environment) throws IOException {

        String serverAddress = PropertiesReader.getProperty("address");
        int port = Integer.parseInt(PropertiesReader.getProperty("port"));
    	MongoClient mongoClient = new MongoClient(serverAddress, port);
    	environment.lifecycle().manage(new MongoClientManager(mongoClient));
        environment.jersey().register(new ProfileResource(mongoClient));
        environment.jersey().register(new ItemResource(mongoClient));
        environment.jersey().register(new LogInResource(mongoClient));
        
        //Configure CORS
        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
        
       
        
    }

}
