package impls;

import objects.ComingProduct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public class ComingProductDaoImplDB implements interfaces.ComingProductDao {
    @Override
    public void addComingProduct(ComingProduct comingProduct) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(comingProduct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public void deleteComingProduct(ComingProduct comingProduct) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(comingProduct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public ComingProduct getComingProduct(Long id) throws SQLException {
        ComingProduct result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (ComingProduct) session.load(ComingProduct.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    @Override
    public List<ComingProduct> getComingProducts() throws SQLException {
        List<ComingProduct> comingProducts= null;

        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            comingProducts = session.createCriteria(ComingProduct.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return comingProducts;
    }

    @Override
    public void clearTable() throws SQLException {

    }

    @Override
    public List<ComingProduct> getComingProducts(String  userName) throws SQLException {
        List<ComingProduct> comingProducts= null;

        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ComingProduct.class);
            criteria.add(Restrictions.eq("userName", userName));
            comingProducts = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return comingProducts;
    }


}
