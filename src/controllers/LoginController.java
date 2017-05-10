package controllers;

import impls.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import objects.Status;
import util.MyUtils;


public class LoginController {
    @FXML
    Button singinButton;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;
    @FXML
    Label lbLoginError;

    final String LOGIN_ERROR = "Дана комбінація логіну і паролю не знайдена";

    public void onSingin() {
        Parent rootNode = null;
        lbLoginError.setText("");
        try {
            Login lg = new Login();
            if(lg.verify(tfUsername.getText(),tfPassword.getText())){
               MyUtils.writeTmpFile(tfUsername.getText(),lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText()));
               if((lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText())).equals(Status.getADMIN())){
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AdminWindow.fxml"));


               }else {
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainWindow.fxml"));
               }
               Stage stage = (Stage) singinButton.getScene().getWindow();
               stage.close();

               Stage mainStage = new Stage();
               Scene scene = new Scene(rootNode);
               mainStage.setScene(scene);
               mainStage.setTitle("АРМ працівника складу");
               mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        System.exit(0);
                    }
                });
               mainStage.show();
           }else{
                lbLoginError.setText(LOGIN_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
