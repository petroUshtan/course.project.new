package interfaces;

import objects.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public interface ClientDao {

    public void addClient(Client client) throws SQLException;
    public void deleteClient(Client client) throws SQLException;
    public Client getClient(Long id) throws SQLException;
    public List<Client>  getClients() throws SQLException;
    public void clearTable() throws SQLException;
}
