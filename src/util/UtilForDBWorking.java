package util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 08.05.2017.
 */
public class UtilForDBWorking{
    public static <T> void addRecord(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    public static <T>  void deleteRecord(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    public static <T,D>  T getRecord(Long id,D className) throws SQLException {
        T result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (T) session.load(className.getClass(), id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    public static<T,D> List<T> getRecords(D className) throws SQLException {
        List<T> records= null;
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            records = session.createCriteria(className.getClass()).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return records;
    }

    public static <T,D> List<T> getRecordByFieldValue(D className,String fieldName,String  fieldValue) throws SQLException {
        List<T> result= null;
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(className.getClass());
            criteria.add(Restrictions.eq(fieldName, fieldValue));
            result = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

}
