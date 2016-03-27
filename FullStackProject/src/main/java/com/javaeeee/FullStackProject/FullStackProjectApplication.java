package com.javaeeee.FullStackProject;

import com.javaeeee.FullStackProject.resources.ItemResource;
import com.javaeeee.FullStackProject.resources.ProfileResource;

import io.dropwizard.Application;
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
        // TODO: application initialization
    }

    @Override
    public void run(final FullStackProjectConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new ProfileResource());
        environment.jersey().register(new ItemResource());
    }

}
