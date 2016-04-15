package com.javaeeee.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.javaeeee.entities.Profile;

public class ProfileDAOImpl extends BasicDAO<Profile, String> implements ProfileDAO {

	
	public ProfileDAOImpl(Class<Profile> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
	
	public Profile getProfile(String userid) throws ProfileDaoException {
		
		return get(userid);
	}

	public void saveProfile(Profile profile, Datastore ds) throws ProfileDaoException {

		ds.save(profile);

	}

	public boolean updateProfile(Profile profile) throws ProfileDaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
