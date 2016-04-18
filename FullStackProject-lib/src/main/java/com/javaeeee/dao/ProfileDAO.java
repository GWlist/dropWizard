package com.javaeeee.dao;

import com.javaeeee.entities.Profile;
import org.mongodb.morphia.Datastore;


public interface ProfileDAO  {

	// need optional?
	
	Profile getProfile(String userid) throws ProfileDaoException;
	
	void saveProfile(Profile profile, Datastore ds) throws ProfileDaoException;
	
	boolean updateProfile(Profile profile) throws ProfileDaoException;
	
	public void deleteProfile(String userid) throws ProfileDaoException;
	
}
