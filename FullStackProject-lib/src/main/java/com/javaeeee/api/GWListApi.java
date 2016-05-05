package com.javaeeee.api;

import com.javaeeee.dao.ItemDAO;
import com.javaeeee.dao.ItemDaoException;
import com.javaeeee.dao.ProfileDAO;
import com.javaeeee.dao.ProfileDaoException;
import com.javaeeee.entities.Item;
import com.javaeeee.entities.Profile;
import com.javaeeee.services.ProfileService;
import org.mongodb.morphia.Datastore;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class GWListApi {

	private ItemDAO itemDao;
	private ProfileDAO profileDao;
    
	public GWListApi(ItemDAO dao) {
        this.itemDao = dao;
    }
	
	public GWListApi(ProfileDAO dao) {
        this.profileDao = dao;
    }

	public GWListApi(ProfileDAO daoP, ItemDAO daoI) {
		this.profileDao = daoP;
		this.itemDao = daoI;
	}

	public boolean checkValidToken(String userid, String token) {
		return profileDao.checkValidToken(userid, token);
	}

	public void deleteItem(String itemid) throws ItemDaoException {
		itemDao.deleteItem(itemid);
	}

	public List<Item> searchItemsByName(String itemName) throws ItemDaoException {
		return itemDao.searchItemsByName(itemName);
	}

	public List<Item> findAllItems() throws ItemDaoException {
		return itemDao.findAll();
	}
	
	public Optional<Item> getItem(String itemid) throws ItemDaoException {
        return itemDao.getItem(itemid);
    }
	
	public void saveItem(Item item, Datastore ds) throws ItemDaoException {
		itemDao.saveItem(item, ds);
	}
	
	public boolean updateItem(Item item) throws ItemDaoException {
		return itemDao.updateItem(item);
	}
	
	public Optional<Profile> getProfile(String userid) throws ProfileDaoException {
		return profileDao.getProfile(userid);
	}
	
	public void saveProfile(Profile profile, Datastore ds) throws ProfileDaoException {
		profileDao.saveProfile(profile, ds);
	}
	
	public boolean updateProfile(Profile profile) throws ProfileDaoException {
		return profileDao.updateProfile(profile);
	}
	
	public void deleteProfile(String userid) throws ProfileDaoException {
		profileDao.deleteProfile(userid);
	}
	
	public Profile getTopUser(Datastore ds) throws ProfileDaoException {
		
		return ProfileService.getTopUser(ds);
		
	}
	
	public String getDistance(String zip1, String zip2) throws Exception {
		
		DecimalFormat df = new DecimalFormat("#.0");
		
		double distance = ProfileService.distance(zip1, zip2);
		
		return df.format(distance);
		
	}
	
	
}
