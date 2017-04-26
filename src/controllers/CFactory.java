package controllers;

import impls.*;
import interfaces.*;

/**
 * Created by Work on 18.04.2017.
 */
public class CFactory {
    private static CFactory instance = new CFactory();
    private UserDao userDao;
    private SoldProductDao soldProductDao;
    private ComingProductDao comingProductDao;
    private ClientDao clientDao;
    private DepartmentDao departmentDao;
    private ProductDao productDao;

    private CFactory() {
    }

    public static CFactory getInstance() {
        return CFactory.instance;
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

    public ProductDao getProductDao() {
        if (productDao==null) productDao=new ProductDaoImplDB();
        return productDao;
    }

    public DepartmentDao getDepartmentDao() {
        if (departmentDao==null) departmentDao=new DepartmentDaoImplDB();
        return departmentDao;
    }

    public ClientDao getClientDao() {
        if (clientDao==null) clientDao=new ClientDaoImplDB();
        return clientDao;
    }


}
