package controllers;

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
import util.MyUtils;
import util.UtilForDBWorking;

import java.sql.SQLException;
import java.util.List;

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

    ObservableList<User> observableList ;
    ObservableList<String> observableListStatus ;
    @FXML
    void initialize(){
        try {
            observableList= FXCollections.observableArrayList(UtilForDBWorking.getRecords(new User()));
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
                    try{
                        tfUsername.setText(((User)row.getItem()).getUsername());
                        tfPassword.setText(((User)row.getItem()).getPassword());
                        cbStatus.setItems(observableListStatus);
                        cbStatus.getSelectionModel().select(((User)row.getItem()).getStatus());
                    }catch (NullPointerException e){
                        MyUtils.AlertError("Помилка","Виберіть рядок!");
                    }
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
        cbStatus.getSelectionModel().select(statT);
        Add();
    }

    public void Delete (){
        try {
            UtilForDBWorking.deleteRecord(((tvUserList.getSelectionModel().getSelectedItem())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        update();
    }

    public void Add(){
        try {
            if((isUnic())&&(!tfUsername.getText().equals(""))&&(!tfPassword.getText().equals(""))){
                User user = new User(tfUsername.getText(),
                        tfPassword.getText(),cbStatus.getSelectionModel().getSelectedItem());
                new UtilForDBWorking().addRecord(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        update();
    }

    boolean isUnic(){
        try {
            List<User> userList = UtilForDBWorking.getRecords(new User());
            for (User u : userList){
                try {
                    if ((u.getUsername().equals(tfUsername.getText()))) {
                        return false;
                    }
                }catch (NullPointerException e){
                    System.out.println("Null string in DB!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    void update(){
        try {
            observableList= FXCollections.observableArrayList(UtilForDBWorking.getRecords(new User()));
        }
        catch (SQLException e){
            e.printStackTrace();
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
        cbStatus.getSelectionModel().clearSelection();
    }
}
