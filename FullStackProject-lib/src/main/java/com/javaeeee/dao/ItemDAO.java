package com.javaeeee.dao;

import com.javaeeee.entities.Item;
import org.mongodb.morphia.Datastore;

import java.util.List;
import java.util.Optional;


public interface ItemDAO  {

	// need optional?
	Optional<Item> getItem(String itemid) throws ItemDaoException;
	
	void saveItem(Item item, Datastore ds) throws ItemDaoException;
	
	boolean updateItem(Item item) throws ItemDaoException;
	
	void deleteItem(String itemid) throws ItemDaoException;

	List<Item> searchItemsByName(String itemName) throws ItemDaoException;
}
