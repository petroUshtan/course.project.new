import impls.ComingProductDaoImplDB;
import impls.SoldProductDaoImplDB;
import impls.UserDaoImplDB;
import interfaces.ComingProductDao;
import interfaces.SoldProductDao;
import interfaces.UserDao;

/**
 * Created by Work on 18.04.2017.
 */
public class Factory {
    public static Factory instance = new Factory();
    public UserDao userDao;
    public SoldProductDao soldProductDao;
    public ComingProductDao comingProductDao;

    private Factory() {
    }

    public static Factory getInstance() {
        return Factory.instance;
    }

    public UserDao getUserDao() {
        if (userDao==null) userDao=new UserDaoImplDB();
        return userDao;
    }

    public SoldProductDao getSoldProductDao() {
        if (soldProductDao==null) soldProductDao=new SoldProductDaoImplDB();
        return soldProductDao;
    }

    public ComingProductDao getComingProductDao() {
        if (comingProductDao==null) comingProductDao=new ComingProductDaoImplDB();
        return comingProductDao;
    }
}
