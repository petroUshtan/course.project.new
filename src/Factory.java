import impls.UserDaoDB;
import interfaces.UserDao;

/**
 * Created by Work on 18.04.2017.
 */
public class Factory {
    public static Factory instance = new Factory();
    public UserDao userDao;

    private Factory() {
    }

    public static Factory getInstance() {
        return Factory.instance;
    }

    public UserDao getUserDao() {
        if (userDao==null) userDao=new UserDaoDB();
        return userDao;
    }

}
