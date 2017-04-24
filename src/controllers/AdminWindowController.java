package controllers;

import impls.UserDaoImplDB;
import interfaces.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import objects.Status;
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
    TableColumn<User, String> tcStatus;
    @FXML
    TableView tvUserList;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;
    @FXML
    ComboBox<String> cbStatus;

    UserDao userDao = new UserDaoImplDB();
    ObservableList<User> observableList ;
    ObservableList<String> observableListStatus ;
@FXML
    void initialize(){
        try {
            observableList= FXCollections.observableArrayList(userDao.getUser());
            observableListStatus= FXCollections.observableArrayList(Status.getStatuses());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        tcId.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        tcUsername.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<User,String>("status"));
        tvUserList.setItems(observableList);
        cbStatus.setItems(observableListStatus);

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
                    cbStatus.setItems(observableListStatus);
                }
            }
        });
    }

    public void Edit(){
        String usT=tfUsername.getText();
        String pasT=tfPassword.getText();
        String statT=cbStatus.getSelectionModel().getSelectedItem();
        Delete();
        tfUsername.setText(usT);
        tfPassword.setText(pasT);
//        cbStatus.setSelectionModel();
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
                userDao.addUser(new User(tfUsername.getText(),tfPassword.getText(),cbStatus.getSelectionModel().getSelectedItem()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(cbStatus.getSelectionModel().getSelectedItem());
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
        tcStatus.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        tvUserList.setItems(observableList);
        clearField();
    }

    void clearField(){
        tfUsername.clear();
        tfPassword.clear();
//        cbStatus.setButtonCell((String)"");
    }
}
