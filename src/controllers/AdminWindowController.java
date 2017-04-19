package controllers;

import impls.UserDaoImplDB;
import interfaces.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.User;

import java.sql.SQLException;

/**
 * Created by Work on 18.04.2017.
 */
public class AdminWindowController {

    @FXML
    TableColumn<User, String> tcId;
    @FXML
    TableColumn<User, String> tcUsername;
    @FXML
    TableView tvUserList;

    UserDao userDao = new UserDaoImplDB();
    ObservableList<User> observableList ;
    @FXML
    void initialize(){
        try {
            observableList= FXCollections.observableArrayList(userDao.getUser());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        tcId.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        tcUsername.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        tvUserList.setItems(observableList);
    }
}
