package com.javaeeee.dao;

import com.javaeeee.entities.Item;
import org.mongodb.morphia.Datastore;


public interface ItemDAO  {

	// need optional?
	
	Item getItem(String itemid) throws ItemDaoException;
	
	void saveItem(Item item, Datastore ds) throws ItemDaoException;
	
	boolean updateItem(Item item) throws ItemDaoException;
	
	
}
