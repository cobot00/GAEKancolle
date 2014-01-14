package simple.dao;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.*;

public class UserDao {

    public boolean entry(HttpServletRequest req) {
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");

        System.out.println("--------------------------");
        System.out.println("id = " + id);
        System.out.println("name = " + name);

        final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        final Key key = KeyFactory.createKey("User", id);
        System.out.println("key = " + KeyFactory.keyToString(key));

        Entity user;
        try {
            user = ds.get(key);

            System.out.println("id = " + id + " exists!!");
            return false;
        } catch (EntityNotFoundException e) {
            //e.printStackTrace();
            System.out.println("create new entity");
        }

        user = new Entity("User", id);
        user.setProperty("id", id);
        user.setProperty("name", name);
        user.setProperty("update_at", new Date());

        ds.put(user);

        System.out.println("entry success");
        return true;
    }
}
