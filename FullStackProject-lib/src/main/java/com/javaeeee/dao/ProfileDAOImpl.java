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


	public boolean checkValidToken(String userid, String token)  {
		Profile p = get(userid);
		return p.getSession().equals(token);
	}

	public void saveProfile(Profile profile, Datastore ds) throws ProfileDaoException {
		ds.save(profile);
	}

	public boolean updateProfile(Profile profile) throws ProfileDaoException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void deleteProfile(String userid) throws ProfileDaoException {
		 
		getDatastore().delete(userid);
	}

}
