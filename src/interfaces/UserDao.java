package interfaces;

import objects.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public interface UserDao {

    public void addUser(User user) throws SQLException;
    public void deleteUser(User user) throws SQLException;
    public User getUser(int id) throws SQLException;
    public List<User>  getUser() throws SQLException;
    public void clearTable() throws SQLException;


}
