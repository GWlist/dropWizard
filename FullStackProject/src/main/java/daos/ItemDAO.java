package daos;

import com.javaeeee.FullStackProject.representations.Item;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

public class ItemDAO extends BasicDAO<Item, String> {


    public ItemDAO(Class<Item> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Item getItem(String itemId){
        return get(itemId);
    }

    
    public void saveItem(Item item, Datastore ds) {
    	ds.save(item);
    }
    
    public boolean updateItem(Item item){

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
