package com.javaeeee.dao;

import com.javaeeee.entities.Profile;

import java.util.Optional;

import org.mongodb.morphia.Datastore;


public interface ProfileDAO  {

	
	public Optional <Profile> getProfile(String userid) throws ProfileDaoException;

	boolean checkValidToken(String userid, String token);
	
	void saveProfile(Profile profile, Datastore ds) throws ProfileDaoException;
	
	boolean updateProfile(Profile profile) throws ProfileDaoException;
	
	void deleteProfile(String userid) throws ProfileDaoException;
	
}
