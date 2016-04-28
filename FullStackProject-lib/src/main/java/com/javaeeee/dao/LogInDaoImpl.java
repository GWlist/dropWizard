package com.javaeeee.dao;

import com.javaeeee.entities.Profile;
import com.javaeeee.pojos.Session;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.Date;
import java.util.UUID;

public class LogInDaoImpl extends BasicDAO<Profile, String> implements LogInDAO {

    public LogInDaoImpl(Class<Profile> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Session logIn(String username) {
        String token = UUID.randomUUID().toString().substring(0, 23);
        Long lastAccess = new Date().getTime();
        Query<Profile> updateQuery = getDatastore().createQuery(Profile.class).field("userid").equal(username);
        UpdateOperations<Profile> ops;
        getDatastore().createUpdateOperations(Profile.class).set("token", token);
        ops = getDatastore().createUpdateOperations(Profile.class).set("lastAccess", lastAccess).
                                                                   set("token", token);
        getDatastore().update(updateQuery, ops);

        return new Session(username, token, lastAccess);
    }

}
