package com.javaeeee.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.javaeeee.entities.Item;

public class ItemDAOImpl extends BasicDAO<Item, String> implements ItemDAO {

	
	public ItemDAOImpl(Class<Item> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
	
	public Item getItem(String itemid) throws ItemDaoException {
		return get(itemid);
	}

	public void saveItem(Item item, Datastore ds) throws ItemDaoException {
		ds.save(item);

	}

	public boolean updateItem(Item item) throws ItemDaoException {
		Query<Item> q = createQuery().filter("_id =", item.getItemid());

        UpdateOperations<Item> up = createUpdateOperations();
        if(item.getName() != null){
            up.set("name", item.getName());
        }
        if(item.getPrice() > 0){
            up.set("price", item.getPrice());
        }
        if(item.getNumSold() > 0){
            up.set("numSold", item.getNumSold());
        }
        if(item.getLocation() != null){
            up.set("location", item.getLocation());
        }

        UpdateResults res = update(q, up);
        return res.getWriteResult().getN() > 0;
	}
	
	
	

}
