package controllers;

import impls.LoginImplDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    Button singinButton;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;


    public void onSingin() {
        try {
           if(new LoginImplDB().verify(tfUsername.getText(),tfPassword.getText())){
               Parent rootNode;
//               if(tfUsername.equals("")){
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AdminWindow.fxml"));
//               }else if(tfUsername.equals("")){
//
//               }

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

    }
}
