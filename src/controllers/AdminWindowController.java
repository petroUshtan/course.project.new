package controllers;

import impls.UserDaoImplDB;
import interfaces.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;

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
        tcId.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        tcUsername.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        tvUserList.setItems(observableList);

        tvUserList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    Node node = ((Node) event.getTarget()).getParent();
                    TableRow row;
                    if (node instanceof TableRow) {
                        row = (TableRow) node;
                    } else {
                        // clicking on text part
                        row = (TableRow) node.getParent();
                    }
                    tfUsername.setText(((User)row.getItem()).getUsername());
                    tfPassword.setText(((User)row.getItem()).getPassword());
                }
            }
        });
    }

    public void Edit(){
        String usT=tfUsername.getText();
        String pasT=tfPassword.getText();
        Delete();
        tfUsername.setText(usT);
        tfPassword.setText(pasT);
        Add();
    }

    public void Delete (){
        try {
            userDao.deleteUser(((User)(tvUserList.getSelectionModel().getSelectedItem())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        update();
    }

    public void Add(){
        try {
            if((isUnic())&&(!tfUsername.getText().equals(""))&&(!tfPassword.getText().equals(""))){
                userDao.addUser(new User(tfUsername.getText(),tfPassword.getText()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        update();
    }

    boolean isUnic(){
        try {
            for (User u : userDao.getUser()){
                if((u.getUsername().equals(tfUsername.getText()))){
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    void update(){
        try {
            observableList= FXCollections.observableArrayList(userDao.getUser());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        tcId.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        tcUsername.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        tvUserList.setItems(observableList);
        clearField();
    }

    void clearField(){
        tfUsername.clear();
        tfPassword.clear();
    }
}
