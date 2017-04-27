package impls;

import objects.Client;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public class ClientDaoImplDB implements interfaces.ClientDao {
    @Override
    public void addClient(Client client) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public void deleteClient(Client client) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
    }

    @Override
    public Client getClient(Long id) throws SQLException {
        Client result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Client) session.load(Client.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    @Override
    public List<Client> getClients() throws SQLException {
        List<Client> clients= null;

        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            clients = session.createCriteria(Client.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) session.close();
        }
        return clients;
    }

    @Override
    public void clearTable() throws SQLException {

    }



}
