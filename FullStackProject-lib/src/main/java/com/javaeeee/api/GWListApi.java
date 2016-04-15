package com.javaeeee.api;

import org.mongodb.morphia.Datastore;

import com.javaeeee.dao.ItemDAO;
import com.javaeeee.dao.ItemDaoException;
import com.javaeeee.dao.ProfileDAO;
import com.javaeeee.dao.ProfileDaoException;
import com.javaeeee.entities.Item;
import com.javaeeee.entities.Profile;

public class GWListApi {

	private ItemDAO itemDao;
	private ProfileDAO profileDao;
    
	public GWListApi(ItemDAO dao) {
        this.itemDao = dao;
    }
	
	public GWListApi(ProfileDAO dao) {
        this.profileDao = dao;
    }
	
	
	
	
	public Item getItem(String itemid) throws ItemDaoException {
        return itemDao.getItem(itemid);
    }
	
	public void saveItem(Item item, Datastore ds) throws ItemDaoException {
		itemDao.saveItem(item, ds);
	}
	
	public boolean updateItem(Item item) throws ItemDaoException {
		return itemDao.updateItem(item);
	}
	
	public Profile getProfile(String userid) throws ProfileDaoException {
		return profileDao.getProfile(userid);
	}
	
	public void saveProfile(Profile profile, Datastore ds) throws ProfileDaoException {
		profileDao.saveProfile(profile, ds);
	}
	
	
}
