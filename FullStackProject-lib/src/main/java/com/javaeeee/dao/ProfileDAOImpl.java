package com.javaeeee.dao;

import java.util.Optional;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.javaeeee.entities.Profile;

public class ProfileDAOImpl extends BasicDAO<Profile, String> implements ProfileDAO {

	
	public ProfileDAOImpl(Class<Profile> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
	
	public Optional <Profile> getProfile(String userid) throws ProfileDaoException {
		
		Profile profile = get(userid);
		
		if (profile != null) {
		  
			return Optional.of(profile);
		}
		else {
			return Optional.empty();
		}
	    
	}


	public boolean checkValidToken(String userid, String token)  {
		Profile p = get(userid);
		return p.getToken().equals(token);
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
