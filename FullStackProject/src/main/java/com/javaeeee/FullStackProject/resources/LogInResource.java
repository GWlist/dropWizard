package com.javaeeee.FullStackProject.resources;

import com.javaeeee.FullStackProject.representations.ProfileJson;
import com.javaeeee.api.GWListApi;
import com.javaeeee.api.GWListLogIn;
import com.javaeeee.dao.*;
import com.javaeeee.entities.Profile;
import com.mongodb.MongoClient;
import org.mindrot.jbcrypt.BCrypt;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class LogInResource {

    private Datastore datastore;
    private GWListApi api;
    private GWListLogIn loginApi;

    public LogInResource(final MongoClient mongoClient) {

        this.datastore = new Morphia().createDatastore(mongoClient, "gwlist");
        ProfileDAO profileDAO = new ProfileDAOImpl(Profile.class, datastore);
        LogInDAO logInDAO = new LogInDaoImpl(Profile.class, datastore);
        this.api = new GWListApi(profileDAO);
        this.loginApi = new GWListLogIn(logInDAO);

    }


    @GET
    @Path("/{userid}/{password}")
    public Response logIn(@PathParam("userid") String userid, @PathParam("password") String password) throws ProfileDaoException {
        // retrieve information about the profile with the provided id
        Profile user = api.getProfile(userid);

        if(BCrypt.hashpw(password, user.getPassword()).equals(user.getPassword())){
            //create Session
            return Response
                    .ok(loginApi.logIn(userid))
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(ProfileJson json) throws ProfileDaoException {
        // retrieve information about the profile with the provided id
        Profile profile = json.asProfile();
        profile.setPassword(BCrypt.hashpw(profile.getPassword(), BCrypt.gensalt()));
        //add checking if profile doesn't exit
        api.saveProfile(profile, datastore);
        return Response
                .created(null)
                .build();
    }

}