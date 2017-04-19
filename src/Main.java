import interfaces.UserDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.User;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
        primaryStage.setTitle("АРМ працівника складу");
        primaryStage.setScene(new Scene(root, 320, 160));
        primaryStage.show();
    }

    public static void st() {
        Factory factory = Factory.getInstance();
        UserDao userDao = factory.getUserDao();
        User u=new User();
        try{
            userDao.addUser(u);
            System.out.println(userDao.getUser());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        st();
//        launch(args);

    }
}
