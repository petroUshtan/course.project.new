package impls;

import interfaces.Login;
import interfaces.UserDao;
import objects.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 19.04.2017.
 */
public class LoginImplDB implements Login {
    @Override
    public boolean verify(String username, String password) {
        UserDao userDao = new UserDaoImplDB();
        try {
            List<User> users = userDao.getUser();
            for (User u : users){
                if((u.getUsername().equals(username))&&(u.getPassword().equals(password))){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
