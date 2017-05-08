package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by Work on 18.04.2017.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil(){}

    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }   catch(Exception e){
            e.printStackTrace();
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
