package controllers;

import impls.ComingProductDaoImplDB;
import impls.LoginImplDB;
import interfaces.ComingProductDao;
import interfaces.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.ComingProduct;
import objects.Status;

import java.sql.SQLException;
import java.util.List;

public class LoginController {
    @FXML
    Button singinButton;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;


    public void onSingin() {
        Parent rootNode = null;
        try {
            Login lg = new LoginImplDB();
            Status status = new Status();
           if(lg.verify(tfUsername.getText(),tfPassword.getText())){

               if((lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText())).equals(status.getADMIN())){
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AdminWindow.fxml"));
               }else if((lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText())).equals(status.getUSER())){
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainWindow.fxml"));
               }

               Stage stage = (Stage) singinButton.getScene().getWindow();
               stage.close();

               Stage mainStage = new Stage();
               Scene scene = new Scene(rootNode);
               mainStage.setScene(scene);
               mainStage.setTitle("АРМ працівника складу");
               mainStage.show();

           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onCancel(){
        ComingProductDao comingProductDao = new ComingProductDaoImplDB() {
        };
        try {
            List<ComingProduct> comingProducts = comingProductDao.getComingProducts();
            for (ComingProduct comingProduct : comingProducts){
                System.out.println(comingProduct.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
