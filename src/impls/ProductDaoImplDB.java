package impls;

import objects.Product;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public class ProductDaoImplDB implements interfaces.ProductDao {
    @Override
    public void addProduct(Product product) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public void deleteProduct(Product product) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public Product getProduct(Long id) throws SQLException {
        Product result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Product) session.load(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    @Override
    public List<Product> getProducts() throws SQLException {
        List<Product> products= null;

        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createCriteria(Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return products;
    }

    @Override
    public void clearTable() throws SQLException {

    }


}
