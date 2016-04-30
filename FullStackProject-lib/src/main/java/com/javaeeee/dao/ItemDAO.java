package com.javaeeee.dao;

import com.javaeeee.entities.Item;

import java.util.Optional;

import org.mongodb.morphia.Datastore;


public interface ItemDAO  {

	// need optional?
	
	public Optional<Item> getItem(String itemid) throws ItemDaoException;
	
	void saveItem(Item item, Datastore ds) throws ItemDaoException;
	
	boolean updateItem(Item item) throws ItemDaoException;
	
	public void deleteItem(String itemid) throws ItemDaoException;
}
