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

    UserDao userDao = new UserDaoImplDB();

    @Override
    public  String getStatusOfUser(String username, String password){
        String status="";
        try {
            List<User> users = userDao.getUser();
            for (User u : users){
                if((u.getUsername().equals(username))&&(u.getPassword().equals(password))){
                    return u.getStatus();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean verify( String username, String password) {
        try {
            List<User> users = userDao.getUser();
            for (User u : users){
                if((u.getUsername().equals(username))&&(u.getPassword().equals(password))){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
