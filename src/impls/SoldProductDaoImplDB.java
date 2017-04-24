package impls;

import objects.SoldProduct;
import objects.User;
import org.hibernate.Session;
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            soldProducts = session.createCriteria(User.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return soldProducts;
    }

    @Override
    public void clearTable() throws SQLException {

    }


}
