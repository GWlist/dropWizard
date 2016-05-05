package com.javaeeee.dao;

import com.javaeeee.entities.Item;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;
import java.util.Optional;

public class ItemDAOImpl extends BasicDAO<Item, String> implements ItemDAO {

	
	public ItemDAOImpl(Class<Item> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
	
	public Optional<Item> getItem(String itemid) throws ItemDaoException {
		
		Item item = get(itemid);
		
		if (item != null) {
			return Optional.of(item);
		}
		else {
			return Optional.empty();
		}
		
	
	}

	public void saveItem(Item item, Datastore ds) throws ItemDaoException {
		ds.save(item);

	}
	
	public void deleteItem(String itemid) throws ItemDaoException {
		 
		getDatastore().delete(itemid);
	}

	@Override
	public List<Item> searchItemsByName(String itemName) throws ItemDaoException {
		Query<Item> q = createQuery().field("name").containsIgnoreCase(itemName);
		QueryResults<Item> items = find(q);
		return items.asList();
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
