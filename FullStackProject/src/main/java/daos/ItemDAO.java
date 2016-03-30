package daos;

import com.javaeeee.FullStackProject.representations.Item;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class ItemDAO extends BasicDAO<Item, String> {


    public ItemDAO(Class<Item> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Item getItem(String itemId){
        return get(itemId);
    }
}
