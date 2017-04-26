package impls;

import objects.Department;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public class DepartmentDaoImplDB implements interfaces.DepartmentDao {
    @Override
    public void addDepartment(Department department) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public void deleteDepartment(Department department) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public Department getDepartment(Long id) throws SQLException {
        Department result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Department) session.load(Department.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    @Override
    public List<Department> getDepartment() throws SQLException {
        List<Department> departments= null;
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            departments = session.createCriteria(Department.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return departments;
    }

    @Override
    public void clearTable() throws SQLException {

    }


}
