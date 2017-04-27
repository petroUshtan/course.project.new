package impls;

import objects.SoldProduct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public class SoldProductDaoImplDB implements interfaces.SoldProductDao {
    @Override
    public void addSoldProduct(SoldProduct soldProduct) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(soldProduct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public void deleteSoldProduct(SoldProduct soldProduct) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(soldProduct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public SoldProduct getSoldProduct(Long id) throws SQLException {
        SoldProduct result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (SoldProduct) session.load(SoldProduct.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    @Override
    public List<SoldProduct> getSoldProducts() throws SQLException {
        List<SoldProduct> soldProducts= null;

        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            soldProducts = session.createCriteria(SoldProduct.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return soldProducts;
    }

    @Override
    public List<SoldProduct> getSoldProducts(String  userName) throws SQLException {
        List<SoldProduct> soldProducts= null;

        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(SoldProduct.class);
            criteria.add(Restrictions.eq("userName", userName));
            soldProducts = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return soldProducts;
    }



    @Override
    public void clearTable() throws SQLException {

    }


}
